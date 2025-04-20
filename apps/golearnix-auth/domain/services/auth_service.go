package services

import (
	"golearnix-auth/domain/dto/request"
	"golearnix-auth/domain/models"
	"time"
)

// AuthService define las operaciones de autenticación.
type AuthService interface {
	Register(req request.RegisterRequest) error
	Login(req request.LoginRequest) (string, error)
	Logout(userID string) error
	Validate(userID string) (*models.User, time.Duration, error)
}
