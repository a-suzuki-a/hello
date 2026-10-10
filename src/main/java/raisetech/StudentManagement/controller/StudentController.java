package raisetech.StudentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import raisetech.StudentManagement.exception.TestException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

import java.util.List;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service){
        this.service = service;

    }

    /**
     * 受講生詳細の一覧検索です。
     * 全件検索を行うので、条件指定は行いません。
     *
     * @return　受講生詳細一覧（全件）
     */
    @Operation(summary = "一覧検索",description = "受講生の一覧を検索します")
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() throws TestException {
        throw new TestException(
                "現在このAPIは利用できません。URLは「studentList」ではなく「students」を利用してください");
    }
    @Operation(summary = "受講生コース一覧検索",description = "受講生に紐づく受講生コース情報の一覧を検索します")
    @GetMapping("/studentsCourseList")
        public List<StudentCourse> getStudentsCourseList () {
            return service.searchStudentsCourseList();
    }

    /**
     * 受講生詳細の検索です。
     * IDに紐づく任意の受講生情報を取得します。
     *
     * @param id　受講生ID
     * @return　受講生
     */
    @Operation(summary = "受講生詳細検索",description = "IDに紐づく受講生情報を検索します")
    @GetMapping("/student/{id}")
    public StudentDetail getStudent(@Parameter(description = "受講生一覧",example = "1")
                                        @PathVariable @NotBlank @Pattern(regexp = "^[0-9]+$") String id){
        return service.searchStudent(id);
    }

    /**
     *受講生詳細の登録を行います。
     *
     * @param studentDetail　受講生詳細
     * @return　実行結果
     */
    @Operation(summary = "受講生登録",description = "受講生の登録をします")
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail>registerStudent(@Valid @RequestBody StudentDetail studentDetail) {
        StudentDetail responseStudentDetail =service.registerStudent(studentDetail);
            return ResponseEntity.ok(responseStudentDetail);
        }

    /**
     * 受講生詳細の更新を行います。
     * キャンセルフラグの更新もここで行います。（論理削除）
     *
     * @param studentDetail　受講生詳細
     * @return　実行結果
     */
    @Operation(summary = "受講生詳細更新",description = "受講生詳細を更新します。")
    @PutMapping("/updateStudent")
    public ResponseEntity<String>updateStudent(@Valid @RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました");
    }

    @ExceptionHandler(TestException.class)
    public ResponseEntity<String>handTestException(TestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
