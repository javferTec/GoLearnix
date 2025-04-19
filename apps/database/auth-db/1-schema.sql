-- Crear la base de datos
-- CREATE DATABASE auth_db;

-- Conectar a bdase de datos
-- \c auth_db

-- Habilitar la extensión para generar UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Habilitar la extensión para poder usar funciones criptográficas
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Crear el tipo ENUM para los roles de usuario
CREATE TYPE user_role AS ENUM ('admin', 'instructor', 'student');

-- Crear la tabla de usuarios
CREATE TABLE users (
  id             UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name           VARCHAR(100) NOT NULL,
  email          VARCHAR(255) UNIQUE NOT NULL,
  password_hash  TEXT NOT NULL,
  role           user_role NOT NULL DEFAULT 'student',
  created_at     TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at     TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Crear la tabla de sesiones
CREATE TABLE sessions (
  id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id     UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  jwt_id      UUID NOT NULL UNIQUE,
  issued_at   TIMESTAMP WITH TIME ZONE NOT NULL,
  expires_at  TIMESTAMP WITH TIME ZONE NOT NULL
);
