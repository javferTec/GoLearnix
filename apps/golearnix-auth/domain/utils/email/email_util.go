package email

import (
	"regexp"
)

// IsValidEmail válida si el correo electrónico tiene un formato correcto
func IsValidEmail(email string) bool {
	// Expresión regular simple para validar correos electrónicos
	re := regexp.MustCompile(`^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$`)
	return re.MatchString(email)
}
