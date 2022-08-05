package com.sip.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sip.ams.controllers.ArticlesController;
import java.io.File;

@SpringBootApplication
public class GestionDeStockApplication {

	public static void main(String[] args) {
		new File(ArticlesController.uploadDirectory).mkdir();
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

}
