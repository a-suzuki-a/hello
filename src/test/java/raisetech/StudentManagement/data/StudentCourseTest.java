package raisetech.StudentManagement.data;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentCourseTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
    @Test
    void 正しい入力ならエラーにならないこと(){
        StudentCourse course = new StudentCourse();
        course.setStudentId(1);
        course.setCourse("Javaコース");
        course.setStartDate(LocalDate.of(2026,10,1));

        Set<ConstraintViolation<StudentCourse>>violations=validator.validate(course);
        assertTrue(violations.isEmpty());
    }
    @Test
    void コース名が空欄にならないこと(){
        StudentCourse course = new StudentCourse();
        course.setCourse("");
        Set<ConstraintViolation<StudentCourse>>violations=validator.validateProperty(course,"course");
        assertEquals(1,violations.size());
        assertEquals("コース名を入力してください",violations.iterator().next().getMessage());
    }
    @Test
    void 受講生IDが未設定ならエラーになること(){
        StudentCourse course = new StudentCourse();

        Set<ConstraintViolation<StudentCourse>>violations=validator.validateProperty(course,"studentId");
        assertEquals(1,violations.size());
        assertEquals("受講生IDを入力してください",violations.iterator().next().getMessage());
    }

}