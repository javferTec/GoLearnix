package input

import "golearnix-auth/internal/domain"

// AuthService define la interfaz que implementa la lógica de autenticación.
type AuthService interface {
	Register(name, email, password string, role domain.UserRole) (string, error) // Devuelve el token JWT y el error
	Login(email, password string) (string, error)                                // Devuelve el token JWT y el error
	Logout(jwtID string) error                                                   // Realiza el logout eliminando la sesión
	ValidateToken(token string) (*domain.Session, error)                         // Valida el token JWT y devuelve la sesión
}
