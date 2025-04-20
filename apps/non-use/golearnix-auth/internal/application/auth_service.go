package application

import (
	"github.com/google/uuid"
	"golang.org/x/crypto/bcrypt"
	"golearnix-auth/internal/domain"
	"golearnix-auth/internal/ports/output"
	"time"
)

type AuthService struct {
	UserRepo    output.UserRepository
	SessionRepo output.SessionRepository
	JWTManager  output.TokenManager
}

func NewAuthService(userRepo output.UserRepository, sessionRepo output.SessionRepository, jwtManager output.TokenManager) *AuthService {
	return &AuthService{
		UserRepo:    userRepo,
		SessionRepo: sessionRepo,
		JWTManager:  jwtManager,
	}
}

// Register maneja el registro de un nuevo usuario y retorna el token JWT generado
func (s *AuthService) Register(name, email, password string, role domain.UserRole) (string, error) {
	hashedPassword, err := bcrypt.GenerateFromPassword([]byte(password), bcrypt.DefaultCost)
	if err != nil {
		return "", err
	}

	user := &domain.User{
		ID:           uuid.New().String(),
		Name:         name,
		Email:        email,
		PasswordHash: string(hashedPassword),
		Role:         role,
		CreatedAt:    time.Now().UTC(),
		UpdatedAt:    time.Now().UTC(),
	}

	if err := s.UserRepo.Save(user); err != nil {
		return "", err
	}

	token, err := s.JWTManager.GenerateToken(user)
	if err != nil {
		return "", err
	}

	claims, _ := s.JWTManager.ValidateToken(token)

	session := &domain.Session{
		ID:        uuid.New().String(),
		UserID:    user.ID,
		JWTID:     claims.JWTID,
		IssuedAt:  claims.IssuedAt,
		ExpiresAt: claims.ExpiresAt,
	}

	if err := s.SessionRepo.Save(session); err != nil {
		return "", err
	}

	return token, nil
}

// Login maneja el login de un usuario y retorna el token JWT generado
func (s *AuthService) Login(email, password string) (string, error) {
	user, err := s.UserRepo.FindByEmail(email)
	if err != nil {
		return "", err
	}

	err = bcrypt.CompareHashAndPassword([]byte(user.PasswordHash), []byte(password))
	if err != nil {
		return "", err
	}

	token, err := s.JWTManager.GenerateToken(user)
	if err != nil {
		return "", err
	}

	claims, _ := s.JWTManager.ValidateToken(token)

	session := &domain.Session{
		ID:        uuid.New().String(),
		UserID:    user.ID,
		JWTID:     claims.JWTID,
		IssuedAt:  claims.IssuedAt,
		ExpiresAt: claims.ExpiresAt,
	}

	if err := s.SessionRepo.Save(session); err != nil {
		return "", err
	}

	return token, nil
}

// Logout maneja el logout de un usuario
func (s *AuthService) Logout(jwtID string) error {
	return s.SessionRepo.DeleteByJWTID(jwtID)
}

// ValidateToken valida un token JWT y devuelve la sesión asociada
func (s *AuthService) ValidateToken(token string) (*domain.Session, error) {
	session, err := s.JWTManager.ValidateToken(token)
	if err != nil {
		return nil, err
	}

	storedSession, err := s.SessionRepo.GetByJWTID(session.JWTID)
	if err != nil {
		return nil, err
	}

	return storedSession, nil
}
