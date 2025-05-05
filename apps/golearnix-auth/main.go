package main

import (
	"golearnix-auth/datasource/database"
	repositories2 "golearnix-auth/datasource/repositories"
	impl2 "golearnix-auth/domain/services/impl"
	"golearnix-auth/events/rabbit/publishers"
	"golearnix-auth/presentation/api"
	controllers2 "golearnix-auth/presentation/controllers"
	"log"
	"os"
	"path/filepath"

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
	envPath := filepath.Join("..", ".env")
	if err := godotenv.Load(envPath); err != nil {
		log.Printf("Advertencia: No se pudo cargar el archivo %s: %v\n", envPath, err)
	}

	// Verificar si la variable DATABASE_URL está configurada
	if os.Getenv("DATABASE_URL") == "" {
		log.Fatal("Error: DATABASE_URL no está configurado")
	}

	// Inicializar conexión a la base de datos
	if err := database.Connect(); err != nil {
		log.Fatalf("Error al conectar con la base de datos: %v", err)
	}

	// Event Publisher
	publisher, err := publishers.NewEventPublisher("amqp://golearnix:golearnix@localhost:5672/")
	if err != nil {
		log.Fatalf("❌ Error conectando a RabbitMQ: %v", err)
	}
	defer publisher.Close()

	// 🔄 Iniciar consumidor de debug (opcional)
	//go startDebugConsumer()

	// Inicializar repositorios
	userRepo := repositories2.NewGormUserRepository(database.DB)
	sessionRepo := repositories2.NewGormSessionRepository(database.DB)

	// Inicializar servicios
	authService := impl2.NewAuthService(userRepo, sessionRepo)
	userService := impl2.NewUserService(userRepo, sessionRepo, publisher) // Iniciar el servicio de usuario

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
	log.Fatal(app.Listen(":2003"))
}

// 🔍 Consumidor RabbitMQ para debug de eventos publicados
/*func startDebugConsumer() {
	conn, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	if err != nil {
		log.Printf("❌ No se pudo conectar a RabbitMQ para consumir eventos: %v", err)
		return
	}
	ch, err := conn.Channel()
	if err != nil {
		log.Printf("❌ Error creando canal de RabbitMQ: %v", err)
		return
	}

	err = ch.ExchangeDeclare(
		"golearnix.events", "topic", true, false, false, false, nil,
	)
	if err != nil {
		log.Printf("❌ Error al declarar el exchange en el consumidor: %v", err)
		return
	}

	q, err := ch.QueueDeclare("", false, true, true, false, nil)
	if err != nil {
		log.Printf("❌ Error al declarar la cola temporal: %v", err)
		return
	}

	err = ch.QueueBind(q.Name, "user.deleted", "golearnix.events", false, nil)
	if err != nil {
		log.Printf("❌ Error al hacer binding con el routing key 'user.deleted': %v", err)
		return
	}

	msgs, err := ch.Consume(q.Name, "", true, true, false, false, nil)
	if err != nil {
		log.Printf("❌ Error al consumir mensajes: %v", err)
		return
	}

	log.Println("📡 [DEBUG] Escuchando eventos `user.deleted` desde RabbitMQ...")
	for msg := range msgs {
		fmt.Printf("📨 [Evento Recibido] %s\n", string(msg.Body))
	}
}*/
