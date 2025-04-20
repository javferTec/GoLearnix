package handler

import (
	"encoding/json"
	"golearnix-auth/internal/domain"
	"golearnix-auth/internal/ports/input"
	"net/http"
	"strings"
)

type AuthHandler struct {
	Service input.AuthService
}

func NewAuthHandler(svc input.AuthService) *AuthHandler {
	return &AuthHandler{Service: svc}
}

// RegisterRequest representa el cuerpo JSON para /register
type RegisterRequest struct {
	Name     string          `json:"name"`
	Email    string          `json:"email"`
	Password string          `json:"password"`
	Role     domain.UserRole `json:"role"`
}

// LoginRequest representa el cuerpo JSON para /login
type LoginRequest struct {
	Email    string `json:"email"`
	Password string `json:"password"`
}

// Register maneja POST /register
func (h *AuthHandler) Register(w http.ResponseWriter, r *http.Request) {
	var req RegisterRequest
	if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
		http.Error(w, "invalid request body", http.StatusBadRequest)
		return
	}

	token, err := h.Service.Register(req.Name, req.Email, req.Password, req.Role)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	_ = json.NewEncoder(w).Encode(map[string]string{"token": token})
}

// Login maneja POST /login
func (h *AuthHandler) Login(w http.ResponseWriter, r *http.Request) {
	var req LoginRequest
	if err := json.NewDecoder(r.Body).Decode(&req); err != nil {
		http.Error(w, "invalid request body", http.StatusBadRequest)
		return
	}

	token, err := h.Service.Login(req.Email, req.Password)
	if err != nil {
		http.Error(w, "unauthorized", http.StatusUnauthorized)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	_ = json.NewEncoder(w).Encode(map[string]string{"token": token})
}

// Logout maneja POST /logout
func (h *AuthHandler) Logout(w http.ResponseWriter, r *http.Request) {
	auth := r.Header.Get("Authorization")
	token := strings.TrimPrefix(auth, "Bearer ")
	if token == "" {
		http.Error(w, "missing or invalid token", http.StatusBadRequest)
		return
	}

	if err := h.Service.Logout(token); err != nil {
		http.Error(w, "logout failed", http.StatusInternalServerError)
		return
	}

	w.WriteHeader(http.StatusNoContent)
}

// Validate maneja GET /validate
func (h *AuthHandler) Validate(w http.ResponseWriter, r *http.Request) {
	auth := r.Header.Get("Authorization")
	token := strings.TrimPrefix(auth, "Bearer ")
	if token == "" {
		http.Error(w, "missing or invalid token", http.StatusUnauthorized)
		return
	}

	session, err := h.Service.ValidateToken(token)
	if err != nil {
		http.Error(w, "unauthorized", http.StatusUnauthorized)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	_ = json.NewEncoder(w).Encode(session)
}
