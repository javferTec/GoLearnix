package output

import "golearnix-auth/internal/domain"

type UserRepository interface {
	Save(user *domain.User) error
	FindByEmail(email string) (*domain.User, error)
	FindByID(id string) (*domain.User, error)
}
