package com.setianjay.springboot.learnspringbootjpaandhibernate.entity;

import com.setianjay.springboot.learnspringbootjpaandhibernate.course.Course;
import com.setianjay.springboot.learnspringbootjpaandhibernate.course.springjpa.CourseSpringJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseTest {

    @Autowired
    private CourseSpringJpaRepository repository;

    @Test
    public void saveCourseToDatabaseTest(){
        Course course = Course.builder()
                .title("Learn All about technologies")
                .author("Hari Setiaji")
                .build();

        repository.save(course);

        List<Course> courses = repository.findAll();
        System.out.println(courses);

        assertEquals(5, courses.size());
    }

    @Test
    public void findAllCourseByAuthorFromDatabaseTest(){
        List<Course> courses = repository.findAllByAuthor("Hari Setiaji");
        System.out.println(courses);
        assertTrue(courses.size() > 0);
    }

    @Test
    public void getAllAuthorsFromDatabaseTest(){
        List<String> courses = repository.getAllAuthors();
        System.out.println(courses);
        assertTrue(courses.size() > 0);
    }
}