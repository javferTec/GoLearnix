package output

import "golearnix-auth/internal/domain"

type SessionRepository interface {
	Save(session *domain.Session) error
	DeleteByJWTID(jwtID string) error
	GetByJWTID(jwtID string) (*domain.Session, error)
}
