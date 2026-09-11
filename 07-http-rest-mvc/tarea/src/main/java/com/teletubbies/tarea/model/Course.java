package com.teletubbies.tarea.model;

import java.util.UUID;

public class Course {

    private String id;
    private String title;
    private String instructor;
    private int capacity;
    private int enrolledCount;

    public Course() {
    }

    public Course(String title, String instructor, int capacity) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public boolean isFull() {
        return this.enrolledCount >= this.capacity;
    }

    public void incrementEnrolledCount() {
        this.enrolledCount++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(int enrolledCount) {
        this.enrolledCount = enrolledCount;
    }
}