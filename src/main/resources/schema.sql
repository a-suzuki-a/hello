CREATE TABLE students (
                          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) DEFAULT NULL,
                          kana VARCHAR(100) DEFAULT NULL,
                          nickname VARCHAR(100) DEFAULT NULL,
                          mailaddress VARCHAR(100) DEFAULT NULL,
                          tiiki VARCHAR(100) DEFAULT NULL,
                          age INT DEFAULT NULL,
                          gender VARCHAR(100) DEFAULT NULL,
                          remark VARCHAR(255) DEFAULT NULL,
                          isDeleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE students_courses (
                                  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                  student_id INT DEFAULT NULL,
                                  course VARCHAR(100) DEFAULT NULL,
                                  start_date DATE DEFAULT NULL,
                                  schedule_end_date DATE DEFAULT NULL
);