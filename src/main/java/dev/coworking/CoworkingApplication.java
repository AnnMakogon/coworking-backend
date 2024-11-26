package dev.coworking;

import dev.coworking.service.InitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CoworkingApplication {

	@Autowired
	private static InitializerService initializerService;

	@Autowired
	public void setInitiatorLoader(InitializerService initiator) {
		CoworkingApplication.initializerService =initiator;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CoworkingApplication.class, args);
		//initializerService.init();
	}
}
