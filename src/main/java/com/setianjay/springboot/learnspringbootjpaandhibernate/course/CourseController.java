package com.setianjay.springboot.learnspringbootjpaandhibernate.course;

import com.setianjay.springboot.learnspringbootjpaandhibernate.course.springjpa.CourseSpringJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    // private final CourseRepository courseRepository;
    private final CourseSpringJpaRepository courseRepository;

    @Autowired
    public CourseController(CourseSpringJpaRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping("/courses")
    public List<Course> retrieveCourses(){
        return courseRepository.findAll();
    }

    @RequestMapping("/coursesByAuthor")
    public List<Course> retrieveCourseByAuthor(){
        return courseRepository.findAllByAuthor("Hari Setiaji");
    }

    @RequestMapping("/authors")
    public List<String> retrieveAuthors(){
        return courseRepository.getAllAuthors();
    }
}
