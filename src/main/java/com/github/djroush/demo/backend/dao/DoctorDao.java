package com.github.djroush.demo.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.djroush.demo.backend.model.Doctor;

@Repository
public class DoctorDao {
	private static int SEQUENCE = 4;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String INSERT_DOCTOR_SQL = "INSERT INTO TEST.DOCTOR(ID, FIRST_NAME, LAST_NAME, EMAIL, ACTIVE) VALUES(?, ?, ?, ?, ?)";

	private static final String SELECT_DOCTORS_SQL = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, ACTIVE FROM TEST.DOCTOR WHERE ACTIVE=true";

	private RowMapper<Doctor> doctorRM = new RowMapper<>() {
		@Override
		public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Doctor doctor = new Doctor();
			doctor.setId(rs.getInt("ID"));
			doctor.setFirstName(rs.getString("FIRST_NAME"));
			doctor.setLastName(rs.getString("LAST_NAME"));
			doctor.setEmail(rs.getString("EMAIL"));
			doctor.setActive(rs.getBoolean("ACTIVE"));
			return doctor;
		}
	};

	public Doctor persist(Doctor doctor) {
		doctor.setId(SEQUENCE++);
		try (final Connection connection = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection()) {
			final PreparedStatement doctorPS = connection.prepareStatement(INSERT_DOCTOR_SQL);
			doctorPS.setInt(1, doctor.getId());
			doctorPS.setString(2, doctor.getFirstName());
			doctorPS.setString(3, doctor.getLastName());
			doctorPS.setString(4, doctor.getEmail());
			doctorPS.setBoolean(5, true);
			doctorPS.execute();

			return doctor;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Doctor> getAll() {
		try {
			final List<Doctor> doctors = jdbcTemplate.getJdbcTemplate().query(SELECT_DOCTORS_SQL, doctorRM);
			return doctors;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
