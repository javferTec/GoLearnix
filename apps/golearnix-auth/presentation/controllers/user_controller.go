package controllers

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/services"
	"golearnix-auth/domain/utils/response"
	"log"
)

// UserController maneja operaciones relacionadas con el usuario.
// @Tags User
type UserController struct {
	UserService services.UserService
}

func NewUserController(userService services.UserService) *UserController {
	return &UserController{UserService: userService}
}

// MeHandler obtiene información del usuario actual.
// @Summary Obtener información del usuario actual
// @Description Devuelve los datos del usuario autenticado.
// @Tags User
// @Security BearerAuth
// @Accept json
// @Produce json
// @Success 200 {object} response.Response{data=map[string]interface{}} "Información del usuario"
// @Failure 401 {object} response.Response "No autorizado"
// @Failure 500 {object} response.Response "Error interno del servidor"
// @Router /api/v1/auth/me [get]
func (uc *UserController) MeHandler(c *fiber.Ctx) error {
	userID := c.Locals("userID").(string)
	user, err := uc.UserService.GetMe(userID)
	if err != nil {
		log.Printf("Error al obtener la información del usuario: %v", err)
		return response.Error(c, fiber.StatusInternalServerError, "No se pudo obtener el usuario")
	}
	return response.Success(c, fiber.StatusOK, "Información del usuario", fiber.Map{
		"user": user,
	})
}

// DeleteHandler elimina la cuenta del usuario actual.
// @Summary Eliminar cuenta de usuario
// @Description Borra al usuario autenticado y todas sus sesiones.
// @Tags User
// @Security BearerAuth
// @Accept json
// @Produce json
// @Success 200 {object} response.Response "Usuario eliminado exitosamente"
// @Failure 401 {object} response.Response "No autorizado"
// @Failure 500 {object} response.Response "Error interno del servidor"
// @Router /api/v1/auth/delete [delete]
func (uc *UserController) DeleteHandler(c *fiber.Ctx) error {
	userID := c.Locals("userID").(string)
	if err := uc.UserService.Delete(userID); err != nil {
		log.Printf("Error al eliminar el usuario: %v", err)
		return response.Error(c, fiber.StatusInternalServerError, "No se pudo eliminar el usuario")
	}
	return response.Success(c, fiber.StatusOK, "Usuario eliminado exitosamente", nil)
}
