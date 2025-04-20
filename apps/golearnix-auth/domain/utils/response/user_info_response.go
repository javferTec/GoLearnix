package response

// UserInfoResponse representa la respuesta con información del usuario.
type UserInfoResponse struct {
	Message string      `json:"message"`
	User    interface{} `json:"user"`
}
