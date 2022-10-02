package com.bolsaideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bolsaideas.springboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SrpingBootDataJpaApplication implements CommandLineRunner{
	
	@Autowired
	IUploadFileService uploadFileService;
	

	public static void main(String[] args) {
		SpringApplication.run(SrpingBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
		
	}

}
