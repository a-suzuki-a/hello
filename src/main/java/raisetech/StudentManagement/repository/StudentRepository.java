package raisetech.StudentManagement.repository;

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

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
    @Select("SELECT * FROM students ")
    List<Student> search();

    /**
     * 受講生のコース情報の全件検索を行います。
     *
     * @return　受講生のコース情報（全件）
     */
    @Select("SELECT * FROM students_courses")
    List<StudentCourses> searchStudentsCourses();

    @Insert("""
           INSERT INTO students
           (name,kana,nickname,mailaddress,tiiki,age,gender,remark,isDeleted)
           VALUES
           (#{name},#{kana},#{nickname},#{mailaddress},#{tiiki},#{age},#{gender},#{remark},#{isDeleted})
           """)
    @Options(useGeneratedKeys = true,keyProperty = "id")

    void registerStudent(Student student);

    @Insert("""
                 INSERT INTO students_courses
                 (student_id,course,start_date,schedule_end_date)
                 VALUES
                 (#{studentId},#{course},#{startDate},#{scheduleEndDate})
               """)
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void registerStudentCourse(StudentCourses studentCourse);

    /**
     * 受講生の検索を行います。
     *
     * @param id　受講生ID
     * @return　受講生
     */
    @Select("""
            SELECT * FROM students WHERE id = #{id}
            """)
     Student searchStudent(String id);

    /**
     * 受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param studentid　受講生ID
     * @return　受講生IDに紐づく受講生コース情報
     */
    @Select("""
        SELECT * FROM students_courses WHERE student_id = #{id}
        """)
    List<StudentCourses> searchStudentsCoursesByStudentId(String studentid);

    @Update("""
            UPDATE students SET
            name = #{name},kana = #{kana},nickname= #{nickname},mailaddress =#{mailaddress},
            tiiki = #{tiiki},age = #{age},gender = #{gender},remark = #{remark},isDeleted = #{isDeleted}
            WHERE id = #{id}
            """)
    void updateStudent(Student student);

    @Update("""
            UPDATE students_courses
            SET
                course = #{course}
            WHERE id = #{id}
            """)
    void updateStudentCourse(StudentCourses studentCourses);


}
