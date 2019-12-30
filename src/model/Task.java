package model;

import idgenerator.IdGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Task implements Serializable {
    private String name;
    private String description;
    private LocalDateTime plannedDate;
    private LocalDateTime dateOfDone;
    private Status status;

    private int id;

    public Task(Task task) {
        id = task.id;
        name = task.name;
        description = task.name;
        plannedDate = task.plannedDate;
        dateOfDone = task.dateOfDone;
        status = task.status;
    }


    public Task(int id, String name, String description, LocalDateTime plannedDate, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plannedDate = plannedDate;
        this.dateOfDone = null;
        this.status = status;
    }

    public Task() {
        id = IdGenerator.getInstance().getId();
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(LocalDateTime plannedDate) {
        this.plannedDate = plannedDate;
    }

    public LocalDateTime getDateOfDone() {
        return dateOfDone;
    }

    public void setDateOfDone(LocalDateTime dateOfDone) {
        this.dateOfDone = dateOfDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
