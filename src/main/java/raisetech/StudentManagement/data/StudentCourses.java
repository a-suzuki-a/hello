package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class StudentCourses {

    private Integer id;
    private Integer studentId;
    private String course;
    private LocalDate startDate;
    private LocalDate scheduleEndDate;
}

