package middlewares

import (
	"github.com/gofiber/fiber/v2"
	"golearnix-auth/domain/repositories"
	"golearnix-auth/domain/utils/response"
	"golearnix-auth/domain/utils/security"
	"strings"
)

func AuthMiddleware(sessionRepo repositories.SessionRepository) fiber.Handler {
	return func(c *fiber.Ctx) error {
		auth := c.Get("Authorization")
		if auth == "" {
			return response.Error(c, fiber.StatusUnauthorized, "Token no proporcionado")
		}
		const prefix = "Bearer "
		if !strings.HasPrefix(auth, prefix) {
			return response.Error(c, fiber.StatusUnauthorized, "Formato de token inválido")
		}
		token := strings.TrimPrefix(auth, prefix)
		claims, err := security.ValidateToken(token)
		if err != nil {
			return response.Error(c, fiber.StatusUnauthorized, "Token inválido o expirado")
		}
		sess, err := sessionRepo.GetSessionByJwtID(claims.ID)
		if err != nil || sess == nil {
			return response.Error(c, fiber.StatusUnauthorized, "Sesión inválida o terminada")
		}
		c.Locals("userID", claims.Sub)
		c.Locals("jwtID", claims.ID)
		return c.Next()
	}
}
