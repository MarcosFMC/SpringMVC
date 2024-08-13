package com.example.DentalClinicMVC.controller;

import com.example.DentalClinicMVC.entity.Dentist;
import com.example.DentalClinicMVC.service.IDentistService;
import com.example.DentalClinicMVC.service.impl.DentistServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.class)
class DentistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IDentistService dentistService;

    public void dataLoad(){
        Dentist dentist = new Dentist();
        dentist.setRegistration(123);
        dentist.setName("Marcos");
        dentist.setLastName("Mendoza");
        dentistService.save(dentist);
    }
    @Test
    @Order(3    )
    public void getDentistById() throws Exception {
        dataLoad();
        mockMvc.perform(get("/odontologos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registration").value("123"))
                .andExpect(jsonPath("$.name").value("Marcos"))
                .andExpect(jsonPath("$.lastName").value("Mendoza"));

    }

    @Test
    @Order(2)
    public void postDentist() throws Exception {

        String dentistSaved = "{  \"registration\": \"125\",  " +
                                  "\"name\": \"Marqui\"," +
                                  "\"lastName\": \"Guzman\"}";

        mockMvc.perform(post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dentistSaved)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.registration").value("125"))
                        .andExpect(jsonPath("$.name").value("Marqui"))
                        .andExpect(jsonPath("$.lastName").value("Guzman"));

    }

    @Test
    @Order(1)
    public void getAllDentist() throws Exception {
        mockMvc.perform(get("/odontologos")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}