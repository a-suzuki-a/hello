package raisetech.StudentManagement.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.service.StudentService;

import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.atomicReferenceArray;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;
    @MockitoBean
    private StudentConverter converter;
    @MockitoBean
    private StudentRepository repository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    void 受講生詳細の一覧検索ができて空のリストが返ってくること() throws Exception {
        mockMvc.perform(get("/studentList"))
                .andExpect(status().isOk());

        verify(service,times(1)).searchStudentList(null, null, null, null, null,
                null, null, null, null, null);
    }
    @Test
    void 受講生詳細の検索ができて空のリストが返ってくること() throws Exception {
        String id = "999";
        mockMvc.perform(get("/student/{id}",id))
                .andExpect(status().isOk());

        verify(service,times(1)).searchStudent(id);
    }
    @Test
    void 受講生詳細の登録が実行できて空のリストが返ってくること() throws Exception {
        mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
                """
                        {
                        "student":{
                        "name":"佐藤太郎",
                        "kana":"サトウタロウ",
                        "nickname":"さと",
                        "mailaddress":"sato@example.com",
                        "tiiki":"神奈川",
                        "age":36,
                        "gender":"male",
                        "remark":""
                        },
                        "studentCourseList":[
                        {
                        "course":"Javaコース"
                        }
                        ]
                        }
                        """
        ))
                .andExpect(status().isOk());
        verify(service,times(1)).registerStudent(any());
    }

    @Test
    void 受講生詳細の受講生で適切な値を入力したときに入力チェックに異常が発生しないこと() {
        Student student = new Student();
        student.setId("1");
        student.setName("山田太郎");
        student.setKana("ヤマダタロウ");
        student.setNickname("タロ");
        student.setMailaddress("taro@example.com");
        student.setTiiki("東京");
        student.setAge(20);
        student.setGender("male");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックに掛かること() {
        Student student = new Student();
        student.setId("テストです");
        student.setName("山田太郎");
        student.setKana("ヤマダタロウ");
        student.setNickname("タロ");
        student.setMailaddress("taro@example.com");
        student.setTiiki("東京");
        student.setAge(20);
        student.setGender("male");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations).extracting("message").containsOnly("数字のみを入力してください");
    }

    @Test
    void 受講生一覧のAPIがRESTとして動作すること() throws Exception{
        mockMvc.perform(get("/studentList"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        verify(service,times(1)).searchStudentList(null, null, null, null, null,
                null, null, null, null, null);
    }

    @Test
    void 受講生詳細を取得できること() throws Exception {
        //テスト用受講生

        Student student = new Student();
        student.setId("123");
        student.setName("山田太郎");
        student.setKana("ヤマダタロウ");
        student.setNickname("タロ");
        student.setMailaddress("taro@example.com");
        student.setTiiki("東京");
        student.setAge(20);
        student.setGender("male");

        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentCourseList(new ArrayList<>());
        //serviceの戻り値設定
        when(service.searchStudent("123"))
                .thenReturn(studentDetail);
        //APIにリクエストを送信して確認
        mockMvc.perform(get("/student/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.student.id").value("123"))
                .andExpect(jsonPath("$.student.name").value("山田太郎"));
        //serviceが1回呼び出されたことを確認
        verify(service, times(1)).searchStudent("123");
    }
    @Test
            void コースのステータスを仮申込に更新できること()
        throws Exception{
        when(service.updateStudentCourseStatus(1,"仮申込"))
                .thenReturn(true);
        mockMvc.perform(put("/studentCourse/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"status":"仮申込"}"""))
                .andExpect(status().isNoContent());
        verify(service,times(1))
                .updateStudentCourseStatus(1,"仮申込");
    }
    @Test
    void コースのステータスを本申込に更新できること() throws Exception {
        when(service.updateStudentCourseStatus(1, "本申込"))
                .thenReturn(true);

        mockMvc.perform(put("/studentCourse/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "本申込"
                            }
                            """))
                .andExpect(status().isNoContent());

        verify(service, times(1))
                .updateStudentCourseStatus(1, "本申込");
    }

    @Test
    void コースのステータスを受講中に更新できること() throws Exception {
        when(service.updateStudentCourseStatus(1, "受講中"))
                .thenReturn(true);

        mockMvc.perform(put("/studentCourse/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "受講中"
                            }
                            """))
                .andExpect(status().isNoContent());

        verify(service, times(1))
                .updateStudentCourseStatus(1, "受講中");
    }

    @Test
    void コースのステータスを受講終了に更新できること() throws Exception {
        when(service.updateStudentCourseStatus(1, "受講終了"))
                .thenReturn(true);

        mockMvc.perform(put("/studentCourse/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "受講終了"
                            }
                            """))
                .andExpect(status().isNoContent());

        verify(service, times(1))
                .updateStudentCourseStatus(1, "受講終了");
    }
@Test
    void 存在しないコースIDを指定したとき404が返ること()
    throws Exception{
        when(service.updateStudentCourseStatus(999,"本申込"))
                .thenReturn(false);
        mockMvc.perform(put("/studentCourse/999/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"status":"本申込"}
                        """))
                .andExpect(status().isNotFound());
        verify(service,times(1))
                .updateStudentCourseStatus(999,"本申込");
}
    @Test
    void 不正なステータスを指定したとき400が返ること() throws Exception {

        mockMvc.perform(put("/studentCourse/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "不正なステータス"
                            }
                            """))
                .andExpect(status().isBadRequest());

        verify(service, never())
                .updateStudentCourseStatus(anyInt(), anyString());
    }
}