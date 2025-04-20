package main

import (
	"golearnix-auth/datasource/database"
	repositories2 "golearnix-auth/datasource/repositories"
	impl2 "golearnix-auth/domain/services/impl"
	"golearnix-auth/presentation/api"
	controllers2 "golearnix-auth/presentation/controllers"
	"log"
	"os"

	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/logger"
	"github.com/joho/godotenv"
	fiberSwagger "github.com/swaggo/fiber-swagger"
	_ "golearnix-auth/docs"
)

// @securityDefinitions.apikey BearerAuth
// @in header
// @name Authorization
// @description Token JWT en formato Bearer. Ejemplo: "Bearer {token}"
func main() {
	// Cargar variables de entorno desde el archivo .env
	if err := godotenv.Load(); err != nil {
		log.Println("Advertencia: No se pudo cargar el archivo .env")
	}

	// Verificar si la variable DATABASE_URL está configurada
	if os.Getenv("DATABASE_URL") == "" {
		log.Fatal("Error: DATABASE_URL no está configurado")
	}

	// Inicializar conexión a la base de datos
	if err := database.Connect(); err != nil {
		log.Fatalf("Error al conectar con la base de datos: %v", err)
	}

	// Inicializar repositorios
	userRepo := repositories2.NewGormUserRepository(database.DB)
	sessionRepo := repositories2.NewGormSessionRepository(database.DB)

	// Inicializar servicios
	authService := impl2.NewAuthService(userRepo, sessionRepo)
	userService := impl2.NewUserService(userRepo, sessionRepo) // Iniciar el servicio de usuario

	// Inicializar controladores
	authController := controllers2.NewAuthController(authService)
	userController := controllers2.NewUserController(userService) // Iniciar el controlador de usuario

	// Crear instancia de Fiber
	app := fiber.New()

	// Middleware global
	app.Use(logger.New())

	// Registrar rutas usando la configuración modularizada
	api.SetupRoutes(app, authController, userController, sessionRepo) // Asegurarse de pasar ambos controladores a las rutas

	// Ruta para acceder a la documentación Swagger
	app.Get("/swagger/*", fiberSwagger.WrapHandler)

	// Iniciar servidor
	log.Fatal(app.Listen(":3000"))
}
