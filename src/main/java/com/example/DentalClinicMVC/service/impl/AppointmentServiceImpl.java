package com.example.DentalClinicMVC.service.impl;

import com.example.DentalClinicMVC.entity.Appointment;
import com.example.DentalClinicMVC.entity.Dentist;
import com.example.DentalClinicMVC.entity.Patient;
import com.example.DentalClinicMVC.entity.dto.AppointmentDTO;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;
import com.example.DentalClinicMVC.repository.AppointmentRepository;
import com.example.DentalClinicMVC.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO){

        Appointment appointmentEntity = new Appointment();

        Dentist dentistEntity = new Dentist();
        dentistEntity.setId(appointmentDTO.getDentist_id());

        Patient patientEntity = new Patient();
        patientEntity.setId(appointmentDTO.getPatient_id());

        appointmentEntity.setPatient(patientEntity);
        appointmentEntity.setDentist(dentistEntity);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(appointmentDTO.getDate(),dateTimeFormatter);

        appointmentEntity.setDate(date);

        Appointment appointmentDB = appointmentRepository.save(appointmentEntity);

        AppointmentDTO appointmentDTOtoReturn = new AppointmentDTO();

        appointmentDTOtoReturn.setId(appointmentDB.getId());
        appointmentDTOtoReturn.setDate(appointmentDB.getDate().toString());
        appointmentDTOtoReturn.setDentist_id(appointmentDB.getDentist().getId());
        appointmentDTOtoReturn.setPatient_id(appointmentDB.getPatient().getId());

        return appointmentDTOtoReturn;
    }
    @Override
    public Optional<AppointmentDTO> findById(Long id){

        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);

        Optional<AppointmentDTO> appointmentDTOOptional = Optional.empty();

        if(appointmentOptional.isPresent()){

            Appointment appointment = appointmentOptional.get();

            AppointmentDTO appointmentDTO = new AppointmentDTO();

            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setPatient_id(appointment.getPatient().getId());
            appointmentDTO.setDentist_id(appointment.getDentist().getId());
            appointmentDTO.setDate(appointment.getDate().toString());

            appointmentDTOOptional = Optional.of(appointmentDTO);
        }
        return appointmentDTOOptional;
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDTO.getId());

        if (appointmentOptional.isPresent()){

            Appointment appointmentEntity = appointmentOptional.get();

            Dentist dentistEntity = new Dentist();
            dentistEntity.setId(appointmentDTO.getDentist_id());

            Patient patientEntity = new Patient();
            patientEntity.setId(appointmentDTO.getPatient_id());

            appointmentEntity.setPatient(patientEntity);
            appointmentEntity.setDentist(dentistEntity);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(appointmentDTO.getDate(),dateTimeFormatter);

            appointmentEntity.setDate(date);

            Appointment appointmentDB = appointmentRepository.save(appointmentEntity);

            AppointmentDTO appointmentDTOtoReturn = new AppointmentDTO();

            appointmentDTOtoReturn.setId(appointmentDB.getId());
            appointmentDTOtoReturn.setDate(appointmentDB.getDate().toString());
            appointmentDTOtoReturn.setDentist_id(appointmentDB.getDentist().getId());
            appointmentDTOtoReturn.setPatient_id(appointmentDB.getPatient().getId());

            return appointmentDTOtoReturn;
        } else {
            throw new Exception("No se pudo actualizar el turno");
        }
    }
    @Override
    public List<AppointmentDTO> findAll(){

        List<Appointment> appointmentList = new ArrayList<>();

        appointmentList = appointmentRepository.findAll();

        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();

        for(Appointment a : appointmentList){

            AppointmentDTO appointmentDTO = new AppointmentDTO();

            appointmentDTO.setId(a.getId());
            appointmentDTO.setDentist_id(a.getDentist().getId());
            appointmentDTO.setPatient_id(a.getPatient().getId());
            appointmentDTO.setDate(a.getDate().toString());

            appointmentDTOList.add(appointmentDTO);
        }


        return appointmentDTOList;
    }

    @Override
    public Optional<AppointmentDTO> delete(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);

        Optional<AppointmentDTO> appointmentDTOOptional = Optional.empty();
        if(appointmentOptional.isPresent()){
            Appointment appointment = appointmentOptional.get();

            AppointmentDTO appointmentDTO = new AppointmentDTO();

            appointmentDTO.setId(appointment.getId());
            appointmentDTO.setDate(appointment.getDate().toString());
            appointmentDTO.setPatient_id(appointment.getPatient().getId());
            appointmentDTO.setDentist_id(appointment.getDentist().getId());

            appointmentDTOOptional = Optional.of(appointmentDTO);
        }else {
            throw new ResourceNotFoundException("No se encontro el turno con el id: " + id);
        }
        return appointmentDTOOptional;
    }
}
