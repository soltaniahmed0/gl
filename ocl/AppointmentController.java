package com.votre.package.controller;

import com.votre.package.Appointment;
import com.votre.package.service.AppointmentService;
import com.votre.package.validator.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private AppointmentValidator appointmentValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(appointmentValidator);
    }
    
    @PostMapping
    public ResponseEntity<?> createAppointment(@Valid @RequestBody Appointment appointment, 
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        
        Appointment savedAppointment = appointmentService.save(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }
    
    @Autowired
    private NotificationService notificationService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService=appointmentService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getitem")
    public Appointment getItem(@RequestParam int id){
         return appointmentService.getappointment(id);
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/roomappointments/{roomId}")
    public List<Appointment> getAppointmentsByRoomId(@PathVariable int roomId) {
        return appointmentService.getAppointmentsByRoomId(roomId);
    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getitems")

    public List<Appointment> getItems(){
        List<Appointment> res=appointmentService.getappointments();
        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Appointment appointment ){
        appointmentService.addappointment(appointment);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Appointment appointment ){
        appointmentService.updateAppointment(appointment);

    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/Approve")
    public void Approve(@RequestBody Appointment appointment ){

        notificationService.sendNotification("Reservation ", appointment.getRoom().getRoom_name()+" : "+appointment.getSubject(),appointment.getEmployee().getDeviceToken());
        appointmentService.updateAppointment(appointment);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        appointmentService.deleteappointment(id);

    }
    @GetMapping("/currentlyAppoinment/{userId}")
    public ResponseEntity<List<Appointment>> getCurrentlyHappeningAppointments(@PathVariable int userId) {
        List<Appointment> currentlyHappeningAppointments = appointmentService.getCurrentlyHappeningAppointmentsForUser(userId);
        return ResponseEntity.ok(currentlyHappeningAppointments);
    }

}
