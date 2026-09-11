package com.teletubbies.tarea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(CourseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("code", "COURSE_NOT_FOUND", "message", ex.getMessage()));
    }

    @ExceptionHandler(CourseFullException.class)
    public ResponseEntity<Map<String, String>> handleCourseFull(CourseFullException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("code", "COURSE_FULL", "message", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("code", "BAD_REQUEST", "message", ex.getMessage()));
    }
}