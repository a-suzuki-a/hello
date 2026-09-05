package raisetech.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {

    private StudentRepository repository;
    private StudentConverter converter;


    @Autowired
    public StudentService(StudentRepository repository,StudentConverter converter){
        this.repository = repository;
        this.converter = converter;
    }

    /**
     * 受講生一覧です。
     * 全件登録を行うので、条件指定は行いません。
     *
     * @return　受講生一覧（全件）
     */
    public List<StudentDetail> searchStudentList() {
       List<Student> studentList = repository.search();
        List<StudentCourses> studentCoursesList= repository.searchStudentsCourses();
        return converter.convertStudentDetails(studentList,studentCoursesList);
    }
    public List<StudentCourses> searchStudentsCourseList() {
        return repository.searchStudentsCourses();
    }

    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        repository.registerStudent(studentDetail.getStudent());

        for (StudentCourses course : studentDetail.getStudentCourses()) {
            course.setStudentId(studentDetail.getStudent().getId());
            course.setStartDate(LocalDate.now());
            course.setScheduleEndDate(LocalDate.now().plusYears(1));
            repository.registerStudentCourse(course);
        }
        return studentDetail;
    }

    /**
     * 受講生検索です。
     * IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
     *
     * @param id　受講生ID
     * @return　受講生
     */
    public StudentDetail searchStudent(String id) {
        Student student = repository.searchStudent(id);
        List<StudentCourses> studentCourses = repository.searchStudentsCoursesByStudentId(id);
        return new StudentDetail(student,studentCourses);

    }

    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        for (StudentCourses course : studentDetail.getStudentCourses()) {
            repository.updateStudentCourse(course);
        }

    }
}

