package raisetech.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

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
    public void registerStudent(StudentDetail studentDetail){
        Student student = studentDetail.getStudent();
        student.setId(UUID.randomUUID().toString());
        repository.registerStudent(student);

        for (StudentCourses course : studentDetail.getStudentCourses()){
            course.setId(UUID.randomUUID().toString());
            course.setStudentId(student.getId());

            repository.registerStudentCourse(course);
        }

    }
}
