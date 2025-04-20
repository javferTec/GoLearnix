package repositories

import (
	"golearnix-auth/domain/models"
)

// UserRepository define las operaciones que se pueden realizar sobre los usuarios.
type UserRepository interface {
	Create(user *models.User) error
	FindByEmail(email string) (*models.User, error)
	DeleteByID(userID string) error
	FindByID(userID string) (*models.User, error)
}
