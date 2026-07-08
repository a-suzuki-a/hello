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
           (id,name,kana,nickname,mailaddress,tiiki,age,gender,remark,isDeleted)
           VALUES
           (#{id},#{name},#{kana},#{nickname},#{mailaddress},#{tiiki},#{age},#{gender},#{remark},#{isDeleted})
           """)
    void registerStudent(Student student);

    @Insert("""
                 INSERT INTO students_courses
                 (id,student_id,course,start_date,schedule_end_date)
                 VALUES
                 (#{id},#{studentId},#{course},#{startDate},#{scheduleEndDate})
               """)
    void registerStudentCourse(StudentCourses studentCourse);


}
