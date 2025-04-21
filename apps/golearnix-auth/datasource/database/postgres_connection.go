package database

import (
	models2 "golearnix-auth/domain/models"
	"log"
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

	// Eliminar la tabla de sesiones
	/*err = db.Migrator().DropTable(&models2.Session{})
	if err != nil {
		log.Printf("❌ Error al eliminar la tabla de sesiones: %v\n", err)
	}*/

	// Realizar migraciones automáticas
	log.Println("✅ Iniciando migraciones...")

	if err := db.AutoMigrate(
		&models2.User{},    // Migrar el modelo de User
		&models2.Session{}, // Migrar el modelo de Session
	); err != nil {
		log.Printf("❌ Error al ejecutar las migraciones: %v\n", err)
		return err
	}

	// Registrar la conexión a la base de datos
	DB = db
	log.Println("✅ Conexión a la base de datos establecida correctamente")

	return nil
}
