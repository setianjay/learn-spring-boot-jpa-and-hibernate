package com.setianjay.springboot.learnspringbootjpaandhibernate.course.springjdbc.repostitory;

import com.setianjay.springboot.learnspringbootjpaandhibernate.course.Course;
import com.setianjay.springboot.learnspringbootjpaandhibernate.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CourseJdbcRepositoryImpl using Spring Jdbc to interact with database.
 * Spring Jdbc: write less java code but write a lot more sql statements.
 * */
@Repository
@Qualifier(value = "spring-jdbc")
public class CourseJdbcRepositoryImpl implements CourseRepository {
    private final JdbcTemplate jdbc;

    private static final String INSERT_COURSE = """
            INSERT INTO course (id, title, author) VALUES(?, ?, ?);
            """;

    private static final String DELETE_COURSE_BY_ID = """
            DELETE FROM course WHERE id = ?;
            """;

    private static final String SELECT_COURSE_BY_ID = """
            SELECT * FROM course WHERE id = ?;
            """;

    private static final String SELECT_ALL_COURSE = """
            SELECT * FROM course;
            """;

    private static final String UPDATE_SPECIFIC_COURSE = """
            UPDATE course SET title = ?, author = ? WHERE id = ?;
            """;

    @Autowired
    public CourseJdbcRepositoryImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public void insertCourse(Course course) {
        jdbc.update(INSERT_COURSE, course.getId(), course.getTitle(), course.getAuthor());
    }

    @Override
    public void deleteCourseById(int id) {
        jdbc.update(DELETE_COURSE_BY_ID, id);
    }

    @Override
    public Course findCourseById(int id) {
        return jdbc.queryForObject(SELECT_COURSE_BY_ID, new BeanPropertyRowMapper<>(Course.class), id);
    }

    @Override
    public List<Course> findAllCourse() {
        return jdbc.query(SELECT_ALL_COURSE, new BeanPropertyRowMapper<>(Course.class));
    }

    @Override
    public void updateCourse(Course course) {
        jdbc.update(UPDATE_SPECIFIC_COURSE, course.getTitle(), course.getAuthor(), course.getId());
    }
}
