package raisetech.StudentManagement.domain;

import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.List;

@Getter
@Setter
public class StudentDetail {

    private Student student;
    private List<StudentCourses> studentCourses;

}
