package impl

import (
	"errors"
	"fmt"
	"golearnix-auth/domain/dto/request"
	models2 "golearnix-auth/domain/models"
	repositories2 "golearnix-auth/domain/repositories"
	"golearnix-auth/domain/services"
	"golearnix-auth/domain/utils/email"
	"golearnix-auth/domain/utils/security"
	"time"

	"github.com/google/uuid"
)

// AuthServiceImpl implementa la lógica del servicio de autenticación.
type AuthServiceImpl struct {
	UserRepo    repositories2.UserRepository
	SessionRepo repositories2.SessionRepository
}

// NewAuthService inicializa una nueva instancia de AuthService.
func NewAuthService(userRepo repositories2.UserRepository, sessionRepo repositories2.SessionRepository) services.AuthService {
	return &AuthServiceImpl{
		UserRepo:    userRepo,
		SessionRepo: sessionRepo,
	}
}

// Register crea un nuevo usuario en la base de datos.
func (as *AuthServiceImpl) Register(req request.RegisterRequest) error {
	// Validación de campos requeridos
	if req.Email == "" || req.Password == "" || req.Name == "" {
		return fmt.Errorf("todos los campos son requeridos")
	}

	// Validación de formato de correo electrónico
	if !email.IsValidEmail(req.Email) {
		return fmt.Errorf("el correo electrónico no es válido")
	}

	// Validación de rol
	validRoles := map[models2.UserRole]struct{}{
		models2.Admin:      {},
		models2.Instructor: {},
		models2.Student:    {},
	}

	if _, valid := validRoles[models2.UserRole(req.Role)]; !valid {
		return fmt.Errorf("rol inválido: %s", req.Role)
	}

	// Hash de la contraseña
	hashedPassword, err := security.HashPassword(req.Password)
	if err != nil {
		return fmt.Errorf("error al procesar la contraseña: %v", err)
	}

	// Crear el objeto user
	user := models2.User{
		Name:         req.Name,
		Email:        req.Email,
		PasswordHash: hashedPassword,
		Role:         models2.UserRole(req.Role),
	}

	// Registrar al usuario en el repositorio
	if err := as.UserRepo.Create(&user); err != nil {
		return fmt.Errorf("error al registrar el usuario: %v", err)
	}

	return nil
}

// Login autentica un usuario y crea una sesión con un token JWT.
func (as *AuthServiceImpl) Login(req request.LoginRequest) (string, error) {
	user, err := as.UserRepo.FindByEmail(req.Email)
	if err != nil {
		return "", fmt.Errorf("usuario no encontrado: %v", err)
	}

	if !security.CheckPasswordHash(req.Password, user.PasswordHash) {
		return "", errors.New("credenciales inválidas")
	}

	jwtID := uuid.New() // un uuid.UUID
	token, err := security.GenerateToken(user.ID, string(user.Role), jwtID.String(), 24*time.Hour)
	if err != nil {
		return "", fmt.Errorf("error al generar el token: %v", err)
	}

	session := models2.Session{
		UserID:    user.ID, // uuid.UUID
		JwtID:     jwtID,   // uuid.UUID
		IssuedAt:  time.Now(),
		ExpiresAt: time.Now().Add(24 * time.Hour),
	}

	if err := as.SessionRepo.CreateSession(&session); err != nil {
		return "", fmt.Errorf("error al crear sesión: %v", err)
	}

	return token, nil
}

// Logout elimina la sesión asociada a un JWT específico.
func (as *AuthServiceImpl) Logout(jwtID string) error {
	// Llamamos al repositorio para eliminar la sesión usando el jwtID
	if err := as.SessionRepo.DeleteSessionByJwtID(jwtID); err != nil {
		return fmt.Errorf("error al eliminar la sesión: %v", err)
	}
	return nil
}

// Validate devuelve información del usuario si el ID es válido.
func (as *AuthServiceImpl) Validate(userID string) (*models2.User, time.Duration, error) {
	// 1) Buscamos el usuario
	user, err := as.UserRepo.FindByID(userID)
	if err != nil {
		return nil, 0, fmt.Errorf("usuario no encontrado: %v", err)
	}

	// 2) Recuperamos la sesión activa (solo las que no hayan expirado)
	session, err := as.SessionRepo.FindActiveSessionByUserID(userID)
	if err != nil {
		return nil, 0, fmt.Errorf("sesión no encontrada o expirada: %v", err)
	}

	// 3) Calculamos el tiempo restante
	remaining := session.ExpiresAt.Sub(time.Now())
	if remaining < 0 {
		return nil, 0, fmt.Errorf("la sesión ya expiró")
	}

	return user, remaining, nil
}
