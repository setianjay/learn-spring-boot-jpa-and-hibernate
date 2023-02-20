package com.setianjay.springboot.learnspringbootjpaandhibernate.course;

import java.util.List;

public interface CourseRepository {
    void insertCourse(Course course);

    void deleteCourseById(int id);

    Course findCourseById(int id);

    List<Course> findAllCourse();

    void updateCourse(Course course);
}
