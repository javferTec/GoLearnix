package middlewares

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/repositories"
	utils2 "golearnix-auth/domain/utils/response"
	"golearnix-auth/domain/utils/security"
	"strings"
)

// AuthMiddleware verifica el token JWT en el encabezado Authorization y valida si la sesión está activa.
func AuthMiddleware(sessionRepo repositories.SessionRepository) fiber.Handler {
	return func(c *fiber.Ctx) error {
		authHeader := c.Get("Authorization")
		if authHeader == "" {
			return utils2.ErrorResponse(c, fiber.StatusUnauthorized, "Token no proporcionado")
		}

		const prefix = "Bearer "
		if !strings.HasPrefix(authHeader, prefix) {
			return utils2.ErrorResponse(c, fiber.StatusUnauthorized, "Formato de token no válido")
		}

		tokenString := strings.TrimPrefix(authHeader, prefix)
		claims, err := security.ValidateToken(tokenString)
		if err != nil {
			return utils2.ErrorResponse(c, fiber.StatusUnauthorized, "Token inválido o expirado")
		}

		// Verificamos si la sesión está activa en la base de datos
		session, err := sessionRepo.GetSessionByJwtID(claims.ID) // Verifica si la sesión existe
		if err != nil || session == nil {
			return utils2.ErrorResponse(c, fiber.StatusUnauthorized, "Sesión inválida o terminada")
		}

		// Si la sesión es válida, pasamos la información al contexto
		c.Locals("userID", claims.Sub)
		c.Locals("jwtID", claims.ID)

		return c.Next()
	}
}
