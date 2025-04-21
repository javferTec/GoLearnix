-- Conectar a auth_db
-- \c auth_db

-- Insertar usuarios
INSERT INTO users (id, name, email, password_hash, role, created_at, updated_at) VALUES
  ('e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', 'Alice Gómez',    'alice@golearnix.com',    crypt('Passw0rd!', gen_salt('bf')), 'instructor', now(), now()),
  ('a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', 'Bob Martínez',    'bob@golearnix.com',      crypt('Secure123$', gen_salt('bf')), 'instructor', now(), now()),
  ('0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 'Carlos Ruiz',     'carlos@golearnix.com',   crypt('Student1!', gen_salt('bf')), 'student',    now(), now()),
  ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'Diana Fernández', 'diana@golearnix.com',    crypt('MyPass456#', gen_salt('bf')), 'student',    now(), now()),
  ('9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', 'Elena López',     'elena@golearnix.com',    crypt('HelloWorld7', gen_salt('bf')), 'student',    now(), now());

-- Insertar sesiones
 INSERT INTO sessions (id, user_id, jwt_id, issued_at, expires_at) VALUES
  ('3c2b1a0f-9e8d-7c6b-5a4f-3e2d1c0b9a8f', 'e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', '4b3a2c1d-0f9e-8d7c-6b5a-4f3e2d1c0b9a', now(), now() + interval '1 day'),
  ('8f7e6d5c-4b3a-2c1d-0f9e-8d7c6b5a4f3e', '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', '7c6b5a4f-3e2d-1c0b-9a8f-7e6d5c4b3a2f', now(), now() + interval '1 day');
