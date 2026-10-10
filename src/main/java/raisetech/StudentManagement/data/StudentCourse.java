package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

    private Integer id;
    @NotNull (message = "受講生IDを入力してください")
    private Integer studentId;
    @NotBlank (message = "コース名を入力してください")
    private String course;
    @NotNull (message = "開始日を入力してください")
    private LocalDate startDate;
    private LocalDate scheduleEndDate;
}

