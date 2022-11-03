package com.github.djroush.demo.backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.djroush.demo.backend.api.DoctorApi;
import com.github.djroush.demo.backend.exception.InvalidRequestException;
import com.github.djroush.demo.backend.model.Doctor;

import static com.github.djroush.demo.backend.util.StringUtil.isBlank;


@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    private DoctorApi doctorApi;

    @GetMapping(path="/list")
    public ResponseEntity<List<Doctor>> getDoctors() {
    	List<Doctor>  doctors = doctorApi.findAll();
    	return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
    }

    
//    @GetMapping(path="/{id}")
//    public ResponseEntity<Appointment> findById(@PathVariable int id) {
//    	final Appointment patient = patientApi.findScan(id);
//    	return new ResponseEntity<Appointment>(patient, HttpStatus.OK);
//    }

    @PostMapping(consumes={"application/json"}, produces={"application/json"}, path="")
    public ResponseEntity<Doctor> uploadFile(@RequestBody Doctor doctor) throws Exception {
    	if (!isValid(doctor)) {
    		throw new InvalidRequestException("Request has missing or incorrect data for required fields.");
    	}
    	final Doctor insertedDoctor = doctorApi.persist(doctor);
    	final ResponseEntity<Doctor> entity = new ResponseEntity<>(insertedDoctor, HttpStatus.CREATED);
    	return entity;
    }
    
    private boolean isValid(Doctor doctor) {
        return doctor != null && 
        	!isBlank(doctor.getFirstName()) &&
        	!isBlank(doctor.getLastName()) &&
        	!isBlank(doctor.getEmail());
    }
}
