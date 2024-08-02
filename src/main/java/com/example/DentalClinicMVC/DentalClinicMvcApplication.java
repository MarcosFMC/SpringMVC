package com.example.DentalClinicMVC;

import com.example.DentalClinicMVC.dao.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DentalClinicMvcApplication {

	public static void main(String[] args) {
		DB.createTables();
		SpringApplication.run(DentalClinicMvcApplication.class, args);
	}

}
