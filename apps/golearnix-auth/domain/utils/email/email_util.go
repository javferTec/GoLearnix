package email

import (
	"regexp"
)

var emailRegex = regexp.MustCompile(`^[\w._%+\-]+@[\w.\-]+\.[A-Za-z]{2,}$`)

// IsValidEmail válida si el correo electrónico tiene un formato correcto
func IsValidEmail(email string) bool {
	return emailRegex.MatchString(email)
}
