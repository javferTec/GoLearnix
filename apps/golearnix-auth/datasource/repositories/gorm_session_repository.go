package repositories

import (
	"golearnix-auth/domain/models"
	"time"

	"gorm.io/gorm"
)

// GormSessionRepository implementa SessionRepository usando GORM.
type GormSessionRepository struct {
	DB *gorm.DB
}

// NewGormSessionRepository crea una nueva instancia de GormSessionRepository.
func NewGormSessionRepository(db *gorm.DB) *GormSessionRepository {
	return &GormSessionRepository{DB: db}
}

// CreateSession guarda una nueva sesión en la base de datos.
func (r *GormSessionRepository) CreateSession(session *models.Session) error {
	return r.DB.Create(session).Error
}

// DeleteSessionsByUserID elimina todas las sesiones asociadas a un usuario.
func (r *GormSessionRepository) DeleteSessionsByUserID(userID string) error {
	return r.DB.Where("user_id = ?", userID).Delete(&models.Session{}).Error
}

// DeleteSessionByJwtID elimina una sesión específica por su JwtID.
func (r *GormSessionRepository) DeleteSessionByJwtID(jwtID string) error {
	// Elimina la sesión cuyo jwtID coincida
	return r.DB.Where("jwt_id = ?", jwtID).Delete(&models.Session{}).Error
}

// FindActiveSessionByUserID encuentra una sesión activa por el ID del usuario.
func (r *GormSessionRepository) FindActiveSessionByUserID(userID string) (*models.Session, error) {
	var session models.Session
	err := r.DB.Where("user_id = ? AND expires_at > ?", userID, time.Now()).First(&session).Error
	if err != nil {
		return nil, err
	}
	return &session, nil
}

// GetSessionByJwtID encuentra una sesión por su JWT ID.
func (r *GormSessionRepository) GetSessionByJwtID(jwtID string) (*models.Session, error) {
	var session models.Session
	err := r.DB.Where("jwt_id = ?", jwtID).First(&session).Error
	if err != nil {
		return nil, err // Si no encuentra la sesión, regresará un error
	}
	return &session, nil
}
