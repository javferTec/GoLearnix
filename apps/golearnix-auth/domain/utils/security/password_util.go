package security

import "golang.org/x/crypto/bcrypt"

// HashPassword genera un hash seguro de una contraseña.
func HashPassword(password string) (string, error) {
	// Genera un hash de la contraseña usando bcrypt
	bytes, err := bcrypt.GenerateFromPassword([]byte(password), bcrypt.DefaultCost)

	if err != nil {
		return "", err
	}
	return string(bytes), nil
}

// CheckPasswordHash compara una contraseña sin procesar con su hash y devuelve si coinciden.
func CheckPasswordHash(password, hash string) bool {
	// Compara la contraseña sin procesar con el hash
	err := bcrypt.CompareHashAndPassword([]byte(hash), []byte(password))

	return err == nil
}
