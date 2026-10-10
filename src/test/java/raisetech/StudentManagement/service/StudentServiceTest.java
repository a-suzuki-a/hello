package raisetech.StudentManagement.service;

import org.apache.commons.lang3.builder.EqualsExclude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    private StudentService sut;


    @BeforeEach
    void setUp() {
        sut = new StudentService(repository, converter);
    }

    @Test
    void 受講生詳細の一覧検索_全件検索が動作すること_リポジトリとコンバーターの処理が適切に呼び出せていること() {
        //事前準備
        List<Student> studentList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();

        when(repository.search()).thenReturn(studentList);
        when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

        //実行
        sut.searchStudentList();
        //検証
        verify(repository, times(1)).search();
        verify(repository, times(1)).searchStudentCourseList();
        verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
        //後処理
    }

    @Test
    void 受講生詳細の検索() {
        String id = "1";
        Student student = new Student();
        List<StudentCourse> studentCourseList = new ArrayList<>();

        when(repository.searchStudent(id)).thenReturn(student);
        when(repository.searchStudentCourseByStudentId(id)).thenReturn(studentCourseList);


        StudentDetail actual = sut.searchStudent(id);

        verify(repository, times(1)).searchStudent(id);
        verify(repository, times(1)).searchStudentCourseByStudentId(id);

        Assertions.assertEquals(student, actual.getStudent());
        Assertions.assertEquals(studentCourseList, actual.getStudentCourseList());
    }


    @Test
    void 受講生詳細の登録_受講生とコース情報が登録されること() {
        Student student = new Student();
        StudentCourse studentCourse = new StudentCourse();
        StudentDetail studentDetail = new StudentDetail(student, List.of(studentCourse));


        StudentDetail actual = sut.registerStudent(studentDetail);

        verify(repository, times(1)).registerStudent(student);
        verify(repository, times(1)).registerStudentCourse(studentCourse);

        Assertions.assertEquals(studentDetail, actual);
    }

    @Test
    void 受講生詳細の更新_受講生とコース情報が更新されること() {
        Student student = new Student();
        StudentCourse studentCourse = new StudentCourse();
        StudentDetail studentDetail = new StudentDetail(student, List.of(studentCourse));

        sut.updateStudent(studentDetail);

        verify(repository, times(1)).updateStudent(student);
        verify(repository, times(1)).updateStudentCourse(studentCourse);
    }

    @Test
    void 受講コース情報の初期設定が正しく行われることされること() {
        Student student = new Student();
        student.setId("1");

        StudentCourse studentCourse = new StudentCourse();

        LocalDate now = LocalDate.now();

        sut.initStudentCourses(studentCourse, student);

        Assertions.assertEquals(1, studentCourse.getStudentId());
        Assertions.assertEquals(now, studentCourse.getStartDate());
        Assertions.assertEquals(now.plusYears(1), studentCourse.getScheduleEndDate());
    }
}

