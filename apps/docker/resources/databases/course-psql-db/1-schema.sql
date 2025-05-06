-- Tabla de usuarios
CREATE TABLE users (
  id UUID PRIMARY KEY
);

-- Tabla de categorías de cursos
CREATE TABLE course_categories (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  description TEXT,
  created_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de cursos
CREATE TABLE courses (
  id            SERIAL PRIMARY KEY,
  title         VARCHAR(200) NOT NULL,
  description   TEXT NOT NULL,
  category_id   INT REFERENCES course_categories(id),
  instructor_id UUID REFERENCES users(id) NOT NULL,
  created_at    TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at    TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de secciones
CREATE TABLE sections (
  id         SERIAL PRIMARY KEY,
  course_id  INT REFERENCES courses(id) ON DELETE CASCADE,
  title      VARCHAR(200) NOT NULL,
  "order"    INTEGER NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de lecciones
CREATE TABLE lessons (
  id         SERIAL PRIMARY KEY,
  section_id INT REFERENCES sections(id) ON DELETE CASCADE,
  title      VARCHAR(200) NOT NULL,
  content    TEXT NOT NULL,
  "order"    INTEGER NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de inscripciones
CREATE TABLE enrollments (
  id          SERIAL PRIMARY KEY,
  course_id   INT REFERENCES courses(id) ON DELETE CASCADE,
  user_id     UUID REFERENCES users(id) NOT NULL,
  enrolled_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de progreso del usuario
CREATE TABLE progress (
  id         SERIAL PRIMARY KEY,
  lesson_id  INT REFERENCES lessons(id) ON DELETE CASCADE,
  user_id    UUID REFERENCES users(id) NOT NULL,
  completed  BOOLEAN DEFAULT false,
  completed_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Tabla de reseñas
CREATE TABLE reviews (
  id         SERIAL PRIMARY KEY,
  course_id  INT REFERENCES courses(id) ON DELETE CASCADE,
  user_id    UUID REFERENCES users(id) NOT NULL,
  rating     INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
  comment    TEXT,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

