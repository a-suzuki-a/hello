package raisetech.StudentManagement.repository;

import org.apache.ibatis.annotations.*;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> search();

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

    @Select("""
            SELECT * FROM students WHERE id = #{id}
            """)
     Student searchStudent(String id);

    @Select("""
        SELECT * FROM students_courses WHERE student_id = #{id}
        """)
    List<StudentCourses> searchStudentsCoursesByStudentId(String id);

    @Update("""
            UPDATE students SET
            name = #{name},kana = #{kana},nickname= #{nickname},=mailaddress #{mailaddress},
            tiiki = #{tiiki},age = #{age},gender = #{gender},remark = #{remark}
            WHERE id = #{id}
            """)
    void updateStudent(Student student);

    @Update("""
            UPDATE student_courses
            SET
                course = #{course},
                start_date = #{startDate},
                schedule_end_date = #{scheduleEndDate}
            WHERE id = #{id}
            """)
    void updateStudentCourse(StudentCourses studentCourses);


}
