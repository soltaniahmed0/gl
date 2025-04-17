package com.example.Backend.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.StartupEvent;
import com.example.Backend.Repository.EmployeeRepository;
import com.example.Backend.Repository.EventRepository;
import com.example.Backend.DTO.StartupEventDTO;
import com.example.Backend.Mapper.StartupEventMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private StartupEventMapper eventMapper;
    
    @Autowired
    private NotificationService notificationService;

    public StartupEventDTO addEvent(StartupEventDTO eventDTO, int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + employeeId + " not found"));
        
        StartupEvent event = eventMapper.toEntity(eventDTO, employee);
        StartupEvent savedEvent = eventRepository.save(event);
        
        return eventMapper.toDTO(savedEvent);
    }

    public StartupEventDTO getEvent(int eventId) {
        StartupEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with ID " + eventId + " not found"));
        
        return eventMapper.toDTO(event);
    }

    public StartupEventDTO updateEvent(StartupEventDTO eventDTO, int eventId) {
        StartupEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with ID " + eventId + " not found"));
        
        Employee employee = employeeRepository.findById(eventDTO.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + eventDTO.getEmployeeId() + " not found"));
        
        // Update event fields
        event.setEventName(eventDTO.getEventName());
        event.setEventDescription(eventDTO.getEventDescription());
        event.setEventDate(eventDTO.getEventDate());
        event.setEventStartTime(eventDTO.getEventStartTime());
        event.setEventEndTime(eventDTO.getEventEndTime());
        event.setLocation(eventDTO.getLocation());
        event.setHost(eventDTO.getHost());
        event.setPrice(eventDTO.getPrice());
        event.setApprove(eventDTO.isApprove());
        event.setEventimg(eventDTO.getEventimg());
        
        StartupEvent updatedEvent = eventRepository.save(event);
        return eventMapper.toDTO(updatedEvent);
    }

    public List<StartupEventDTO> getAllEvents() {
        List<StartupEvent> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String deleteEvent(int eventId) {
        try {
            eventRepository.deleteById(eventId);
            return "deleted";
        } catch (Exception e) {
            return "not deleted";
        }
    }

    public void updateEventApproval(int eventId, boolean approve) {
        StartupEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event id"));
        
        event.setApprove(approve);
        eventRepository.save(event);
        
        if (approve) {
            notificationService.sendNotification(
                "Event Approved",
                event.getEventName(),
                event.getEmployee().getDeviceToken()
            );
        }
    }

    public List<StartupEventDTO> getApprovedEvents() {
        List<StartupEvent> events = eventRepository.findAll();
        return events.stream()
                .filter(StartupEvent::isApprove)
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<StartupEventDTO> getEmployeeEvents(int employeeId) {
        List<StartupEvent> events = eventRepository.findByEmployeeId(employeeId);
        return events.stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }
}