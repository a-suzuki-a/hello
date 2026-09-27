package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

    private Integer id;
    private Integer studentId;
    private String course;
    private LocalDate startDate;
    private LocalDate scheduleEndDate;
}

