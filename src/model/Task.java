package model;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    // todo plannedDate or datePlan?
    private Date plannedDate;
    // todo the same
    private Date dateOfDone;
    private Status status;
    private int id;

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
}
