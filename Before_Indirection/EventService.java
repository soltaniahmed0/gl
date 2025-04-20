package com.example.Backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.StartupEvent;
import com.example.Backend.Repository.EmployeeRepository;
import com.example.Backend.Repository.EventRepository;

import Factory.Employee;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public StartupEvent addEvent(StartupEvent startupEvent, int employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException("Employee with ID " + employeeId + " not found"));
        startupEvent.setEmployee(employee);
        return eventRepository.save(startupEvent);
    }
    public Optional<StartupEvent> getEvent(int event_id){
        StartupEvent startupEvent =eventRepository.findById(event_id).orElseThrow(() ->
                new EntityNotFoundException("Event wiht Id"+event_id+"not found"));
        return Optional.ofNullable(startupEvent);
    }
    public StartupEvent updateEvent(StartupEvent startupEvent, int event_id){
        StartupEvent event=eventRepository.findById(event_id).orElseThrow(() ->
                new EntityNotFoundException("Event wiht Id"+event_id+"not found"));
        event.setEventName(startupEvent.getEventName());
        event.setEventDescription(startupEvent.getEventDescription());
        event.setEventDate(startupEvent.getEventDate());
        event.setEventStartTime(startupEvent.getEventStartTime());
        event.setEventEndTime(startupEvent.getEventEndTime());
        event.setLocation(startupEvent.getLocation());
        event.setApprove(startupEvent.isApprove());
        event.setEventimg(startupEvent.getEventimg());
        eventRepository.save(event);
        return  event;
    }
    public List<StartupEvent>getAllEvents(){
        List<StartupEvent> events=eventRepository.findAll();
        return events;
    }
    public String deleteEvent(int event_id){
        try{
            eventRepository.deleteById(event_id);
            return "deleted";
        }

        catch (Exception e)
        {
            return "not deleted";
        }
    }
    public void updateEventApproval(int eventId, boolean approve) {
        StartupEvent event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event id"));
        event.setApprove(approve);
        eventRepository.save(event);
    }


    public List<StartupEvent> getEmployeeOnlyEvents(int id)
    {
        List<StartupEvent> events=eventRepository.findByEmployeeId(id);
        return  events;
    }
}