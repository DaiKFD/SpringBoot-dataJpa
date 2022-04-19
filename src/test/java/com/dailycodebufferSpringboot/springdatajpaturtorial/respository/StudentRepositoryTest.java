package com.dailycodebufferSpringboot.springdatajpaturtorial.respository;

import com.dailycodebufferSpringboot.springdatajpaturtorial.SpringDataJpaTurtorialApplication;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Course;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.CourseMaterial;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Guardian;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SpringDataJpaTurtorialApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class StudentRepositoryTest {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public CourseRepository courseRepository;

    @Autowired
    public CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("dainthe153784@fpt.edu.vn")
                .firstName("Dai")
                .lastName("kfd")
//                .guardianName("Dung")
//                .guardianEmail("dungahihi@gmail.com")
//                .guardianMobile("1234567789")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .email("dungahihi@gmail.com")
                .name("Dung")
                .mobile("1234567789")
                .build();

        Student student = Student.builder()
                .firstName("anh thang")
                .emailId("myname@gmail.com")
                .lastName("tay to")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("Dai");
        System.out.println("studentList = " + students);

    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("a");
        System.out.println("studentList = " + students);

    }

    @Test
    public void printStudentByGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Dung");
        System.out.println("studentList = " + students);

    }

    @Test
    public void printStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("dainthe153784@fpt.edu.vn");
        System.out.println("student = " + student);

    }

    @Test
    public void printGetStudentFirstNameByEmailAddress() {
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("dainthe153784@fpt.edu.vn");
        System.out.println("student = " + firstName);

    }

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .title(".net")
                .credit(6)
                .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.sololearn.com")
                        .course(course)
                        .build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printGetStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("dainthe153784@fpt.edu.vn");
        System.out.println("student = " + student);

    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("dainthe153784@fpt.edu.vn");
        System.out.println("student = " + student);

    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId("Trong Dai", "dainthe153784@fpt.edu.vn");
    }

    @Test
    public void printAllCoursesMaterial() {
        List<CourseMaterial> courseMaterials =
                courseMaterialRepository.findAll();
        System.out.println("courseMaterials = " + courseMaterials);

    }

    @Test
    public void printCourse() {
        List<Course> courses =
                courseRepository.findAll();
        System.out.println("Courses = " + courses);
    }
}