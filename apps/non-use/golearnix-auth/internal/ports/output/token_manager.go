package output

import "golearnix-auth/internal/domain"

type TokenManager interface {
	GenerateToken(user *domain.User) (string, error)
	ValidateToken(tokenString string) (*domain.Session, error)
}
