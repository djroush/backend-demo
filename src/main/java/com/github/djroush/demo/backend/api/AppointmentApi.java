package com.github.djroush.demo.backend.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.djroush.demo.backend.dao.AppointmentDao;
import com.github.djroush.demo.backend.exception.ConcurrentSchedulingException;
import com.github.djroush.demo.backend.model.Appointment;

@Service
public class AppointmentApi {
    @Autowired
    private AppointmentDao appointmentDao;

    public Appointment persist(Appointment appointment) throws Exception {
    	final LocalDate apptDate = appointment.getApptDate();
    	final int doctorId = appointment.getDoctorId();
    	
    	final List<Appointment> existingAppts = appointmentDao.getByDateAndDoctor(apptDate, doctorId);
    	if (existingAppts.size() >= 3) {
    		throw new ConcurrentSchedulingException("Appointment cannot be created at this time as this doctor already has multiple appointments scheduled");
    	}
    	
        return appointmentDao.persist(appointment);
    }

    public List<Appointment> findByDateAndDoctor(LocalDate date, int doctorId) {
        return appointmentDao.getByDateAndDoctor(date, doctorId);
    }

    public void remove(int patientId) {
    	appointmentDao.delete(patientId);
    }
}
