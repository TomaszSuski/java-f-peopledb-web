package com.lingarogroup.peopledbweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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
// WebMvcConfigurer is an interface that provides default methods for configuring Spring MVC.
// all added additional configuration to the application makes possible to change the locale of the application
// by adding a locale parameter to the URL (e.g., http://localhost:8080/people?locale=fr)
public class PeopledbWebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PeopledbWebApplication.class, args);
	}

	/**
	 * The localeResolver method is a bean that resolves the locale a client is using.
	 * It uses the SessionLocaleResolver class, which resolves the locale based on a locale attribute in the user's session.
	 * If no locale attribute is found in the session, this resolver falls back to the default locale.
	 *
	 * @return A LocaleResolver that resolves the locale based on a locale attribute in the user's session.
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.getDefault());
		return slr;
	}

	/**
	 * The localeChangeInterceptor method is a bean that intercepts incoming requests and optionally modifies the locale.
	 * It works in conjunction with the localeResolver bean.
	 * If a request parameter named "locale" is present in the request, this interceptor changes the current locale to the one specified by the "locale" parameter.
	 *
	 * @return A LocaleChangeInterceptor that intercepts incoming requests and optionally modifies the locale.
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		return new LocaleChangeInterceptor();
	}

	/**
	 * The addInterceptors method is used to add the LocaleChangeInterceptor to the application's interceptor registry.
	 * This makes the interceptor apply to all incoming requests.
	 *
	 * @param registry The application's interceptor registry.
	 */
	@Override
	public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
