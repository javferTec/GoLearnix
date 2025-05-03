-- Insertar usuarios
INSERT INTO users (id) VALUES
  ('e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b'),
  ('a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e'),
  ('0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d'),
  ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d'),
  ('9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b');

-- Insertar categorías (sin ID explícito)
INSERT INTO course_categories (name, description, created_at) VALUES
  ('Programación', 'Cursos de desarrollo de software.', now()),
  ('Diseño', 'Cursos de diseño gráfico y UX/UI.', now());

-- Insertar cursos
INSERT INTO courses (title, description, category_id, instructor_id, created_at, updated_at) VALUES
  ('Master en Go', 'Aprende Go de cero a experto.', 1, 'e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', now(), now()),
  ('Introducción a Diseño UX', 'Fundamentos de UX y experiencia de usuario.', 2, 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', now(), now());

-- Insertar secciones
INSERT INTO sections (course_id, title, "order", created_at) VALUES
  (1, 'Fundamentos de Go', 1, now()),
  (1, 'Buenas prácticas con Go', 2, now()),
  (2, 'Principios de UX', 1, now()),
  (2, 'Herramientas de Prototipado', 2, now());

-- Insertar lecciones
INSERT INTO lessons (section_id, title, content, "order", created_at) VALUES
  (1, 'Sintaxis Básica', 'Introducción a la sintaxis de Go.', 1, now()),
  (1, 'Tipos y Variables', 'Trabajo con tipos y variables en Go.', 2, now()),
  (2, 'Gestión de Errores', 'Manejo de errores y panic/recover.', 1, now()),
  (3, 'Introducción a UX', 'Principios básicos de UX.', 1, now()),
  (3, 'Investigación de Usuarios', 'Técnicas de investigación.', 2, now()),
  (4, 'Sketch', 'Diseño de prototipos con Sketch.', 1, now());

-- Insertar inscripciones
INSERT INTO enrollments (course_id, user_id, enrolled_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (2, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', now());

-- Insertar progreso
INSERT INTO progress (lesson_id, user_id, completed, updated_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', false, now()),
  (4, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', true, now());

-- Insertar reseñas
INSERT INTO reviews (course_id, user_id, rating, comment, created_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, '¡Excelente curso de Go!', now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 4, 'Muy bueno, me ayudó mucho.', now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, 'Gran introducción a UX.', now()),
  (2, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', 3, 'Contenido interesante pero introductorio.', now());
