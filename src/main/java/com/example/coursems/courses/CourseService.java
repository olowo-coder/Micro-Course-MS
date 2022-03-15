package com.example.coursems.courses;

import java.util.List;

public interface CourseService {
    boolean create(Course course);

    Course getById (Long id);

    List<Course> getAll();

    boolean update(Course course);

    boolean delete(Long id);
}
