package main

import (
	"database/sql"
	"golearnix-auth/internal/adapters/handler"
	"golearnix-auth/internal/adapters/jwt"
	"golearnix-auth/internal/adapters/repository"
	"golearnix-auth/internal/application"
	"golearnix-auth/internal/config"
	"log"
	"net/http"
	"time"

	_ "github.com/lib/pq"
)

func main() {
	// 1. Cargar configuración
	cfg := config.LoadConfig()

	// 2. Conectar a la base de datos
	db, err := sql.Open("postgres", cfg.DBUrl)
	if err != nil {
		log.Fatalf("could not open db connection: %v", err)
	}
	// Asegurarse de cerrar la conexión al terminar
	defer func() {
		if cerr := db.Close(); cerr != nil {
			log.Printf("error closing db: %v", cerr)
		}
	}()

	// 3. Verificar que la BD esté viva
	if err := db.Ping(); err != nil {
		log.Fatalf("could not ping db: %v", err)
	}

	// 4. Inicializar adaptadores e infraestructura
	userRepo := repository.NewUserPostgresRepository(db)
	sessionRepo := repository.NewSessionPostgresRepository(db)
	jwtMgr := jwt.NewJWTManager(cfg.JWTSecret, time.Hour*24)

	// 5. Montar la aplicación (casos de uso) y los handlers
	authService := application.NewAuthService(userRepo, sessionRepo, jwtMgr)
	authHandler := handler.NewAuthHandler(authService)

	// 6. Configurar rutas
	mux := http.NewServeMux()
	mux.HandleFunc("/register", authHandler.Register)
	mux.HandleFunc("/login", authHandler.Login)
	mux.HandleFunc("/logout", authHandler.Logout)
	mux.HandleFunc("/validate", authHandler.Validate)

	// 7. Arrancar el servidor
	addr := ":" + cfg.Port
	log.Printf("Starting Auth service on %s", addr)
	if err := http.ListenAndServe(addr, mux); err != nil {
		log.Fatalf("server failed to start: %v", err)
	}
}
