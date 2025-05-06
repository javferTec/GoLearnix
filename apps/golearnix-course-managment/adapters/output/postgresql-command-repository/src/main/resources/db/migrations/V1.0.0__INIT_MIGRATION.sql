-- ####################################
-- ############# SCHEMA ###############
-- ####################################

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


-- ####################################
-- ############## DATA ################
-- ####################################

-- Insert users
INSERT INTO users (id) VALUES
  ('e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b'),
  ('a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e'),
  ('0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d'),
  ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d'),
  ('9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b');

-- Insert categories
INSERT INTO course_categories (name, description, created_at) VALUES
  ('Programming', 'Software development courses.', now()),
  ('Design', 'Graphic design and UX/UI courses.', now());

-- Insert courses
INSERT INTO courses (title, description, category_id, instructor_id, created_at, updated_at) VALUES
  ('Go Masterclass', 'Learn Go from beginner to expert.', 1, 'e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', now(), now()),
  ('Introduction to UX Design', 'Fundamentals of UX and user experience.', 2, 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', now(), now());

-- Insert sections
INSERT INTO sections (course_id, title, "order", created_at) VALUES
  (1, 'Go Fundamentals', 1, now()),
  (1, 'Go Best Practices', 2, now()),
  (2, 'UX Principles', 1, now()),
  (2, 'Prototyping Tools', 2, now());

-- Insert lessons
INSERT INTO lessons (section_id, title, content, "order", created_at) VALUES
  (1, 'Basic Syntax', 'Introduction to Go syntax.', 1, now()),
  (1, 'Types and Variables', 'Working with types and variables in Go.', 2, now()),
  (2, 'Error Handling', 'Handling errors and panic/recover.', 1, now()),
  (3, 'Introduction to UX', 'Basic principles of UX.', 1, now()),
  (3, 'User Research', 'Research techniques.', 2, now()),
  (4, 'Sketch', 'Prototyping with Sketch.', 1, now());

-- Insert enrollments
INSERT INTO enrollments (course_id, user_id, enrolled_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (2, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', now());

-- Insert progress
INSERT INTO progress (lesson_id, user_id, completed, completed_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', false, now()),
  (4, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', true, now());

-- Insert reviews
INSERT INTO reviews (course_id, user_id, rating, comment, created_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, 'Excellent Go course!', now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 4, 'Very good, helped me a lot.', now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, 'Great introduction to UX.', now()),
  (2, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', 3, 'Interesting content but very introductory.', now());

