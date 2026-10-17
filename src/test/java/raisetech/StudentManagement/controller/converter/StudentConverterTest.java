package raisetech.StudentManagement.controller.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentConverterTest {

private StudentConverter sut;

@BeforeEach
    void before() {
    sut = new StudentConverter();
}
@Test
    void 受講生リストと受講生コース情報のリストを渡して受講生詳細リストができること(){
    //受講生作成
    Student student = new Student();
    student.setId("1");
    student.setName("山田太郎");
    //受講生コース情報作成
    StudentCourse course = new StudentCourse();
    course.setStudentId(1);
    course.setCourse("Javaコース");

    List<Student>studentList=List.of(student);
    List<StudentCourse>studentCourseList=List.of(course);

    //テスト対象のメソッド実行
    List<StudentDetail>actual=sut.convertStudentDetails(studentList,studentCourseList);
    //変換結果の検証
    assertEquals(1,actual.size());
    assertSame(student,actual.get(0).getStudent());
    assertEquals(1,actual.get(0).getStudentCourseList().size());
    assertSame(course,actual.get(0).getStudentCourseList().get(0));

}

}
