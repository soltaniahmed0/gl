package com.example.Backend.Mapper;

import org.springframework.stereotype.Component;

import com.example.Backend.DTO.StartupEventDTO;
import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.StartupEvent;

@Component
public class StartupEventMapper {

    public StartupEventDTO toDTO(StartupEvent event) {
        String employeeFullName = null;
        int employeeId = 0;
        
        if (event.getEmployee() != null) {
            employeeId = event.getEmployee().getId();
            employeeFullName = event.getEmployee().getFirstName() + " " + event.getEmployee().getLastName();
        }

        return new StartupEventDTO(
                event.getEventId(),
                event.getEventName(),
                event.getEventDescription(),
                event.getEventDate(),
                event.getEventStartTime(),
                event.getEventEndTime(),
                event.getLocation(),
                event.getHost(),
                event.getPrice(),
                event.isApprove(),
                event.getEventimg(),
                employeeId,
                employeeFullName
        );
    }

    public StartupEvent toEntity(StartupEventDTO dto, Employee employee) {
        StartupEvent event = new StartupEvent();
        event.setEventId(dto.getEventId());
        event.setEventName(dto.getEventName());
        event.setEventDescription(dto.getEventDescription());
        event.setEventDate(dto.getEventDate());
        event.setEventStartTime(dto.getEventStartTime());
        event.setEventEndTime(dto.getEventEndTime());
        event.setLocation(dto.getLocation());
        event.setHost(dto.getHost());
        event.setPrice(dto.getPrice());
        event.setApprove(dto.isApprove());
        event.setEventimg(dto.getEventimg());
        event.setEmployee(employee);
        
        return event;
    }
}