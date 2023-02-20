package com.setianjay.springboot.learnspringbootjpaandhibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(@Qualifier(value = "jpa") CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping("/courses")
    public List<Course> retrieveCourses(){
        return courseRepository.findAllCourse();
    }
}
