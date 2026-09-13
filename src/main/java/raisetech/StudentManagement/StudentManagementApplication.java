package raisetech.StudentManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("raisetech.StudentManagement")

@SpringBootApplication

public class StudentManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudentManagementApplication.class, args);
    }
}