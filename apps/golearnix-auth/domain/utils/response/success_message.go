package response

type SuccessMessage struct {
	Message   string      `json:"message,omitempty"`
	Token     string      `json:"token,omitempty"`
	Email     string      `json:"email,omitempty"`
	ExpiresIn string      `json:"expires_in,omitempty"`
	Data      interface{} `json:"data,omitempty"`
}
