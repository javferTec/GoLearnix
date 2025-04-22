package services

import (
	"golearnix-auth/domain/models"
)

// UserService define las operaciones de usuario
type UserService interface {
	GetMe(userID string) (*models.User, error)
	Delete(userID string) error
}
