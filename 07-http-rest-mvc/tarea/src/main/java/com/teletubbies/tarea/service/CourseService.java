package com.teletubbies.tarea.service;

import com.teletubbies.tarea.exception.CourseFullException;
import com.teletubbies.tarea.exception.CourseNotFoundException;
import com.teletubbies.tarea.model.Course;
import com.teletubbies.tarea.model.Enrollment;
import com.teletubbies.tarea.repository.CourseRepository;
import com.teletubbies.tarea.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public PagedResult<Course> listCourses(String instructor, int page, int pageSize) {
        if (page < 1 || pageSize < 1 || pageSize > 100) {
            throw new IllegalArgumentException("page o pageSize inválidos");
        }
        List<Course> filtered = courseRepository.findByInstructor(instructor);

        int totalItems = filtered.size();
        int totalPages = (int) Math.ceil(totalItems / (double) pageSize);
        int fromIndex = Math.min((page - 1) * pageSize, totalItems);
        int toIndex = Math.min(fromIndex + pageSize, totalItems);

        List<Course> pageItems = filtered.subList(fromIndex, toIndex);
        return new PagedResult<>(pageItems, page, pageSize, totalItems, totalPages);
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    public Course createCourse(String title, String instructor, int capacity) {
        if (title == null || title.isBlank() || instructor == null || instructor.isBlank() || capacity <= 0) {
            throw new IllegalArgumentException("title, instructor y capacity (positivo) son requeridos");
        }
        Course course = new Course(title, instructor, capacity);
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, String title, String instructor, int capacity) {
        if (title == null || title.isBlank() || instructor == null || instructor.isBlank() || capacity <= 0) {
            throw new IllegalArgumentException("title, instructor y capacity (positivo) son requeridos");
        }

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        course.setTitle(title);
        course.setInstructor(instructor);
        course.setCapacity(capacity);

        return courseRepository.save(course);
    }

    public Course patchCourse(String id, String title, String instructor, Integer capacity) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        if (title != null) {
            if (title.isBlank()) {
                throw new IllegalArgumentException("title no puede quedar vacío");
            }
            course.setTitle(title);
        }
        if (instructor != null) {
            if (instructor.isBlank()) {
                throw new IllegalArgumentException("instructor no puede quedar vacío");
            }
            course.setInstructor(instructor);
        }
        if (capacity != null) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("capacity debe ser un entero positivo");
            }
            course.setCapacity(capacity);
        }

        return courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }

    public Enrollment enrollStudent(String courseId, String studentName) {
        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("studentName es requerido");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        if (course.isFull()) {
            throw new CourseFullException(courseId);
        }

        Enrollment enrollment = new Enrollment(courseId, studentName);
        enrollmentRepository.save(enrollment);

        course.incrementEnrolledCount();
        courseRepository.save(course);

        return enrollment;
    }

    public static class PagedResult<T> {
        public final List<T> items;
        public final int page;
        public final int pageSize;
        public final int totalItems;
        public final int totalPages;

        public PagedResult(List<T> items, int page, int pageSize, int totalItems, int totalPages) {
            this.items = items;
            this.page = page;
            this.pageSize = pageSize;
            this.totalItems = totalItems;
            this.totalPages = totalPages;
        }
    }
}