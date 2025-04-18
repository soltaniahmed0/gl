package com.votre.package;

import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "L'heure de début ne peut pas être nulle")
    private Timestamp startTime;
    
    @NotNull(message = "L'heure de fin ne peut pas être nulle")
    private Timestamp endTime;
    
    @NotBlank(message = "Le sujet ne peut pas être vide")
    @Size(min = 1, message = "Le sujet doit contenir au moins un caractère")
    private String subject;
    
    private String notes;
    
    private boolean approve;
    
    @NotNull(message = "L'employé ne peut pas être nul")
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @NotNull(message = "La salle ne peut pas être nulle")
    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    private Room room;
    
    // Getters et setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Timestamp getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    
    public Timestamp getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public boolean isApprove() {
        return approve;
    }
    
    public void setApprove(boolean approve) {
        this.approve = approve;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    // Validation personnalisée pour la contrainte: startTime < endTime
    @AssertTrue(message = "L'heure de début doit être avant l'heure de fin")
    public boolean isStartTimeBeforeEndTime() {
        if (startTime == null || endTime == null) {
            return true; // La validation @NotNull s'en chargera
        }
        return startTime.before(endTime);
    }
}