package com.example.Backend.Validator;

import com.example.Backend.Entity.Appointment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppointmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Appointment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Appointment appointment = (Appointment) target;
        
        // inv: self.startTime < self.endTime
        if (appointment.getStartTime() != null && appointment.getEndTime() != null 
            && !appointment.getStartTime().before(appointment.getEndTime())) {
            errors.rejectValue("endTime", "ocl.constraint.timeSequence", 
                              "OCL: self.startTime < self.endTime");
        }
        
        // inv: not self.subject.oclIsUndefined() and self.subject.size() > 0
        if (appointment.getSubject() == null || appointment.getSubject().trim().isEmpty()) {
            errors.rejectValue("subject", "ocl.constraint.subjectRequired", 
                              "OCL: not self.subject.oclIsUndefined() and self.subject.size() > 0");
        }
        
        // inv: not self.employee.oclIsUndefined() and not self.room.oclIsUndefined()
        if (appointment.getEmployee() == null) {
            errors.rejectValue("employee", "ocl.constraint.employeeRequired", 
                              "OCL: not self.employee.oclIsUndefined()");
        }
        
        if (appointment.getRoom() == null) {
            errors.rejectValue("room", "ocl.constraint.roomRequired", 
                              "OCL: not self.room.oclIsUndefined()");
        }
    }
}