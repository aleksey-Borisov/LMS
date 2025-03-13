package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.model.Student;

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(LmsApplication.class, args);
		Student student = new Student();
		student.setFirstName("Test"); // Сгенерированный метод
			student.setLastName("User");

		// Выведет "Test User"
		System.out.println(student.getFirstName() + " " + student.getLastName());

	}

}
