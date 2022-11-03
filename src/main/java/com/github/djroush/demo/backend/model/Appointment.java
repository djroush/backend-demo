package com.github.djroush.demo.backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
	private int id;
	private int doctorId;
	private String firstName;
	private String lastName;
	private LocalDate apptDate;
	private LocalTime apptTime;
	private boolean isNewPatient;
	private boolean isDeleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getApptDate() {
		return apptDate;
	}
	public void setApptDate(LocalDate apptDate) {
		this.apptDate = apptDate;
	}
	public LocalTime getApptTime() {
		return apptTime;
	}
	public void setApptTime(LocalTime apptTime) {
		this.apptTime = apptTime;
	}
	public boolean isNewPatient() {
		return isNewPatient;
	}
	public void setNewPatient(boolean isNewPatient) {
		this.isNewPatient = isNewPatient;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
