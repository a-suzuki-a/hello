package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

    private  String id;
    private  String name;
    private  String kana;
    private  String nickname;
    private  String mailaddress;
    private  String tiiki;
    private  int age;
    private  String gender;
    private  String remark;
    private  boolean isDeleted;
}
