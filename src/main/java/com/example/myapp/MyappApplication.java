//package com.example.myapp;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@SpringBootApplication
//public class MyappApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(MyappApplication.class, args);
//	}
//	@Configuration
//	public static class Myconfiguration{
//		@Bean
//		public WebMvcConfigurer corsConfigurer(){
//			return new WebMvcConfigurer() {
//
//				@Override
//				public void addCorsMappings(CorsRegistry registry){
//					registry.addMapping("/**")
//					 .allowedMethods("HEAD", "GET", "PUT","POST","DELETE","PATCH" );
//				}
//			};
//		}
//	}

//}


package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		// Crear el directorio 'uploads' si no existe
		File uploadsDir = new File("uploads");
		if (!uploadsDir.exists()) {
			uploadsDir.mkdir(); // Crear el directorio 'uploads'
			System.out.println("Directorio 'uploads' creado con éxito.");
		} else {
			System.out.println("Directorio 'uploads' ya existe.");
		}

		SpringApplication.run(MyappApplication.class, args);
	}

	@Configuration
	public static class Myconfiguration {

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {

				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
				}
			};
		}
	}
}

