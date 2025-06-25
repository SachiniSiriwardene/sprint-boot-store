package com.example.store;

import com.example.store.entities.Address;
import com.example.store.entities.Profile;
import com.example.store.entities.Tag;
import com.example.store.entities.User;
import com.example.store.repositories.UserRepository;
import com.example.store.services.ProductService;
import com.example.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext context  = SpringApplication.run(StoreApplication.class, args);
		var service =  context.getBean(UserService.class);
		var productService = context.getBean(ProductService.class);
		service.fetchProductsByCriteria();

//		var orderService = context.getBean(OrderService.class);
//		orderService.placeOrder();

//		var notificationManager = context.getBean(NotificationManager.class);
//		notificationManager.sendNotification("Hello");


//		user.addTag("tag1");
//
//		var profile = Profile.builder()
//				.bio("bio")
//				.build();
//		user.setProfile(profile);
//		profile.setUser(user);
//
//		var address = Address.builder()
//				.street("street")
//				.city("city")
//				.state("state")
//				.zip("zip")
//				.build();
//
//		user.addAddress(address);
//
//		System.out.println(user);
	}

}
