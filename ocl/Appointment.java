package com.example.Backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "L'heure de début ne peut pas être nulle")
    private Timestamp startTime;
    
    @NotNull(message = "L'heure de fin ne peut pas être nulle")
    private Timestamp endTime;
    
    @NotBlank(message = "Le sujet ne peut pas être vide")
    private String subject;
    
    private String notes;
    
    private boolean approve;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    @NotNull(message = "L'employé ne peut pas être nul")
    private Employee employee;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    @NotNull(message = "La salle ne peut pas être nulle")
    private Room room;
}