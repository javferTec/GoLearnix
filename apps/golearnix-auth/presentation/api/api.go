package api

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/repositories"
	controllers2 "golearnix-auth/presentation/controllers"
)

func SetupRoutes(app *fiber.App, authController *controllers2.AuthController, userController *controllers2.UserController, sessionRepo repositories.SessionRepository) {
	// Configurar rutas de autenticación
	SetupAuthRoutes(app, authController, sessionRepo)

	// Configurar rutas del usuario
	SetupUserRoutes(app, userController, sessionRepo) // Aquí pasas el repositorio de sesiones
}
