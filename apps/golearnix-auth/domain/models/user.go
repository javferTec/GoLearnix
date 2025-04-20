package models

import (
	"time"

	"github.com/google/uuid"
)

type User struct {
	ID           uuid.UUID `gorm:"type:uuid;default:uuid_generate_v4();primaryKey" json:"id"`
	Name         string    `gorm:"size:100;not null" json:"name"`
	Email        string    `gorm:"size:255;unique;not null" json:"email"`
	PasswordHash string    `gorm:"not null" json:"-"`
	Role         UserRole  `gorm:"type:user_role;default:'student';not null" json:"role"`
	CreatedAt    time.Time `gorm:"autoCreateTime" json:"created_at"`
	UpdatedAt    time.Time `gorm:"autoUpdateTime" json:"updated_at"`
}
