package security

import (
	"encoding/hex"
	"fmt"
	"github.com/joho/godotenv"
	"log"
	"os"
	"time"

	"github.com/golang-jwt/jwt/v4"
	"github.com/google/uuid"
)

// jwtSecret contiene los bytes decodificados desde el hex en JWT_SECRET
var jwtSecret []byte

func init() {
	if err := godotenv.Load(); err != nil {
		log.Println("⚠️ Advertencia: no se pudo cargar .env en security.init()")
	}

	hexKey := os.Getenv("JWT_SECRET")

	if hexKey == "" {
		log.Fatal("JWT_SECRET no configurado")
	}

	keyBytes, err := hex.DecodeString(hexKey)

	if err != nil {
		log.Fatalf("error al decodificar el JWT_SECRET desde hex: %v", err)
	}

	jwtSecret = keyBytes
}

// GenerateToken genera un JWT para un usuario con duración específica y JTI.
func GenerateToken(userID uuid.UUID, role string, jwtID string, duration time.Duration) (string, error) {
	// Crea los claims del token
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

	// Firmar el token
	signedToken, err := token.SignedString(jwtSecret)
	if err != nil {
		return "", fmt.Errorf("error al firmar el token: %v", err)
	}

	// Retornar el token firmado
	return signedToken, nil
}

// ValidateToken válida el JWT y devuelve los claims si es válido.
func ValidateToken(tokenString string) (*Claims, error) {
	// Parsea el token con los claims definidos
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
