package dev.coworking;

import dev.coworking.service.InitalServices.InitialBookings;
import dev.coworking.service.InitalServices.InitialTestManager;
import dev.coworking.service.InitalServices.InitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CoworkingApplication {

	@Autowired
	private static InitialBookings initialBookings;

	@Autowired
	public void setInitiatorLoader(InitialBookings initiator) {
		CoworkingApplication.initialBookings =initiator;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CoworkingApplication.class, args);
		//initialBookings.init();
	}
}
