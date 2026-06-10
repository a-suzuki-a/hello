package raisetech.StudentManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MapperScan("raisetech.StudentManagement")

@SpringBootApplication
@RestController
public class StudentManagementApplication {

    @Autowired
    private StudentRepository repository;

    public static void main(String[] args) {

        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @GetMapping("/studentList")
    public List<StudentCourses> getStudentList() {
        return repository.search();
    }
}