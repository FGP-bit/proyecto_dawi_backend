package com.cibertec.mshistorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.cibertec.gestionmedica.entity"})
public class MsHistorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHistorialApplication.class, args);
	}

}
