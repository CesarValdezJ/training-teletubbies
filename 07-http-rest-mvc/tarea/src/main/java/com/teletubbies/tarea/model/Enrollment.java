package com.teletubbies.tarea.model;

import java.util.UUID;

public class Enrollment {

    private String id;
    private String courseId;
    private String studentName;

    public Enrollment() {
    }

    public Enrollment(String courseId, String studentName) {
        this.id = UUID.randomUUID().toString();
        this.courseId = courseId;
        this.studentName = studentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}