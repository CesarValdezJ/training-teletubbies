package com.teletubbies.tarea.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String courseId) {
        super("No existe un curso con id: " + courseId);
    }
}