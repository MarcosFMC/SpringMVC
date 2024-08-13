package com.example.DentalClinicMVC.repository;

import com.example.DentalClinicMVC.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
