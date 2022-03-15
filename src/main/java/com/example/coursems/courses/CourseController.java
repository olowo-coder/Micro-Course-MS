package com.example.coursems.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(courseService.getAll());
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveStudent(@RequestBody Course course){
        return ResponseEntity.ok(courseService.create(course));
    }

    @GetMapping("/ID/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Course course){
        return ResponseEntity.ok(courseService.update(course));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(courseService.delete(id));
    }

}
