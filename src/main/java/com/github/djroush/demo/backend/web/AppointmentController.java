package com.github.djroush.demo.backend.web;


import static com.github.djroush.demo.backend.util.StringUtil.isBlank;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.djroush.demo.backend.api.AppointmentApi;
import com.github.djroush.demo.backend.exception.InvalidRequestException;
import com.github.djroush.demo.backend.model.Appointment;


@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentApi appointmentApi;
    
    @GetMapping(path="/{apptDate}/{doctorId}")
    public ResponseEntity<List<Appointment>> findById(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate apptDate, @PathVariable int doctorId) {
    	final List<Appointment> appointments = appointmentApi.findByDateAndDoctor(apptDate, doctorId);
    	return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
    }

    @PostMapping(consumes={"application/json"}, produces={"application/json"}, path="")
    public ResponseEntity<?> createAppointment(@RequestBody Appointment patient) throws Exception {
    	if (!isValid(patient)) {
    		throw new InvalidRequestException("Request has missing or incorrect data for required fields.");
    	}
    	final Appointment insertedAppointment = appointmentApi.persist(patient);
    	final ResponseEntity<Appointment> entity = new ResponseEntity<>(insertedAppointment, HttpStatus.CREATED);
    	return entity;
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> removeById(@PathVariable int id) {
    	appointmentApi.remove(id);
    	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private boolean isValid(Appointment appointment) {
        return appointment != null && 
        	!isBlank(appointment.getFirstName()) &&
        	!isBlank(appointment.getLastName()) &&
        	appointment.getApptDate() != null;
    }
    
    
    

}
