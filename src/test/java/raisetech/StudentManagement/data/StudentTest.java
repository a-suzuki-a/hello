package raisetech.StudentManagement.data;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation
                .buildDefaultValidatorFactory().getValidator();
    }
    @Test
    void 正しい入力ならエラーにならないこと(){
        Student student = new Student();
        student.setId("123");
        student.setName("山田太郎");
        student.setKana("ヤマダタロウ");
        student.setNickname("タロ");
        student.setMailaddress("taro@example.com");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.isEmpty());
    }
    @Test
    void IDが数字以外ならエラーになること(){
        Student student = new Student();
        student.setId("abc");
        Set<ConstraintViolation<Student>> violations = validator.validateProperty(student,"id");
        assertEquals(1,violations.size());
        assertEquals("数字のみを入力してください",violations.iterator().next().getMessage());
    }
    @Test
    void 名前が空欄ならエラーになること(){
        Student student = new Student();
        student.setName("");
        Set<ConstraintViolation<Student>> violations = validator.validateProperty(student,"name");
        assertEquals(1,violations.size());
        assertEquals("名前を入力してください",violations.iterator().next().getMessage());
    }
    @Test
    void メールアドレスの形式が不正ならエラーになること(){
        Student student = new Student();
        student.setMailaddress("abc");
        Set<ConstraintViolation<Student>> violations = validator.validateProperty(student,"mailaddress");
        assertEquals(1,violations.size());
        assertEquals("メールアドレスの形式が正しくありません",violations.iterator().next().getMessage());
    }

}