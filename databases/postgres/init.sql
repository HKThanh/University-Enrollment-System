CREATE DATABASE auth_db;
CREATE DATABASE enroll_db;
CREATE DATABASE faculty_db;

-- Grant privileges to the postgres user
ALTER DATABASE auth_db OWNER TO postgres;
ALTER DATABASE enroll_db OWNER TO postgres;
ALTER DATABASE faculty_db OWNER TO postgres;

\c auth_db;

-- Thiết lập các thông số tương tự
SET TIME ZONE 'UTC';

-- Bảng roles
DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  description VARCHAR(150) NOT NULL,
  name VARCHAR(40) NOT NULL UNIQUE
);

INSERT INTO roles (id, description, name) VALUES
(1, 'Students who are currently studies at the university', 'ROLE_STUDENT'),
(2, 'Students who are completed studies at the university', 'ROLE_GRADUATE');

-- Bảng student
DROP TABLE IF EXISTS student CASCADE;
CREATE TABLE student (
  id VARCHAR(255) PRIMARY KEY,
  full_name VARCHAR(45) NOT NULL,
  password VARCHAR(64) NOT NULL,
  photos VARCHAR(64) DEFAULT NULL
);

INSERT INTO student (id, full_name, password, photos) VALUES
('21086061', 'Lê Minh Thật', '$2a$10$d.kRHxGIsH1aULbZg8g85Oj/1pfyDGy6YJ8RL8wdWu4XiwdX1PySO', 'https://source.unsplash.com/random'), 
('21086351', 'Huỳnh Kim Thành', '$2a$10$d.kRHxGIsH1aULbZg8g85Oj/1pfyDGy6YJ8RL8wdWu4XiwdX1PySO', 'https://source.unsplash.com/random'); 

-- Bảng students_roles
DROP TABLE IF EXISTS students_roles CASCADE;
CREATE TABLE students_roles (
  student_id VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (student_id, role_id),
  FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

INSERT INTO students_roles (student_id, role_id) VALUES
('21086061', 1), 
('21086351', 1);

\c faculty_db;

-- Bảng faculties
DROP TABLE IF EXISTS faculties CASCADE;
CREATE TABLE faculties (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL
);

INSERT INTO faculties (id, name) VALUES
(1, 'Khoa Công nghệ Thông tin');

-- Bảng majors
DROP TABLE IF EXISTS majors CASCADE;
CREATE TABLE majors (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL,
  faculty_id INT DEFAULT NULL,
  CONSTRAINT fk_majors_faculty FOREIGN KEY (faculty_id) REFERENCES faculties(id) ON DELETE SET NULL
);

INSERT INTO majors (id, name, faculty_id) VALUES
(1, 'Kỹ thuật phần mềm', 1),
(2, 'Khoa học máy tính', 1),
(3, 'Khoa học dữ liệu', 1),
(4, 'Hệ thống thông tin', 1),
(5, 'Công nghệ thông tin', 1);

-- Bảng major_course_year
DROP TABLE IF EXISTS major_course_year CASCADE;
CREATE TABLE major_course_year (
  academic_year INT NOT NULL,
  major_id INT NOT NULL,
  course_id VARCHAR(255) NOT NULL,
  semester INT NOT NULL,
  type SMALLINT CHECK (type BETWEEN 0 AND 1),
  PRIMARY KEY (academic_year, course_id, major_id),
  CONSTRAINT fk_major_course_year_major FOREIGN KEY (major_id) REFERENCES majors(id) ON DELETE CASCADE
);

INSERT INTO major_course_year (academic_year, major_id, course_id, semester, type) VALUES
(2021, 1, '4203002009', 1, 1),
(2021, 1, '4203003192', 1, 1),
(2021, 1, '4203003242', 1, 1),
(2021, 1, '4203003259', 1, 1),
(2021, 1, '4203003307', 1, 1),
(2021, 1, '4203003848', 1, 1),
(2021, 1, '4203014164', 1, 1);

-- Bảng students
DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL,
  major_id INT DEFAULT NULL,
  year INT NOT NULL,
  CONSTRAINT fk_students_major FOREIGN KEY (major_id) REFERENCES majors(id) ON DELETE SET NULL
);

INSERT INTO students (id, name, major_id, year) VALUES
('21086061', 'Lê Minh Thật', 1, 2021), 
('21086351', 'Huỳnh Kim Thành', 1, 2021); 

-- Bảng major_semester_summary
DROP TABLE IF EXISTS major_semester_summary CASCADE;
CREATE TABLE major_semester_summary (
  major_id INT NOT NULL,
  semester INT NOT NULL,
  year INT NOT NULL,
  total_elective_credits INT NOT NULL,
  total_mandatory_credits INT NOT NULL,
  PRIMARY KEY (major_id, semester, year),
  CONSTRAINT fk_major_semester_major FOREIGN KEY (major_id) REFERENCES majors(id) ON DELETE CASCADE
);

INSERT INTO major_semester_summary (major_id, semester, year, total_elective_credits, total_mandatory_credits) VALUES
(1, 1, 2021, 0, 11),
(1, 2, 2021, 3, 12),
(1, 3, 2021, 3, 16),
(1, 4, 2021, 7, 12),
(1, 5, 2021, 3, 13),
(1, 6, 2021, 6, 12),
(1, 7, 2021, 4, 11),
(1, 8, 2021, 6, 9),
(1, 9, 2021, 0, 13);
