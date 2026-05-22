package raisetech.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class StudentManagementApplication {


	private Map<String,String> student = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

@GetMapping("/studentInfo")
public Map<String,String> getStudentInfo() {
	return student;
}

@PostMapping ("/studentInfo")
	public void  setStudentInfo(String name,String age){

		student.put(name,age);
}

@PostMapping("/studentName")
	public void updateStudentName(String oldName,String newName){
		String age = student.get(oldName);
		student.remove(oldName);
		student.put(newName,age);

}
}