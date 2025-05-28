package com.example.store;

import com.example.store.entities.Address;
import com.example.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context  = SpringApplication.run(StoreApplication.class, args);
//		var orderService = context.getBean(OrderService.class);
//		orderService.placeOrder();

//		var notificationManager = context.getBean(NotificationManager.class);
//		notificationManager.sendNotification("Hello");

		//var user = new User(1L, "Sachini", "email", "passs");
		var user = User.builder()
				.name("Sachi")
				.email("sachinisiriwardene@gmail.com")
				.password("pass")
				.build();

		var address = Address.builder()
				.street("street")
				.city("city")
				.state("state")
				.zip("zip")
				.build();

		user.addAddress(address);

		System.out.println(user);
	}

}
