package raisetech.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {

        this.repository = repository;
    }

    public List<Student> searchStudentList() {

        return repository.search();

    }

    public List<StudentCourses> searchStudentsCourseList() {

        return repository.searchStudentsCourses();

    }

    @Transactional
    public void registerStudent(StudentDetail studentDetail) {

        repository.registerStudent(studentDetail.getStudent());

        for (StudentCourses course : studentDetail.getStudentCourses()) {
            course.setStudentId(studentDetail.getStudent().getId());
            course.setStartDate(LocalDate.now());
            course.setScheduleEndDate(LocalDate.now().plusYears(1));
            repository.registerStudentCourse(course);
        }
    }

    public StudentDetail searchStudent(String id) {
        Student student = repository.searchStudent(id);
        List<StudentCourses> studentCourses = repository.searchStudentsCoursesByStudentId(id);

        StudentDetail studentDetail1 = new StudentDetail();
        studentDetail1.setStudent(student);
        studentDetail1.setStudentCourses(studentCourses);
        return studentDetail1;

    }

    @Transactional
    public void updateStudent(StudentDetail studentDetail) {

        repository.updateStudent(studentDetail.getStudent());

        for (StudentCourses course : studentDetail.getStudentCourses()) {
            repository.updateStudentCourse(course);
        }

    }
}

