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
  ('Design', 'Graphic design and UX/UI courses.', now()),
  ('Marketing', 'Digital and traditional marketing courses.', now()),
  ('Business', 'Courses about business and management.', now());

-- Insert courses
INSERT INTO courses (title, description, category_id, instructor_id, created_at, updated_at) VALUES
  ('Go Masterclass', 'Learn Go from beginner to expert.', 1, 'e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', now(), now()),
  ('Introduction to UX Design', 'Fundamentals of UX and user experience.', 2, 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', now(), now()),
  ('Digital Marketing 101', 'Learn the basics of digital marketing.', 3, 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', now(), now()),
  ('Startup Fundamentals', 'Build and manage a startup business.', 4, 'e7b9a1f2-3c4d-4e5f-9a2b-7c8d9e0f1a2b', now(), now());

-- Insert sections
INSERT INTO sections (course_id, title, "order", created_at) VALUES
  (1, 'Go Fundamentals', 1, now()),
  (1, 'Go Best Practices', 2, now()),
  (2, 'UX Principles', 1, now()),
  (2, 'Prototyping Tools', 2, now()),
  (3, 'SEO Basics', 1, now()),
  (3, 'Social Media Marketing', 2, now()),
  (4, 'Startup Lifecycle', 1, now()),
  (4, 'Business Models', 2, now());

-- Insert lessons
INSERT INTO lessons (section_id, title, content, "order", created_at) VALUES
  (1, 'Basic Syntax', 'Introduction to Go syntax.', 1, now()),
  (1, 'Types and Variables', 'Working with types and variables in Go.', 2, now()),
  (2, 'Error Handling', 'Handling errors and panic/recover.', 1, now()),
  (3, 'Introduction to UX', 'Basic principles of UX.', 1, now()),
  (3, 'User Research', 'Research techniques.', 2, now()),
  (4, 'Sketch', 'Prototyping with Sketch.', 1, now()),
  (5, 'What is SEO?', 'Intro to Search Engine Optimization.', 1, now()),
  (5, 'Keyword Research', 'Finding keywords for SEO.', 2, now()),
  (6, 'Social Platforms', 'Using Facebook, Instagram, and Twitter.', 1, now()),
  (7, 'Startup Phases', 'From idea to scaling.', 1, now()),
  (8, 'Revenue Streams', 'How startups make money.', 1, now());

-- Insert enrollments
INSERT INTO enrollments (course_id, user_id, enrolled_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (2, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', now()),
  (3, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', now()),
  (3, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', now()),
  (4, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', now()),
  (4, 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', now());

-- Insert progress
INSERT INTO progress (lesson_id, user_id, completed, completed_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', false, now()),
  (4, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', true, now()),
  (7, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', true, now()),
  (8, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', false, now()),
  (9, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', false, now()),
  (10, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', true, now());

-- Insert reviews
INSERT INTO reviews (course_id, user_id, rating, comment, created_at) VALUES
  (1, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, 'Excellent Go course!', now()),
  (1, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 4, 'Very good, helped me a lot.', now()),
  (2, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 5, 'Great introduction to UX.', now()),
  (2, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', 3, 'Interesting content but very introductory.', now()),
  (3, '0f1e2d3c-4b5a-6978-8c7d-6e5f4a3b2c1d', 4, 'Nice coverage of marketing basics.', now()),
  (3, '9e8f7a6b-5c4d-3e2f-1a0b-9c8d7e6f5a4b', 5, 'Very useful for beginners.', now()),
  (4, '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 5, 'Excellent insights on startups.', now()),
  (4, 'a1d2c3e4-f5b6-7a8c-9d0e-1f2a3b4c5d6e', 3, 'Good content but could use more case studies.', now());
