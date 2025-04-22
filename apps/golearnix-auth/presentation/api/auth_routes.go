package api

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/repositories"
	"golearnix-auth/presentation/controllers"
	"golearnix-auth/presentation/middlewares"
)

// SetupAuthRoutes configura las rutas relacionadas con la autenticación
func SetupAuthRoutes(app *fiber.App, authController *controllers.AuthController, sessionRepo repositories.SessionRepository) {
	// Rutas públicas de autenticación
	authGroup := app.Group("/api/v1/auth")
	authGroup.Post("/register", authController.RegisterHandler)
	authGroup.Post("/login", authController.LoginHandler)

	// Rutas protegidas con JWT
	protected := authGroup.Group("/", middlewares.AuthMiddleware(sessionRepo))
	protected.Post("/logout", authController.LogoutHandler)
	protected.Get("/validate", authController.ValidateHandler)
}
