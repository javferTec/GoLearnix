package repositories

import (
	"golearnix-auth/domain/models"
	"golearnix-auth/domain/repositories"
	"gorm.io/gorm"
)

// GormUserRepository es la implementación concreta de UserRepository usando GORM.
type GormUserRepository struct {
	db *gorm.DB
}

// NewGormUserRepository crea una nueva instancia de GormUserRepository.
func NewGormUserRepository(db *gorm.DB) repositories.UserRepository {
	return &GormUserRepository{db: db}
}

// Create guarda un nuevo usuario en la base de datos.
func (r *GormUserRepository) Create(user *models.User) error {
	return r.db.Create(user).Error
}

// FindByEmail busca un usuario por su correo electrónico.
func (r *GormUserRepository) FindByEmail(email string) (*models.User, error) {
	var user models.User
	err := r.db.Where("email = ?", email).First(&user).Error
	return &user, err
}

// DeleteByID elimina un usuario por su ID.
func (r *GormUserRepository) DeleteByID(userID string) error {
	return r.db.Where("id = ?", userID).Delete(&models.User{}).Error
}

// FindByID busca un usuario por su ID.
func (r *GormUserRepository) FindByID(userID string) (*models.User, error) {
	var user models.User
	err := r.db.First(&user, "id = ?", userID).Error
	return &user, err
}
