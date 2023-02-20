package com.setianjay.springboot.learnspringbootjpaandhibernate.course;

import com.setianjay.springboot.learnspringbootjpaandhibernate.course.springjpa.CourseSpringJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class implements interface CommandLineRunner,  CommandLineRunner is an interface used to execute code after the
 * Spring Boot application is started.
 * */
@Component
public class CourseCommandLineRunner implements CommandLineRunner {
//    private final CourseRepository repository;

    private final CourseSpringJpaRepository repository;

    @Autowired
    public CourseCommandLineRunner(CourseSpringJpaRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
//        repository.insertCourse(new Course(1, "Learn Kotlin", "Hari Setiaji"));
//        repository.insertCourse(new Course(2, "Learn Java", "Hari Setiaji"));
//        repository.insertCourse(new Course(3, "Learn Android", "Hari Setiaji"));
//        repository.insertCourse(new Course(4, "Learn SpringBoot", "Hari Setiaji"));

        repository.save(new Course(1, "Learn Kotlin", "Hari Setiaji"));
        repository.save(new Course(2, "Learn Java", "Hari Setiaji"));
        repository.save(new Course(3, "Learn Android", "Hari Setiaji"));
        repository.save(new Course(4, "Learn SpringBoot", "Hari Setiaji"));

        /* delete course with id 4 */
//        repository.deleteCourseById(4);
        repository.deleteById(4L);

        /* update course with id 3 */
//        repository.updateCourse(new Course(3, "Learn Android and Spring Boot", "Setyarto"));
        repository.save(new Course(3, "Learn Android and Spring Boot", "Setyarto"));

//        System.out.println(repository.findCourseById(3));
//        System.out.println(repository.findCourseById(1));
        System.out.println(repository.findById(3L));
        System.out.println(repository.findById(1L));
    }
}
