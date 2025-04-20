package api

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/repositories"
	"golearnix-auth/presentation/controllers"
	"golearnix-auth/presentation/middlewares"
)

// SetupUserRoutes configura las rutas relacionadas con el usuario autenticado
func SetupUserRoutes(app *fiber.App, userController *controllers.UserController, sessionRepo repositories.SessionRepository) {
	// Rutas del usuario autenticado
	userGroup := app.Group("/api/v1/user", middlewares.AuthMiddleware(sessionRepo))
	userGroup.Get("/me", userController.MeHandler)
	userGroup.Delete("/delete", userController.DeleteHandler)
}
