package com.example.todolist.models;


import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Boolean status;
    private Date dueDate;
    private String type;

    public Task() {
    }

    public Task(int id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = false;
        this.type = type;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
