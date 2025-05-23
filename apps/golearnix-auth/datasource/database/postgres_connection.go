package database

import (
	"github.com/gofiber/fiber/v2/log"
	models2 "golearnix-auth/domain/models"
	"os"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var DB *gorm.DB

// Connect inicializa la conexión a la base de datos y realiza las migraciones.
func Connect() error {
	// Obtener la URL de conexión a la base de datos desde el entorno
	dsn := os.Getenv("DATABASE_URL")
	if dsn == "" {
		log.Fatal("DATABASE_URL no está configurada en el entorno")
	}

	// Conectar con la base de datos
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		return err
	}

	if err := db.AutoMigrate(
		&models2.User{},  
		&models2.Session{},
	); err != nil {
		return err
	}

	DB = db // Registrar la conexión a la base de datos

	return nil
}
