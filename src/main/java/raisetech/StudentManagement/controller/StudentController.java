package raisetech.StudentManagement.controller;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */

@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service){
        this.service = service;

    }

    /**
     * 受講生一覧検索です。
     * 全件検索を行うので、条件指定は行いません。
     *
     * @return　受講生一覧（全件）
     */
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }
    @GetMapping("/studentsCourseList")
        public List<StudentCourses> getStudentsCourseList () {
            return service.searchStudentsCourseList();
    }

    /**
     * 受講生検索です。
     * IDに紐づく任意の受講生情報を取得します。
     *
     * @param id　受講生ID
     * @return　受講生
     */
    @GetMapping("/student/{id}")
    public StudentDetail getStudent(@PathVariable String id){
        return service.searchStudent(id);
    }


    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail>registerStudent(@RequestBody StudentDetail studentDetail) {
        StudentDetail responseStudentDetail =service.registerStudent(studentDetail);
            return ResponseEntity.ok(responseStudentDetail);
        }

    @PostMapping("/updateStudent")
    public ResponseEntity<String>updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました");
    }
}
