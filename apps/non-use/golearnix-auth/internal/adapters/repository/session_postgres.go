package repository

import (
	"database/sql"
	"golearnix-auth/internal/domain"
	"golearnix-auth/internal/ports/output"
)

type SessionPostgresRepository struct {
	DB *sql.DB
}

func NewSessionPostgresRepository(db *sql.DB) output.SessionRepository {
	return &SessionPostgresRepository{DB: db}
}

func (r *SessionPostgresRepository) Save(session *domain.Session) error {
	_, err := r.DB.Exec(`
        INSERT INTO sessions (id, user_id, jwt_id, issued_at, expires_at)
        VALUES ($1, $2, $3, $4, $5)
    `, session.ID, session.UserID, session.JWTID, session.IssuedAt, session.ExpiresAt)
	return err
}

func (r *SessionPostgresRepository) DeleteByJWTID(jwtID string) error {
	_, err := r.DB.Exec(`DELETE FROM sessions WHERE jwt_id = $1`, jwtID)
	return err
}

func (r *SessionPostgresRepository) GetByJWTID(jwtID string) (*domain.Session, error) {
	var session domain.Session
	err := r.DB.QueryRow(`
        SELECT id, user_id, jwt_id, issued_at, expires_at
        FROM sessions WHERE jwt_id = $1
    `, jwtID).Scan(&session.ID, &session.UserID, &session.JWTID, &session.IssuedAt, &session.ExpiresAt)
	if err != nil {
		return nil, err
	}
	return &session, nil
}
