package controllers

import (
	"fmt"
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/dto/request"
	"golearnix-auth/domain/services"
	"golearnix-auth/domain/utils/response"
	"log"
)

// AuthController maneja las operaciones de autenticación.
type AuthController struct {
	AuthService services.AuthService
}

// NewAuthController crea una nueva instancia de AuthController.
func NewAuthController(authService services.AuthService) *AuthController {
	return &AuthController{AuthService: authService}
}

// RegisterHandler registra un nuevo usuario.
// @Summary Registrar un nuevo usuario
// @Tags Auth
// @Accept json
// @Produce json
// @Param request body request.RegisterRequest true "Detalles del usuario para registro"
// @Success 201 {object} response.SuccessMessage{Message=string} "Usuario registrado exitosamente"
// @Failure 400 {object} response.ErrorMessage{Message=string} "Error en los datos de entrada"
// @Failure 500 {object} response.ErrorMessage{Message=string} "Error interno del servidor"
// @Router /api/v1/auth/register [post]
func (ac *AuthController) RegisterHandler(c *fiber.Ctx) error {
	var req request.RegisterRequest
	if err := c.BodyParser(&req); err != nil {
		return response.ErrorResponse(c, fiber.StatusBadRequest, "Datos inválidos")
	}

	if err := ac.AuthService.Register(req); err != nil {
		log.Printf("Error al registrar usuario: %v", err)
		return response.ErrorResponse(c, fiber.StatusInternalServerError, err.Error())
	}

	return response.SuccessResponse(c, fiber.StatusCreated, "Usuario registrado exitosamente", nil)
}

// LoginHandler permite a un usuario iniciar sesión.
// @Summary Iniciar sesión de usuario
// @Tags Auth
// @Accept json
// @Produce json
// @Param request body request.LoginRequest true "Credenciales de inicio de sesión"
// @Success 200 {object} response.SuccessMessage{Token=string} "Token de acceso"
// @Failure 401 {object} response.ErrorMessage{Message=string} "Credenciales incorrectas"
// @Failure 400 {object} response.ErrorMessage{Message=string} "Error en los datos de entrada"
// @Router /api/v1/auth/login [post]
func (ac *AuthController) LoginHandler(c *fiber.Ctx) error {
	var req request.LoginRequest
	if err := c.BodyParser(&req); err != nil {
		return response.ErrorResponse(c, fiber.StatusBadRequest, "Credenciales inválidas")
	}

	token, err := ac.AuthService.Login(req)
	if err != nil {
		log.Printf("Error de inicio de sesión: %v", err)
		return response.ErrorResponse(c, fiber.StatusUnauthorized, "Credenciales incorrectas")
	}

	return response.SuccessResponse(c, fiber.StatusOK, "Inicio de sesión exitoso", fiber.Map{"token": token})
}

// LogoutHandler cierra la sesión del usuario actual.
// @Summary Cerrar sesión del usuario
// @Tags Auth
// @Accept json
// @Produce json
// @Security BearerAuth
// @Success 200 {object} response.SuccessMessage{Message=string} "Sesión cerrada exitosamente"
// @Failure 400 {object} response.ErrorMessage{Message=string} "Error al cerrar sesión"
// @Router /api/v1/auth/logout [post]
func (ac *AuthController) LogoutHandler(c *fiber.Ctx) error {
	// Obtener el jwtID desde el contexto (esto debería provenir de un middleware que valida el JWT)
	jwtID := c.Locals("jwtID").(string) // Aquí asumimos que tienes un middleware que coloca jwtID en los Locals

	// Llamar al servicio para cerrar la sesión
	if err := ac.AuthService.Logout(jwtID); err != nil {
		log.Printf("Error al cerrar sesión: %v", err)
		return response.ErrorResponse(c, fiber.StatusInternalServerError, "Error al cerrar sesión")
	}

	// Responder con un mensaje de éxito
	return response.SuccessResponse(c, fiber.StatusOK, "Sesión cerrada exitosamente", nil)
}

// ValidateHandler valida el token JWT y devuelve el correo y tiempo restante.
// @Summary Validar el token JWT
// @Tags Auth
// @Accept json
// @Produce json
// @Security BearerAuth
// @Success 200 {object} response.SuccessMessage{Email=string, ExpiresIn=string} "Token válido"
// @Failure 401 {object} response.ErrorMessage{Message=string} "Token inválido o sesión expirada"
// @Router /api/v1/auth/validate [get]
func (ac *AuthController) ValidateHandler(c *fiber.Ctx) error {
	userID := c.Locals("userID").(string)

	// Ahora Validate devuelve usuario y duración restante
	user, remaining, err := ac.AuthService.Validate(userID)
	if err != nil {
		log.Printf("Error de validación de token: %v", err)
		return response.ErrorResponse(c, fiber.StatusUnauthorized, "Token inválido o sesión expirada")
	}

	// Formateamos horas y minutos
	hours := int(remaining.Hours())
	minutes := int(remaining.Minutes()) % 60
	expiresIn := fmt.Sprintf("%02dh:%02dm", hours, minutes)

	return response.SuccessResponse(c, fiber.StatusOK, "Token válido", fiber.Map{
		"email":      user.Email,
		"expires_in": expiresIn,
	})
}
