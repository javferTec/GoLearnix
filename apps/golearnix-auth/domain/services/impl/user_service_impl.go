package impl

import (
	"fmt"
	"golearnix-auth/domain/models"
	repositories2 "golearnix-auth/domain/repositories"
	"golearnix-auth/domain/services"
)

// UserServiceImpl es la implementación concreta de UserService.
type UserServiceImpl struct {
	UserRepo    repositories2.UserRepository    // Repositorio de usuarios
	SessionRepo repositories2.SessionRepository // Repositorio de sesiones
}

// NewUserService crea una nueva instancia de UserServiceImpl.
func NewUserService(userRepo repositories2.UserRepository, sessionRepo repositories2.SessionRepository) services.UserService {
	return &UserServiceImpl{UserRepo: userRepo, SessionRepo: sessionRepo}
}

// GetMe obtiene la información del usuario actual por su ID.
func (us *UserServiceImpl) GetMe(userID string) (*models.User, error) {
	// Buscar el usuario por ID
	user, err := us.UserRepo.FindByID(userID)
	if err != nil {
		// Envolver el error con más contexto para facilitar el rastreo
		return nil, fmt.Errorf("no se pudo obtener el usuario con ID %s: %w", userID, err)
	}
	return user, nil
}

// Delete elimina un usuario y sus sesiones activas.
func (us *UserServiceImpl) Delete(userID string) error {
	// Eliminar sesiones activas del usuario
	if err := us.SessionRepo.DeleteSessionsByUserID(userID); err != nil {
		// Añadir más contexto a los errores, utilizando fmt.Errorf para envolverlos
		return fmt.Errorf("error al eliminar las sesiones activas del usuario con ID %s: %w", userID, err)
	}

	// Eliminar el usuario
	if err := us.UserRepo.DeleteByID(userID); err != nil {
		// Añadir más contexto a los errores
		return fmt.Errorf("error al eliminar el usuario con ID %s: %w", userID, err)
	}

	return nil
}
