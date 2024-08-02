package com.example.DentalClinicMVC.controller;


import com.example.DentalClinicMVC.model.Dentist;
import com.example.DentalClinicMVC.model.Patient;
import com.example.DentalClinicMVC.service.DentistService;
import com.example.DentalClinicMVC.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

    private PatientService patientService;

    private DentistService dentistService;

    public IndexController(PatientService patientService, DentistService dentistService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @GetMapping
    public String findPatientByEmail(Model model, @RequestParam("email") String email, @RequestParam("id") Integer id){
        Patient patient = patientService.findByEmail(email);
        Dentist dentist = dentistService.findById(id);

        model.addAttribute("name",patient.getName());
        model.addAttribute("lastname",patient.getLastName());

        model.addAttribute("nameDentist",dentist.getName());
        model.addAttribute("lastNameDentist",dentist.getLastName());
        model.addAttribute("registration",dentist.getRegistration());
        return "index";
    }
}
