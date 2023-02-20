package com.setianjay.springboot.learnspringbootjpaandhibernate.course.jpa;

import com.setianjay.springboot.learnspringbootjpaandhibernate.course.Course;
import com.setianjay.springboot.learnspringbootjpaandhibernate.course.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@Qualifier(value = "jpa")
public class CourseJpaRepositoryImpl implements CourseRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void insertCourse(Course course) {
        manager.merge(course);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = findCourseById(id);
        manager.remove(course);
    }

    @Override
    public Course findCourseById(int id) {
        return manager.find(Course.class, id);
    }

    @Override
    public List<Course> findAllCourse() {
        return manager.createQuery("SELECT c FROM course c", Course.class).getResultList();
    }

    @Override
    public void updateCourse(Course course) {
        manager.merge(course);
    }
}
