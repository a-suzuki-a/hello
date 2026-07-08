package raisetech.StudentManagement.controller;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service,StudentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        List<Student> students = service.searchStudentList();
        List<StudentCourses> studentCourses = service.searchStudentsCourseList();

        model.addAttribute("studentList",converter.convertStudentDetails(students,studentCourses));
        return "studentList";
    }
    @GetMapping("/studentsCourseList")
        public List<StudentCourses> getStudentsCourseList () {
            return service.searchStudentsCourseList();
    }

    @GetMapping("/newStudent")
    public String newStudent(Model model){

        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(new Student());
        List<StudentCourses> courses = new ArrayList<>();
        courses.add(new StudentCourses());
        studentDetail.setStudentCourses(courses);

       model.addAttribute("studentDetail",studentDetail);
       return "registerStudent";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
        if(result.hasErrors()){
            return "registerStudent";
        }

        service.registerStudent(studentDetail);

        return "redirect:/studentList";
    }
}
