package com.github.djroush.demo.backend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.djroush.demo.backend.dao.DoctorDao;
import com.github.djroush.demo.backend.model.Doctor;

@Service
public class DoctorApi {
    @Autowired
    private DoctorDao doctorDao;

    public Doctor persist(Doctor doctor) {
        return doctorDao.persist(doctor);
    }

    //FIXME: come back and fix this later
    public List<Doctor> findAll() {
        return doctorDao.getAll();
    }
    
//    public void remove(int id) {
//    	doctorDao.delete(id);
//    }
}
