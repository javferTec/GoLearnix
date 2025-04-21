package response

import "github.com/gofiber/fiber/v2"

// Response es la forma genérica de todas las respuestas.
type Response struct {
	Success bool        `json:"success"`
	Message string      `json:"message"`
	Data    interface{} `json:"data,omitempty"`
}

// Send es la función interna que envía la respuesta.
func Send(c *fiber.Ctx, status int, success bool, message string, data interface{}) error {
	return c.Status(status).JSON(Response{
		Success: success,
		Message: message,
		Data:    data,
	})
}

// Success shortcut para respuestas 2xx
func Success(c *fiber.Ctx, status int, message string, data interface{}) error {
	return Send(c, status, true, message, data)
}

// Error shortcut para respuestas de error
func Error(c *fiber.Ctx, status int, message string) error {
	return Send(c, status, false, message, nil)
}
