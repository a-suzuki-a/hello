package raisetech.StudentManagement.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CourseStatusRequest {

    @NotBlank(message = "ステータスを入力してください")
    @Pattern(
            regexp = "仮申込|本申込|受講中|受講終了",
            message = "正しいステータスを指定してください")
    private String status;

    public String getStatus(){
    return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
}
