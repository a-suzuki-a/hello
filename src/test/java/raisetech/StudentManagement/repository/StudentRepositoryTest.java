package raisetech.StudentManagement.repository;


import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@MybatisTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository sut;

    @Test
    void 受講生の全件検索が行われること() {
        List<Student> actual = sut.search();
        assertThat(actual.size()).isEqualTo(5);
    }

    @Test
    void 受講生の登録が行われること() {
        Student student = new Student();

        student.setName("山田太郎");
        student.setKana("ヤマダタロウ");
        student.setNickname("タロ");
        student.setMailaddress("taro@example.com");
        student.setTiiki("東京");
        student.setAge(20);
        student.setGender("male");
        student.setRemark("");
        student.setDeleted(false);

        sut.registerStudent(student);

        List<Student> actual = sut.search();
        assertThat(actual.size()).isEqualTo(6);
    }

    @Test
    void 受講生のコース情報の全件検索が行われること() {
        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual.size()).isEqualTo(5);
    }

    @Test
    void 受講生のコース情報の登録が行われること() {
        StudentCourse course = new StudentCourse();

        course.setStudentId(1);
        course.setCourse("Javaコース");
        course.setStartDate(LocalDate.of(2026, 11, 1));
        course.setScheduleEndDate(LocalDate.of(2027, 10, 31));

        sut.registerStudentCourse(course);

        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual.size()).isEqualTo(6);
    }

    @Test
    void 受講生の検索が行われること() {
        Student actual = sut.searchStudent("1");

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo("1");

    }

    @Test
    void 受講生IDに紐づく受講コースの検索が行われること() {
        List<StudentCourse> actual =
                sut.searchStudentCourseByStudentId("1");

        assertThat(actual).isNotEmpty();

        assertThat(actual)
                .allSatisfy(course ->
                        assertThat(course.getStudentId()).isEqualTo(1));
    }

    @Test
    void 受講生情報の更新が行われること() {
        Student student = sut.searchStudent("1");
//名前の更新
        student.setName("更新テスト太郎");
        sut.updateStudent(student);
        Student actual = sut.searchStudent("1");
        assertThat(actual.getName()).isEqualTo("更新テスト太郎");
    }

    @Test
    void 受講コース情報の更新が行われること() {
        List<StudentCourse> courses = sut.searchStudentCourseByStudentId("1");
        assertThat(courses).isNotEmpty();
        StudentCourse course = courses.get(0);
        course.setCourse("更新テストコース");
        sut.updateStudentCourse(course);
        List<StudentCourse> actual = sut.searchStudentCourseByStudentId("1");
        assertThat(actual.get(0).getCourse()).isEqualTo("更新テストコース");
    }
}