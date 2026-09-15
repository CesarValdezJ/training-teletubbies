package com.teletubbies.tarea.exception;

public class CourseFullException extends RuntimeException {

    public CourseFullException(String courseId) {
        super("El curso " + courseId + " ya alcanzó su capacidad máxima.");
    }
}