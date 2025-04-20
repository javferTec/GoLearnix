package config

import (
	"log"
	"os"
)

type Config struct {
	DBUrl     string
	JWTSecret string
	Port      string
}

func LoadConfig() Config {
	return Config{
		DBUrl:     getEnv("DATABASE_URL", "postgres://postgres:1234@localhost:5432/auth_db?sslmode=disable"),
		JWTSecret: getEnv("JWT_SECRET", "supersecretkey"),
		Port:      getEnv("PORT", "9090"),
	}
}

func getEnv(key string, fallback string) string {
	if value := os.Getenv(key); value != "" {
		return value
	}
	log.Printf("Env variable %s not set. Using default: %s", key, fallback)
	return fallback
}
