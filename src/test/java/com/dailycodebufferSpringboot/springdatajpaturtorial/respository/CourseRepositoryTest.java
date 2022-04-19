package com.dailycodebufferSpringboot.springdatajpaturtorial.respository;

import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Course;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Student;
import com.dailycodebufferSpringboot.springdatajpaturtorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("cousese = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Tuan")
                .lastName("vm")
                .build();

        Course course = Course.builder()
                .title("JavaScript")
                .credit(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        org.springframework.data.domain.Pageable firstPagewithThreeRecords = PageRequest.of(0, 3);
        org.springframework.data.domain.Pageable secondPagewithThreeRecords = PageRequest.of(0, 2);

        List<Course> courses = courseRepository.findAll(secondPagewithThreeRecords).getContent();

        long totalElements = courseRepository.findAll(secondPagewithThreeRecords).getTotalElements();

        long totalPages = courseRepository.findAll(secondPagewithThreeRecords).getTotalPages();

        System.out.println("Total Elements = " + totalElements);
        System.out.println("Total Pages = " + totalPages);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting() {
        org.springframework.data.domain.Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        org.springframework.data.domain.Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
        org.springframework.data.domain.Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void printFindByTitleContaining() {
        org.springframework.data.domain.Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses = courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Anh Loi")
                .lastName("Tay to")
                .emailId("tayto@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudent(student);
        courseRepository.save(course);
    }
}