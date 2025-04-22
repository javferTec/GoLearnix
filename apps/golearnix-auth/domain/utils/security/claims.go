package security

import "github.com/golang-jwt/jwt/v4"

// Claims define los datos personalizados del token JWT.
type Claims struct {
	Sub  string `json:"sub"`  // ID del usuario
	Role string `json:"role"` // Rol del usuario
	jwt.RegisteredClaims
}
