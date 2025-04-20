package services

import (
	"golearnix-auth/domain/models"
)

type UserService interface {
	GetMe(userID string) (*models.User, error)
	Delete(userID string) error
}
