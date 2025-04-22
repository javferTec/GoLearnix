package impl

import (
	"fmt"
	"golearnix-auth/domain/dto/events"
	"golearnix-auth/domain/models"
	repositories2 "golearnix-auth/domain/repositories"
	"golearnix-auth/domain/services"
	"golearnix-auth/events/rabbit/publishers"
)

// UserServiceImpl es la implementación concreta de UserService.
type UserServiceImpl struct {
	UserRepo       repositories2.UserRepository
	SessionRepo    repositories2.SessionRepository
	EventPublisher *publishers.EventPublisher
}

// NewUserService crea una nueva instancia de UserServiceImpl.
func NewUserService(userRepo repositories2.UserRepository, sessionRepo repositories2.SessionRepository, publisher *publishers.EventPublisher) services.UserService {
	return &UserServiceImpl{
		UserRepo:       userRepo,
		SessionRepo:    sessionRepo,
		EventPublisher: publisher,
	}
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
	// Obtener info del usuario antes de borrarlo
	user, err := us.UserRepo.FindByID(userID)
	if err != nil {
		return fmt.Errorf("error obteniendo usuario para eliminar: %w", err)
	}

	// Eliminar sesiones activas del usuario
	if err := us.SessionRepo.DeleteSessionsByUserID(userID); err != nil {
		return fmt.Errorf("error al eliminar sesiones del usuario %s: %w", userID, err)
	}

	// Eliminar el usuario
	if err := us.UserRepo.DeleteByID(userID); err != nil {
		return fmt.Errorf("error al eliminar usuario %s: %w", userID, err)
	}

	// Publicar evento con ID y email del usuario eliminado
	event := events.UserDeletedEvent{
		UserID: user.ID.String(),
		Email:  user.Email,
	}
	if err := us.EventPublisher.PublishUserDeleted(event); err != nil {
		return fmt.Errorf("error al publicar evento UserDeleted: %w", err)
	}

	return nil
}
