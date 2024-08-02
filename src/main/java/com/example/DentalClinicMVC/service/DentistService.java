package com.example.DentalClinicMVC.service;

import com.example.DentalClinicMVC.dao.DentistDao;
import com.example.DentalClinicMVC.dao.IDao;
import com.example.DentalClinicMVC.model.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {

    private IDao<Dentist> dentistIDao;

    public DentistService() {
        this.dentistIDao = new DentistDao();
    }

    public Dentist save(Dentist dentist){
        return dentistIDao.save(dentist);
    }

    public Dentist findById(Integer id){
        return dentistIDao.findById(id);
    }

    public void update(Dentist dentist){
        dentistIDao.update(dentist);
    }
    public void delete(Integer id){
        dentistIDao.delete(id);
    }
    public List<Dentist> findAll(){
        return dentistIDao.findAll();
    }
}
