package java0.springboot.service;

import java0.springboot.properties.StudentProperties;

public class Student {

    private StudentProperties studentProperties;

    public Student(StudentProperties studentProperties) {
        this.studentProperties = studentProperties;
    }

    public String gen() {
        return "招收年龄是" + studentProperties.getAge() + "，" + "姓名是" + studentProperties.getName() + "的学生。";
    }
}
