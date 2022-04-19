package com.dailycodebufferSpringboot.springdatajpaturtorial.respository;

import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Course;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();
        Course courseJava = Course.builder()
                .title("Java")
                .credit(7)
                .build();
        Course coursePython = Course.builder()
                .title("python")
                .credit(8)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Qutub")
                        .lastName("Khan")
                        .courses(List.of(courseDBA,courseJava,coursePython))
                        .build();
        teacherRepository.save(teacher);
    }
}