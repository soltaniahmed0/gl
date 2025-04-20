package com.example.Backend.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Backend.Entity.StartupEvent;

import Factory.EmployeeService;
import strateg_polymorphism.NotificationService;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    EventService eventService;
    @Autowired
    EmployeeService employeeService;
    @PostMapping("addEvent/{employeeId}")
    public ResponseEntity<String> addEvent(@RequestBody StartupEvent startupEvent, @PathVariable int employeeId){
        try {
            eventService.addEvent(startupEvent,employeeId);
            return  ResponseEntity.ok("added");
        }
        catch (Exception e){
            return  new ResponseEntity<>("Error addidng the startupEvent"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("GetEvent/{event_id}")
    public ResponseEntity<Optional<StartupEvent>> getEvent(@PathVariable int event_id){
        try {
            Optional<StartupEvent> event=eventService.getEvent(event_id);
            return ResponseEntity.ok(event);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("updateEvent/{event_id}")
    public ResponseEntity<?> updateEvent(@PathVariable int event_id,@RequestBody StartupEvent updatedEvent){
        try {
            StartupEvent startupEvent =eventService.updateEvent(updatedEvent,event_id);
            return ResponseEntity.ok(startupEvent);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("getallEvents")
    public ResponseEntity<List<StartupEvent>>getAllEvents(){
        try {
            List<StartupEvent> startupEventList =eventService.getAllEvents();
            return ResponseEntity.ok(startupEventList);
        }
        catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("deleteEvent/{event_id}")
    public ResponseEntity<String>deletEvent(@PathVariable int event_id){
        try {
            eventService.deleteEvent(event_id);
            return ResponseEntity.noContent().build();

        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("updateEventApproval/{eventId}")
    public ResponseEntity<Void> updateEventApproval(@PathVariable int eventId, @RequestParam boolean approve) {
        try {
            eventService.updateEventApproval(eventId, approve);
            Optional<StartupEvent> event=eventService.getEvent(eventId);
            //notificationService.sendNotificationToAll("Eventalll", event.get().getEventName(),event.get().getEmployee().getDeviceToken());
            notificationService.sendNotification("Event Approved",event.get().getEventName(),event.get().getEmployee().getDeviceToken());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("getEvents")
    public ResponseEntity<List<StartupEvent>> getEvents() {
        try {
            List<StartupEvent> events = eventService.getAllEvents().stream()
                    .filter(event -> event.isApprove())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getEmployeeEvent/{id}")
    public ResponseEntity<List<StartupEvent>> getEventList(@PathVariable int id)
    {
        try {
            List<StartupEvent> events=eventService.getEmployeeOnlyEvents(id);
            return  ResponseEntity.ok(events);
        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}