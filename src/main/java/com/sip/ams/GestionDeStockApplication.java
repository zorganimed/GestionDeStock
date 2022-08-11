package com.sip.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.sip.ams.controllers.ArticlesController;
import java.io.File;

@SpringBootApplication
public class GestionDeStockApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		new File(ArticlesController.uploadDirectory).mkdir();
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

}
