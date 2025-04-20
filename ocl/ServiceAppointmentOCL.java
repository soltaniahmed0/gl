package com.example.Backend.Services;

import com.example.Backend.Entity.Appointment;
import com.example.Backend.Entity.Room;
import com.example.Backend.Repository.AppointmentRepository;
import com.example.Backend.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getappointments() {
        return appointmentRepository.findAll();
    }

    public void addappointment(Appointment appointment) {
        // Validation des contraintes OCL avant l'enregistrement
        validateAppointmentConstraints(appointment);
        appointmentRepository.save(appointment);
    }

    public Appointment getappointment(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> getAppointmentsByRoomId(int roomId) {
        Optional<Room> findroom = roomRepository.findById(roomId);
        if (findroom.isPresent()) {
            Room room = findroom.get();
            return appointmentRepository.findByRoom(room);
        } else {
            throw new IllegalArgumentException("room with id " + roomId + " not found");
        }
    }

    public List<Appointment> getCurrentlyHappeningAppointmentsForUser(int userId) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimestamp.getTime());
        calendar.add(Calendar.HOUR, +1);
        Timestamp updatedTimestamp = new Timestamp(calendar.getTimeInMillis());
        return appointmentRepository.findByEmployeeIdAndStartTimeBeforeAndEndTimeAfter(userId, updatedTimestamp, updatedTimestamp);
    }

    public Appointment updateAppointment(Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        if (appointmentOptional.isPresent()) {
            Appointment existingAppointment = appointmentOptional.get();
            existingAppointment.setStartTime(appointment.getStartTime());
            existingAppointment.setEndTime(appointment.getEndTime());
            existingAppointment.setSubject(appointment.getSubject());
            existingAppointment.setNotes(appointment.getNotes());
            existingAppointment.setApprove(appointment.isApprove());
            
            // Validation des contraintes OCL avant mise à jour
            validateAppointmentConstraints(existingAppointment);
            
            return appointmentRepository.save(existingAppointment);
        } else {
            throw new IllegalArgumentException("Appointment with id " + appointment.getId() + " not found");
        }
    }

    public void deleteappointment(int id) {
        appointmentRepository.deleteById(id);
    }
    
    /**
     * Valide les contraintes OCL de l'objet Appointment
     * @param appointment l'objet à valider
     * @throws IllegalArgumentException si les contraintes ne sont pas respectées
     */
    private void validateAppointmentConstraints(Appointment appointment) {
        // inv: self.startTime < self.endTime
        if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
            throw new IllegalArgumentException("Les heures de début et de fin ne peuvent pas être nulles");
        }
        if (!appointment.getStartTime().before(appointment.getEndTime())) {
            throw new IllegalArgumentException("L'heure de début doit être antérieure à l'heure de fin");
        }
        
        // inv: not self.subject.oclIsUndefined() and self.subject.size() > 0
        if (appointment.getSubject() == null || appointment.getSubject().trim().isEmpty()) {
            throw new IllegalArgumentException("Le sujet du rendez-vous ne peut pas être vide");
        }
        
        // inv: not self.employee.oclIsUndefined() and not self.room.oclIsUndefined()
        if (appointment.getEmployee() == null) {
            throw new IllegalArgumentException("Un employé doit être assigné au rendez-vous");
        }
        
        if (appointment.getRoom() == null) {
            throw new IllegalArgumentException("Une salle doit être assignée au rendez-vous");
        }
    }
}