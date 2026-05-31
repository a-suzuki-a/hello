package raisetech.StudentManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@MapperScan("raisetech.StudentManagement")

@SpringBootApplication
@RestController
public class StudentManagementApplication {

    @Autowired
    private StudentRepository repository;

    public static void main(String[] args) {

        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @GetMapping("/student")
    public String getStudent(@RequestParam String name) {
        Student student = repository.searchByName(name);

        return student.getName() + "  " + student.getAge() + "歳";
    }

    @PostMapping("/student")
    public void registerStudent(String name, int age) {
        repository.registerStudent(name, age);
    }

    @PatchMapping("/student")
    public void updateStudentName(String name, int age) {
        repository.updateStudent(name, age);
    }

    @DeleteMapping("/student")
    public void deleteStudent(String name) {
        repository.deleteStudent(name);
    }
}
