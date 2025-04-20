package security

import (
	"os"
	"time"

	"github.com/golang-jwt/jwt/v4"
	"github.com/google/uuid"
)

var jwtSecret = []byte(os.Getenv("JWT_SECRET"))

// Claims define los datos personalizados del token JWT.
type Claims struct {
	Sub  string `json:"sub"`  // ID del usuario
	Role string `json:"role"` // Rol del usuario
	jwt.RegisteredClaims
}

// GenerateToken genera un JWT para un usuario con duración específica y JTI.
func GenerateToken(userID uuid.UUID, role string, jwtID string, duration time.Duration) (string, error) {
	claims := Claims{
		Sub:  userID.String(),
		Role: role,
		RegisteredClaims: jwt.RegisteredClaims{
			ID:        jwtID,
			ExpiresAt: jwt.NewNumericDate(time.Now().Add(duration)),
			IssuedAt:  jwt.NewNumericDate(time.Now()),
		},
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	return token.SignedString(jwtSecret)
}

// ValidateToken válida el JWT y devuelve los claims si es válido.
func ValidateToken(tokenString string) (*Claims, error) {
	token, err := jwt.ParseWithClaims(tokenString, &Claims{}, func(token *jwt.Token) (interface{}, error) {
		return jwtSecret, nil
	})

	if err != nil {
		return nil, err
	}

	claims, ok := token.Claims.(*Claims)
	if !ok || !token.Valid {
		return nil, jwt.ErrSignatureInvalid
	}

	return claims, nil
}
