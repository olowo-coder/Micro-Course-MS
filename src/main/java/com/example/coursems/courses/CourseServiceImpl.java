package com.example.coursems.courses;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

//    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final Tracer tracer;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, Tracer tracer) {
        this.courseRepository = courseRepository;
        this.tracer = tracer;
    }

    @Override
    public boolean create(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public Course getById(Long id) {
        log.trace("trace: log before custom span");
        log.info("info: log before custom span");
        Span newSpan = this.tracer.nextSpan().name("custom-log");
        try(Tracer.SpanInScope ws = this.tracer.withSpan(newSpan.start())){
            newSpan.tag("custom-tag", "###333###");
            log.info("info: log in custom span");
        }
        finally {
            //remember to end the span
            newSpan.end();
        }
        log.info("info: log after custom span");
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
