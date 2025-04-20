package repositories

import (
	"golearnix-auth/domain/models"
)

// SessionRepository define las operaciones que se pueden realizar sobre las sesiones.
type SessionRepository interface {
	CreateSession(session *models.Session) error
	DeleteSessionsByUserID(userID string) error
	DeleteSessionByJwtID(jwtID string) error
	FindActiveSessionByUserID(userID string) (*models.Session, error)
	GetSessionByJwtID(jwtID string) (*models.Session, error)
}
