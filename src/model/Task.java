package model;

import java.util.Date;
import idgenerator.IdGenerator;


public class Task {
    private String name;
    private String description;
    private Date plannedDate;
    private Date dateOfDone;
    private Status status;

    private int id;

    public Task() {
        id = IdGenerator.getInstance().getId();
    };

    public Task(String name, String description, Date plannedDate, Status status) {
        id = IdGenerator.getInstance().getId();
        this.name = name;
        this.description = description;
        this.plannedDate = plannedDate;
        this.dateOfDone = null;
        this.status = status;
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

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Date getDateOfDone() {
        return dateOfDone;
    }

    public void setDateOfDone(Date dateOfDone) {
        this.dateOfDone = dateOfDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
