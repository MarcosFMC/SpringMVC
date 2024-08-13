package com.example.DentalClinicMVC.controller;

import com.example.DentalClinicMVC.entity.Dentist;
import com.example.DentalClinicMVC.entity.dto.AppointmentDTO;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;
import com.example.DentalClinicMVC.service.impl.AppointmentServiceImpl;
import com.example.DentalClinicMVC.service.impl.DentistServiceImpl;
import com.example.DentalClinicMVC.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class AppointmentController {
    private AppointmentServiceImpl appointmentService;
    private DentistServiceImpl dentistService;
    private PatientServiceImpl patientService;
    @Autowired
    public AppointmentController(AppointmentServiceImpl appointmentService, DentistServiceImpl dentistService, PatientServiceImpl patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll(){
        return ResponseEntity.ok(appointmentService.findAll());
    }
    @PostMapping
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTOParam){

        if(dentistService.findById(appointmentDTOParam.getDentist_id()).isPresent()
                &&  patientService.findById(appointmentDTOParam.getPatient_id()).isPresent()){
            return ResponseEntity.ok(appointmentService.save(appointmentDTOParam));
        }else {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id){

        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.findById(id);

        if(appointmentDTOOptional.isPresent()){
            return ResponseEntity.ok(appointmentDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO) throws Exception{

        ResponseEntity<AppointmentDTO> response;
        Optional<AppointmentDTO> appointmentDTOOptional = appointmentService.findById(appointmentDTO.getId());

        if(appointmentDTOOptional.isPresent()){
            appointmentService.update(appointmentDTO);
            response = ResponseEntity.ok(appointmentDTOOptional.get());
        }
        else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {

        appointmentService.delete(id);
        return ResponseEntity.ok("Se elimino el turno con el id:" + id);
    }
}
