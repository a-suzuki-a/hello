package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter

public class Student {

    private  Integer id;
    @NotBlank(message = "名前を入力してください")
    private  String name;
    @NotBlank(message = "カナ名を入力してください")
    private  String kana;
    @NotBlank
    private  String nickname;
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式が正しくありません")
    private  String mailaddress;
    private  String tiiki;
    private  int age;
    private  String gender;
    private  String remark;
    private  boolean isDeleted;

    public boolean isDeleted(){
        return isDeleted;
    }
    public void setDeleted(boolean deleted){
        this.isDeleted = deleted;
    }
}
