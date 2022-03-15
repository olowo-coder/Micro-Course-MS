package com.example.coursems.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean create(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public boolean update(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        courseRepository.deleteById(id);
        return true;
    }
}
