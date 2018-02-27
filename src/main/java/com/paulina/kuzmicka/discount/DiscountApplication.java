package com.paulina.kuzmicka.discount;

import com.paulina.kuzmicka.discount.ui.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscountApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountApplication.class, args);
		UserInterface ui = new UserInterface();
		ui.runProgram();
	}
}
