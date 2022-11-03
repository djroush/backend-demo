package com.github.djroush.demo.backend.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.djroush.demo.backend.model.Appointment;

@Repository
public class AppointmentDao {
    private static int SEQUENCE = 6;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_PATIENT_SQL = 
      "INSERT INTO TEST.APPOINTMENT(ID, DOCTOR_ID, FIRST_NAME, LAST_NAME, APPT_DATE, APPT_TIME, NEW_PATIENT, DELETED) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_APPOINTMENTS_BY_DATE_AND_DOCTOR_SQL = 
      "SELECT ID, DOCTOR_ID, FIRST_NAME, LAST_NAME, APPT_DATE, APPT_TIME, NEW_PATIENT, DELETED " + 
      "  FROM TEST.APPOINTMENT " + 
      " WHERE APPT_DATE = ? " + 
      "   AND DOCTOR_ID = ? " + 
      "   AND DELETED = false";
    
    private static final String DELETE_PATIENT_SQL = 
      "UPDATE TEST.APPOINTMENT SET DELETED=true WHERE ID = ?";

	private RowMapper<Appointment> appointmentRM = new RowMapper<Appointment>(){
	  @Override
	  public Appointment mapRow(ResultSet rs,int rowNum) throws SQLException{
	      Appointment appointment=new Appointment();
	      appointment.setId(rs.getInt("ID"));
	      appointment.setDoctorId(rs.getInt("DOCTOR_ID"));
	      appointment.setFirstName(rs.getString("FIRST_NAME"));
	      appointment.setLastName(rs.getString("LAST_NAME"));
	      appointment.setApptDate(rs.getDate("APPT_DATE").toLocalDate());
	      appointment.setApptTime(rs.getTime("APPT_TIME").toLocalTime());
	      appointment.setNewPatient(rs.getBoolean("NEW_PATIENT"));
	      appointment.setDeleted(rs.getBoolean("DELETED"));
	      return appointment;
	  }
	};
    
    public Appointment persist(Appointment appointment) {
    	appointment.setId(SEQUENCE++);
    	try (final Connection connection = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection()) {
	      final Date apptDate = Date.valueOf(appointment.getApptDate());
	      final Time apptTime = Time.valueOf(appointment.getApptTime());

    	  final PreparedStatement appointmentPS = connection.prepareStatement(INSERT_PATIENT_SQL);
          appointmentPS.setInt(1, appointment.getId());
          appointmentPS.setInt(2, appointment.getDoctorId());
          appointmentPS.setString(3, appointment.getFirstName());
          appointmentPS.setString(4, appointment.getLastName());
          appointmentPS.setDate(5, apptDate);
          appointmentPS.setTime(6,  apptTime);
          appointmentPS.setBoolean(7, appointment.isNewPatient());
          appointmentPS.setBoolean(8, appointment.isDeleted());
          appointmentPS.execute();
          return appointment;
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return null;
    }
    
    public List<Appointment> getByDateAndDoctor(LocalDate apptDate, int doctorId) {
    	try {
    	  final List<Appointment> appointments= jdbcTemplate.getJdbcTemplate().query(SELECT_APPOINTMENTS_BY_DATE_AND_DOCTOR_SQL, 
    	    appointmentRM, apptDate, doctorId);
    	  return appointments;    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    public void delete(int patientId) {
    	try (final Connection connection = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection()) {
          final PreparedStatement patientPS = connection.prepareStatement(DELETE_PATIENT_SQL);
          patientPS.setInt(1, patientId);
          patientPS.execute();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }
}
