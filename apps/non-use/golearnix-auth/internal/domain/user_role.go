package domain

type UserRole string

const (
	Admin      UserRole = "admin"
	Instructor UserRole = "instructor"
	Student    UserRole = "student"
)
