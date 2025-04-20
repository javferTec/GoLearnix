package jwt

import (
	"github.com/golang-jwt/jwt/v5"
	"github.com/google/uuid"
	"golearnix-auth/internal/domain"
	"golearnix-auth/internal/ports/output"
	"time"
)

type JWTManager struct {
	SecretKey     string
	TokenDuration time.Duration
}

func NewJWTManager(secret string, duration time.Duration) output.TokenManager {
	return &JWTManager{SecretKey: secret, TokenDuration: duration}
}

func (j *JWTManager) GenerateToken(user *domain.User) (string, error) {
	now := time.Now().UTC()
	jwtID := uuid.New().String()

	claims := jwt.MapClaims{
		"sub":   user.ID,
		"name":  user.Name,
		"email": user.Email,
		"role":  user.Role,
		"jti":   jwtID,
		"iat":   now.Unix(),
		"exp":   now.Add(j.TokenDuration).Unix(),
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	return token.SignedString([]byte(j.SecretKey))
}

func (j *JWTManager) ValidateToken(tokenString string) (*domain.Session, error) {
	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
		return []byte(j.SecretKey), nil
	})

	if err != nil || !token.Valid {
		return nil, err
	}

	claims := token.Claims.(jwt.MapClaims)
	session := &domain.Session{
		JWTID:     claims["jti"].(string),
		UserID:    claims["sub"].(string),
		IssuedAt:  time.Unix(int64(claims["iat"].(float64)), 0),
		ExpiresAt: time.Unix(int64(claims["exp"].(float64)), 0),
	}

	return session, nil
}
