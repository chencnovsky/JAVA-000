package java0.springboot.configuration;

import java0.springboot.properties.StudentProperties;
import java0.springboot.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(StudentProperties.class)
@Configuration
public class StudentAutoConfiguration {

    @Autowired
    private StudentProperties studentProperties;

    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student fetchStudent() {
        return new Student(studentProperties);
    }
}
