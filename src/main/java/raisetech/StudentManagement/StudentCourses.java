package raisetech.StudentManagement;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class StudentCourses {

    private String id;
    private String studentId;
    private String course;
    private LocalDate startDate;
    private LocalDate scheduleEndDate;
}

