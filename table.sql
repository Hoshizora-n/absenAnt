use absen_new;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(64) UNIQUE,
    password VARCHAR(64),
    `role` VARCHAR(64) default 'student'
);

CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    name VARCHAR(64),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE semesters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64)
);

CREATE TABLE study_programs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64)
);

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    name VARCHAR(64),
    nim VARCHAR(64),
    `number` VARCHAR(64),
    semester_id int,
    study_id int,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (semester_id) REFERENCES semesters(id) ON DELETE CASCADE,
    FOREIGN KEY (study_id) REFERENCES study_programs(id) ON DELETE CASCADE
);

CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    name VARCHAR(64),
    nidn VARCHAR(64),
    `number` VARCHAR(64),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

insert into users (username, password, role) values ('admin', 'admin', 'admin'), ('student', 'admin', 'student'), ('teacher', 'admin', 'teacher');

insert into semesters (name) values ('Semester 1 Reguler'), ('Semester 1 Karyawan')

insert into semesters (name) values ('Semester 2 Reguler'), ('Semester 2 Karyawan')

insert into study_programs (name) values ('Informatika'), ('Sistem Informasi'), ('Teknologi Informasi');

CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_id int,
    name VARCHAR(64),
    `time` VARCHAR(64),
    `schedule` VARCHAR(64),
    `day` VARCHAR(15),
    semester_id int,
    study_id int,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    FOREIGN KEY (semester_id) REFERENCES semesters(id) ON DELETE CASCADE,
    FOREIGN KEY (study_id) REFERENCES study_programs(id) ON DELETE CASCADE
);

CREATE TABLE student_courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id int,
    course_id int,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id int,
    course_id int,
    `time` datetime not null,
    meet_no int,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);