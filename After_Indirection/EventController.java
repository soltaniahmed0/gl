package com.example.Backend.Controller;

import com.example.Backend.DTO.StartupEventDTO;
import com.example.Backend.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @PostMapping("addEvent/{employeeId}")
    public ResponseEntity<String> addEvent(@RequestBody StartupEventDTO eventDTO, @PathVariable int employeeId) {
        try {
            eventService.addEvent(eventDTO, employeeId);
            return ResponseEntity.ok("added");
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding the event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("GetEvent/{eventId}")
    public ResponseEntity<StartupEventDTO> getEvent(@PathVariable int eventId) {
        try {
            StartupEventDTO event = eventService.getEvent(eventId);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("updateEvent/{eventId}")
    public ResponseEntity<StartupEventDTO> updateEvent(@PathVariable int eventId, @RequestBody StartupEventDTO updatedEventDTO) {
        try {
            StartupEventDTO eventDTO = eventService.updateEvent(updatedEventDTO, eventId);
            return ResponseEntity.ok(eventDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("getallEvents")
    public ResponseEntity<List<StartupEventDTO>> getAllEvents() {
        try {
            List<StartupEventDTO> eventDTOList = eventService.getAllEvents();
            return ResponseEntity.ok(eventDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("deleteEvent/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable int eventId) {
        try {
            eventService.deleteEvent(eventId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("updateEventApproval/{eventId}")
    public ResponseEntity<Void> updateEventApproval(@PathVariable int eventId, @RequestParam boolean approve) {
        try {
            eventService.updateEventApproval(eventId, approve);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("getEvents")
    public ResponseEntity<List<StartupEventDTO>> getApprovedEvents() {
        try {
            List<StartupEventDTO> events = eventService.getApprovedEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getEmployeeEvent/{id}")
    public ResponseEntity<List<StartupEventDTO>> getEmployeeEvents(@PathVariable int id) {
        try {
            List<StartupEventDTO> events = eventService.getEmployeeEvents(id);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}