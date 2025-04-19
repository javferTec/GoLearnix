-- Crear la base de datos
-- CREATE DATABASE course_db;

-- Conectar a bdase de datos
-- \c course_db

-- Habilitar la extensión para generación de UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabla de categorías de cursos
CREATE TABLE course_categories (
  id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name        VARCHAR(100) NOT NULL,
  description TEXT,
  created_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de cursos
CREATE TABLE courses (
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title         VARCHAR(200) NOT NULL,
  description   TEXT NOT NULL,
  category_id   UUID REFERENCES course_categories(id),
  instructor_id UUID NOT NULL,
  created_at    TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at    TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de secciones
CREATE TABLE sections (
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  course_id  UUID REFERENCES courses(id) ON DELETE CASCADE,
  title      VARCHAR(200) NOT NULL,
  "order"    INTEGER NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de lecciones
CREATE TABLE lessons (
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  section_id UUID REFERENCES sections(id) ON DELETE CASCADE,
  title      VARCHAR(200) NOT NULL,
  content    TEXT NOT NULL,
  "order"    INTEGER NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de inscripciones
CREATE TABLE enrollments (
  id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  course_id   UUID REFERENCES courses(id) ON DELETE CASCADE,
  user_id     UUID NOT NULL,
  enrolled_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de progreso del usuario
CREATE TABLE progress (
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  lesson_id  UUID REFERENCES lessons(id) ON DELETE CASCADE,
  user_id    UUID NOT NULL,
  completed  BOOLEAN DEFAULT false,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de reseñas
CREATE TABLE reviews (
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  course_id  UUID REFERENCES courses(id) ON DELETE CASCADE,
  user_id    UUID NOT NULL,
  rating     INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
  comment    TEXT,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);
