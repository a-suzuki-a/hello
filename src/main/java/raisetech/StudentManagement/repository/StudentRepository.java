package raisetech.StudentManagement.repository;

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

    /**
     * 受講生の全件検索を行います。
     *
     * @return　受講生一覧（全件）
     */
    List<Student> search();

    /**
     * 受講生のコース情報の全件検索を行います。
     *
     * @return　受講生のコース情報（全件）
     */
    List<StudentCourse> searchStudentCourseList();

    /**
     * 検索条件を指定して受講生を検索します。
     * @param name　受講生の名前
     * @param tiiki　受講生の地域
     * @return 条件に一致する受講生一覧
     */
    List<Student> searchByCondition(
            @Param("id")String id,
            @Param("name")String name,
            @Param("kana")String kana,
            @Param("nickname")String nickname,
            @Param("mailaddress")String mailaddress,
            @Param("tiiki")String tiiki,
            @Param("age")Integer age,
            @Param("gender")String gender,
            @Param("remark")String remark,
            @Param("course")String course);

    /**
     * 受講生を新規登録します。
     * IDに関しては自動採番を行う。
     *
     * @param student　受講生
     */

    void registerStudent(Student student);

    /**
     * 受講生コース情報を新規登録します。
     * IDに関しては自動採番を行う。
     *
     * @param studentCourse　受講生コース情報
     */

    void registerStudentCourse(StudentCourse studentCourse);

    /**
     * 受講生の検索を行います。
     *
     * @param id　受講生ID
     * @return　受講生
     */
     Student searchStudent(String id);

    /**
     * 受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param studentid　受講生ID
     * @return　受講生IDに紐づく受講生コース情報
     */
    List<StudentCourse> searchStudentCourseByStudentId(String studentid);

    /**
     * 受講生を更新します。
     *
      * @param student　受講生
     */

    void updateStudent(Student student);

    /**
     * 受講コース情報のコース名を更新します。
     *
     * @param studentCourse　受講生コース情報
     */

    void updateStudentCourse(StudentCourse studentCourse);


}
