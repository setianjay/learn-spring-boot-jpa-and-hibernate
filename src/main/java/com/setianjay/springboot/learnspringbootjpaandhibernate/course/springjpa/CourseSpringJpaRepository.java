package com.setianjay.springboot.learnspringbootjpaandhibernate.course.springjpa;

import com.setianjay.springboot.learnspringbootjpaandhibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CourseSpringJpaRepositoryImpl using Spring JPA to interact with database.
 * Spring JPA: make JPA more simple and will take care of everything.
 * */
public interface CourseSpringJpaRepository extends JpaRepository<Course, Long> {

    /*
    * Create custom functionality. If you use this way, name of function after a sentence 'By' must be same with field
    * on table. Queries are handle by Spring.
    * */
    List<Course> findAllByAuthor(String author);

    /*
    * Create custom functionality. If you use this way, You are free to give any function name because you custom the
    * queries.
    * */
    @Query("SELECT DISTINCT c.author FROM Course c")
    List<String> getAllAuthors();
}
