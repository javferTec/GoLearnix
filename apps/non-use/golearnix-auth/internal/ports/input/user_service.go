package input

import "golearnix-auth/internal/domain"

type UserService interface {
	GetProfile(userID string) (*domain.User, error)
}
