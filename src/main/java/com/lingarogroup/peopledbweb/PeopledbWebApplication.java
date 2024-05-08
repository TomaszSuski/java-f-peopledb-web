package com.lingarogroup.peopledbweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The PeopledbWebApplication class is the entry point of the Spring Boot application.
 * It is annotated with @SpringBootApplication, which is a convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'com.lingarogroup.peopledbweb' package.
 *
 * The main method is the entry point of the JVM and delegates to Spring Boot's SpringApplication class by calling run.
 * SpringApplication bootstraps our application, starting Spring, which, in turn, starts the auto-configured Tomcat web server.
 * We need to pass PeopledbWebApplication.class as an argument to the run method to tell SpringApplication which is the primary Spring component.
 * The args array is also passed through to expose any command-line arguments.
 */
@SpringBootApplication
public class PeopledbWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopledbWebApplication.class, args);
	}

}
