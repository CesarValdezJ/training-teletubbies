package com.teletubbies.tarea.controller;

import com.teletubbies.tarea.model.Course;
import com.teletubbies.tarea.model.Enrollment;
import com.teletubbies.tarea.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<CourseService.PagedResult<Course>> listCourses(
            @RequestParam(required = false) String instructor,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(courseService.listCourses(instructor, page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    // POST /courses
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        String instructor = (String) body.get("instructor");
        Integer capacity = extractCapacity(body);

        if (capacity == null) {
            throw new IllegalArgumentException("capacity es requerido y debe ser numérico");
        }

        Course created = courseService.createCourse(title, instructor, capacity);

        return ResponseEntity
                .created(URI.create("/courses/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String id,
            @RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        String instructor = (String) body.get("instructor");
        Integer capacity = extractCapacity(body);

        if (capacity == null) {
            throw new IllegalArgumentException("capacity es requerido y debe ser numérico");
        }

        Course updated = courseService.updateCourse(id, title, instructor, capacity);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Course> patchCourse(
            @PathVariable String id,
            @RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        String instructor = (String) body.get("instructor");
        Integer capacity = extractCapacity(body);

        Course patched = courseService.patchCourse(id, title, instructor, capacity);
        return ResponseEntity.ok(patched);
    }

    // DELETE /courses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/enrollments")
    public ResponseEntity<Enrollment> enrollStudent(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {
        String studentName = body.get("studentName");

        Enrollment enrollment = courseService.enrollStudent(id, studentName);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/courses/" + id + "/enrollments/" + enrollment.getId())
                .body(enrollment);
    }

    private Integer extractCapacity(Map<String, Object> body) {
        Object capacity = body.get("capacity");
        if (capacity == null) {
            return null;
        }
        if (capacity instanceof Number) {
            return ((Number) capacity).intValue();
        }
        throw new IllegalArgumentException("capacity debe ser un número");
    }
}