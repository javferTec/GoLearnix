package controllers

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/services"
	"golearnix-auth/domain/utils/response"
	"log"
)

// UserController maneja operaciones relacionadas con el usuario.
type UserController struct {
	UserService services.UserService
}

// NewUserController crea una nueva instancia de UserController.
func NewUserController(userService services.UserService) *UserController {
	return &UserController{UserService: userService}
}

// MeHandler obtiene información del usuario actual.
// @Summary Obtener información del usuario actual
// @Tags User
// @Accept json
// @Produce json
// @Security BearerAuth
// @Success 200 {object} response.UserInfoResponse "Información del usuario actual"
// @Failure 401 {object} response.ErrorMessage "No autorizado"
// @Failure 500 {object} response.ErrorMessage "Error interno del servidor"
// @Router /api/v1/auth/me [get]
func (uc *UserController) MeHandler(c *fiber.Ctx) error {
	userID := c.Locals("userID").(string)

	user, err := uc.UserService.GetMe(userID)
	if err != nil {
		log.Printf("Error al obtener la información del usuario: %v", err)
		return response.ErrorResponse(c, fiber.StatusInternalServerError, "No se pudo obtener la información del usuario")
	}

	return response.SuccessResponse(c, fiber.StatusOK, "Información del usuario", fiber.Map{"user": user})
}

// DeleteHandler elimina la cuenta del usuario actual.
// @Summary Eliminar la cuenta del usuario actual
// @Tags User
// @Accept json
// @Produce json
// @Security BearerAuth
// @Success 200 {object} response.SuccessMessage "Usuario eliminado exitosamente"
// @Failure 401 {object} response.ErrorMessage "No autorizado"
// @Failure 500 {object} response.ErrorMessage "Error interno del servidor"
// @Router /api/v1/auth/delete [delete]
func (uc *UserController) DeleteHandler(c *fiber.Ctx) error {
	userID := c.Locals("userID").(string)

	if err := uc.UserService.Delete(userID); err != nil {
		log.Printf("Error al eliminar el usuario: %v", err)
		return response.ErrorResponse(c, fiber.StatusInternalServerError, "No se pudo eliminar el usuario")
	}

	return response.SuccessResponse(c, fiber.StatusOK, "Usuario eliminado exitosamente", nil)
}
