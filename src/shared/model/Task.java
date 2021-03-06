package shared.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import shared.utils.IdGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Task class, which have a name, description, date of complete, planned date and status
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Task implements Serializable {
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    /*@JsonSerialize(as = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)*/
    private LocalDateTime plannedDate;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    /*@JsonSerialize(as = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class, as = LocalDateTime.class)*/
    private LocalDateTime dateOfDone;
    private Status status;

    private int id;

    /**
     * Copy constructor
     *
     * @param task - copied object
     */

    public Task(Task task) {
        id = task.id;
        name = task.name;
        description = task.description;
        plannedDate = task.plannedDate;
        dateOfDone = task.dateOfDone;
        status = task.status;
    }

    /**
     * Constructor with certain values
     *
     * @param id          - id of task
     * @param name        - name of task
     * @param description - description of task
     * @param plannedDate - planned date of task
     * @param status      - status of task
     */


    public Task(int id, String name, String description, LocalDateTime plannedDate, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plannedDate = plannedDate;
        this.dateOfDone = null;
        this.status = status;
    }


    /**
     * Default constructor
     */

    public Task() {
        id = IdGenerator.getInstance().getId();
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", plannedDate=" + plannedDate +
                ", dateOfDone=" + dateOfDone +
                ", status=" + status +
                ", id=" + id +
                '}';
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
