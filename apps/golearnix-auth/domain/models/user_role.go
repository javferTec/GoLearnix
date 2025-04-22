package models

type UserRole string

// UserRole representa el rol de un usuario
const (
	Admin      UserRole = "admin"
	Instructor UserRole = "instructor"
	Student    UserRole = "student"
)
