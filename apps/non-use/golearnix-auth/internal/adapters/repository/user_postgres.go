package repository

import (
	"database/sql"
	"golearnix-auth/internal/domain"
	"golearnix-auth/internal/ports/output"
)

type UserPostgresRepository struct {
	DB *sql.DB
}

func NewUserPostgresRepository(db *sql.DB) output.UserRepository {
	return &UserPostgresRepository{DB: db}
}

func (r *UserPostgresRepository) Save(user *domain.User) error {
	_, err := r.DB.Exec(`
        INSERT INTO users (id, name, email, password_hash, role, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7)
    `, user.ID, user.Name, user.Email, user.PasswordHash, user.Role, user.CreatedAt, user.UpdatedAt)
	return err
}

func (r *UserPostgresRepository) FindByEmail(email string) (*domain.User, error) {
	var user domain.User
	err := r.DB.QueryRow(`
        SELECT id, name, email, password_hash, role, created_at, updated_at
        FROM users WHERE email = $1
    `, email).Scan(
		&user.ID, &user.Name, &user.Email, &user.PasswordHash,
		&user.Role, &user.CreatedAt, &user.UpdatedAt,
	)
	if err != nil {
		return nil, err
	}
	return &user, nil
}

func (r *UserPostgresRepository) FindByID(id string) (*domain.User, error) {
	var user domain.User
	err := r.DB.QueryRow(`
        SELECT id, name, email, password_hash, role, created_at, updated_at
        FROM users WHERE id = $1
    `, id).Scan(
		&user.ID, &user.Name, &user.Email, &user.PasswordHash,
		&user.Role, &user.CreatedAt, &user.UpdatedAt,
	)
	if err != nil {
		return nil, err
	}
	return &user, nil
}
