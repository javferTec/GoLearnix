-- Conectar a course_db
-- \c course_db

-- Insertar categorías
INSERT INTO course_categories (id, name, description, created_at) VALUES
  ('f1e2d3c4-b5a6-7c8d-9e0f-1a2b3c4d5e6f', 'Programación', 'Cursos de desarrollo de software.', now()),
  ('0a1b2c3d-4e5f-6a7b-8c9d-0e1f2a3b4c5d', 'Diseño',       'Cursos de diseño gráfico y UX/UI.',    now());

-- Insertar cursos
INSERT INTO courses (id, title, description, category_id, instructor_id, created_at, updated_at) VALUES
  ('1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', 'Master en Go',             'Aprende Go de cero a experto.',           'f1e2d3c4-b5a6-7c8d-9e0f-1a2b3c4d5e6f', 'e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', now(), now()),
  ('2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', 'Introducción a Diseño UX', 'Fundamentos de UX y experiencia de usuario.', '0a1b2c3d-4e5f-6a7b-8c9d-0e1f2a3b4c5d', 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', now(), now());

-- Insertar secciones
INSERT INTO sections (id, course_id, title, "order", created_at) VALUES
  ('3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d', '1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', 'Fundamentos de Go',       1, now()),
  ('4c5d6e7f-8a9b-0c1d-2e3f-4a5b6c7d8e9f', '1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', 'Buenas prácticas con Go', 2, now()),
  ('5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', '2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', 'Principios de UX',        1, now()),
  ('6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c', '2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', 'Herramientas de Prototipado', 2, now());

-- Insertar lecciones
INSERT INTO lessons (id, section_id, title, content, "order", created_at) VALUES
  ('7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', '3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d', 'Sintaxis Básica',          'Introducción a la sintaxis de Go.', 1, now()),
  ('8b9c0d1e-2f3a-4b5c-6d7e-8f9a0b1c2d3e', '3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d', 'Tipos y Variables',        'Trabajo con tipos y variables en Go.', 2, now()),
  ('9c0d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f', '4c5d6e7f-8a9b-0c1d-2e3f-4a5b6c7d8e9f', 'Gestión de Errores',       'Manejo de errores y panic/recover.', 1, now()),
  ('0d1e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a', '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', 'Introducción a UX',        'Principios básicos de UX.', 1, now()),
  ('1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', 'Investigación de Usuarios','Técnicas de investigación.', 2, now()),
  ('2f3a4b5c-6d7e-8f9a-0b1c-2d3e4f5a6b7c', '6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c', 'Sketch',                   'Diseño de prototipos con Sketch.', 1, now());

-- Insertar inscripciones
INSERT INTO enrollments (id, course_id, user_id, enrolled_at) VALUES
  ('3f4a5b6c-7d8e-9f0a-1b2c-3d4e5f6a7b8c', '1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  ('4a5b6c7d-8e9f-0a1b-2c3d-4e5f6a7b8c9d', '1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', now()),
  ('5b6c7d8e-9f0a-1b2c-3d4e-5f6a7b8c9d0e', '2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  ('6c7d8e9f-0a1b-2c3d-4e5f-6a7b8c9d0e1f', '2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', now());

-- Insertar progreso
INSERT INTO progress (id, lesson_id, user_id, completed, updated_at) VALUES
  ('7d8e9f0a-1b2c-3d4e-5f6a-7b8c9d0e1f2a', '7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true,  now()),
  ('8e9f0a1b-2c3d-4e5f-6a7b-8c9d0e1f2a3b', '8b9c0d1e-2f3a-4b5c-6d7e-8f9a0b1c2d3e', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true,  now()),
  ('9f0a1b2c-3d4e-5f6a-7b8c-9d0e1f2a3b4c', '7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', false, now()),
  ('0a1b2c3d-4e5f-6a7b-8c9d-0e1f2a3b4c5d', '0d1e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a', '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', true,  now());

-- Insertar reseñas
INSERT INTO reviews (id, course_id, user_id, rating, comment, created_at) VALUES
  ('1b2c3d4e-5f6a-7b8c-9d0e-1f2a3b4c5d6e', '1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, '¡Excelente curso de Go!',                  now()),
  ('2c3d4e5f-6a7b-8c9d-0e1f-2a3b4c5d6e7f', '1f2e3d4c-5b6a-7c8d-9e0f-1a2b3c4d5e6f', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 4, 'Muy bueno, me ayudó mucho.',               now()),
  ('3d4e5f6a-7b8c-9d0e-1f2a-3b4c5d6e7f8a', '2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, 'Gran introducción a UX.',                 now()),
  ('4e5f6a7b-8c9d-0e1f-2a3b-4c5d6e7f8a9b', '2d3c4b5a-6e7f-8a9b-0c1d-2e3f4a5b6c7d', '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', 3, 'Contenido interesante pero introductorio.', now());
