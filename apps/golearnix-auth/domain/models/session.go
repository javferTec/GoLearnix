package models

import (
	"time"

	"github.com/google/uuid"
)

// Session representa una sesión de usuario activa.
type Session struct {
	ID        uuid.UUID `gorm:"type:uuid;default:uuid_generate_v4();primaryKey" json:"id"`
	UserID    uuid.UUID `gorm:"type:uuid;not null;index:user_id_index" json:"user_id"`
	JwtID     uuid.UUID `gorm:"type:uuid;not null;unique" json:"jwt_id"`
	IssuedAt  time.Time `gorm:"not null" json:"issued_at"`
	ExpiresAt time.Time `gorm:"not null" json:"expires_at"`
}
