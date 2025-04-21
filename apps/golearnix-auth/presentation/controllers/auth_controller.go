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
// @tags Auth
type AuthController struct {
	AuthService services.AuthService
}

func NewAuthController(authService services.AuthService) *AuthController {
	return &AuthController{AuthService: authService}
}

// RegisterHandler registra un nuevo usuario.
// @Summary Registrar un nuevo usuario
// @Description Crea un usuario con nombre, email, contraseña y rol.
// @Tags Auth
// @Accept json
// @Produce json
// @Param payload body request.RegisterRequest true "Datos de registro"
// @Success 201 {object} response.Response "Usuario registrado exitosamente"
// @Failure 400 {object} response.Response "Datos inválidos"
// @Failure 409 {object} response.Response "Email ya registrado"
// @Failure 500 {object} response.Response "Error interno del servidor"
// @Router /api/v1/auth/register [post]
func (ac *AuthController) RegisterHandler(c *fiber.Ctx) error {
	var req request.RegisterRequest
	if err := c.BodyParser(&req); err != nil {
		return response.Error(c, fiber.StatusBadRequest, "Datos inválidos")
	}

	if err := ac.AuthService.Register(req); err != nil {
		log.Printf("Error al registrar usuario: %v", err)
		// podrías discriminar ErrEmailExists para devolver 409
		return response.Error(c, fiber.StatusInternalServerError, err.Error())
	}

	return response.Success(c, fiber.StatusCreated, "Usuario registrado exitosamente", nil)
}

// LoginHandler permite a un usuario iniciar sesión.
// @Summary Iniciar sesión de usuario
// @Description Valida credenciales y devuelve un JWT válido por 24h.
// @Tags Auth
// @Accept json
// @Produce json
// @Param payload body request.LoginRequest true "Email y contraseña"
// @Success 200 {object} response.Response{data=map[string]string} "Inicio de sesión exitoso"
// @Failure 400 {object} response.Response "Credenciales inválidas"
// @Failure 401 {object} response.Response "Credenciales incorrectas"
// @Router /api/v1/auth/login [post]
func (ac *AuthController) LoginHandler(c *fiber.Ctx) error {
	var req request.LoginRequest
	if err := c.BodyParser(&req); err != nil {
		return response.Error(c, fiber.StatusBadRequest, "Credenciales inválidas")
	}

	token, err := ac.AuthService.Login(req)
	if err != nil {
		log.Printf("Error de inicio de sesión: %v", err)
		return response.Error(c, fiber.StatusUnauthorized, "Credenciales incorrectas")
	}

	return response.Success(c, fiber.StatusOK, "Inicio de sesión exitoso", fiber.Map{
		"token": token,
	})
}

// LogoutHandler cierra la sesión del usuario actual.
// @Summary Cerrar sesión
// @Description Invalida el JWT actual eliminando la sesión en BD.
// @Tags Auth
// @Security BearerAuth
// @Accept json
// @Produce json
// @Success 200 {object} response.Response "Sesión cerrada exitosamente"
// @Failure 401 {object} response.Response "Token no proporcionado o inválido"
// @Failure 500 {object} response.Response "Error interno al cerrar sesión"
// @Router /api/v1/auth/logout [post]
func (ac *AuthController) LogoutHandler(c *fiber.Ctx) error {
	jwtID := c.Locals("jwtID").(string)
	if err := ac.AuthService.Logout(jwtID); err != nil {
		log.Printf("Error al cerrar sesión: %v", err)
		return response.Error(c, fiber.StatusInternalServerError, "Error al cerrar sesión")
	}
	return response.Success(c, fiber.StatusOK, "Sesión cerrada exitosamente", nil)
}

// ValidateHandler valida el token JWT y devuelve email y tiempo restante.
// @Summary Validar token JWT
// @Description Comprueba que el token está activo y devuelve email y tiempo restante.
// @Tags Auth
// @Security BearerAuth
// @Accept json
// @Produce json
// @Success 200 {object} response.Response{data=map[string]string} "Token válido"
// @Failure 401 {object} response.Response "Token inválido o expirado"
// @Router /api/v1/auth/validate [get]
func (ac *AuthController) ValidateHandler(c *fiber.Ctx) error {
	userID := c.Locals("userID").(string)
	user, remaining, err := ac.AuthService.Validate(userID)
	if err != nil {
		log.Printf("Error de validación de token: %v", err)
		return response.Error(c, fiber.StatusUnauthorized, "Token inválido o sesión expirada")
	}

	expiresIn := fmt.Sprintf("%02dh:%02dm", int(remaining.Hours()), int(remaining.Minutes())%60)
	return response.Success(c, fiber.StatusOK, "Token válido", fiber.Map{
		"email":      user.Email,
		"expires_in": expiresIn,
	})
}
