package springbootdemo;

import java0.springboot.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private Student student;

    @GetMapping("/school/rev")
    public String getSchoolRev() {
        return student.gen();
    }


}
