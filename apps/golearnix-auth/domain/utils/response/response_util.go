package response

import (
	"github.com/gofiber/fiber/v2"
)

func SuccessResponse(c *fiber.Ctx, status int, message string, data interface{}) error {
	return c.Status(status).JSON(SuccessMessage{Message: message, Data: data})
}

func ErrorResponse(c *fiber.Ctx, status int, message string) error {
	return c.Status(status).JSON(ErrorMessage{Message: message})
}
