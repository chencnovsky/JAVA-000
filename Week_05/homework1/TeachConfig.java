package java0.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeachConfig {

    @Bean
    public IPerson myStudent() {
        return new Student();
    }

    @Bean
    public IElement myKlass() {
        return new Klass();
    }

    @Bean
    public ISchool mySchool(IElement klass, IPerson student) {
        School school = new School();
        school.setKlass(klass);
        school.setStudent(student);
        return school;
    }

}
