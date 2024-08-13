package com.example.DentalClinicMVC.service;

import com.example.DentalClinicMVC.entity.Appointment;
import com.example.DentalClinicMVC.entity.dto.AppointmentDTO;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
     AppointmentDTO save(AppointmentDTO appointmentDTO);
     Optional<AppointmentDTO> findById(Long id);
     AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception;
     List<AppointmentDTO> findAll();
     Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException;

}
