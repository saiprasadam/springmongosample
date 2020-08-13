package com.openshift.coursecatalogue;

import org.springframework.boot.SpringApplication;

import java.io.File;
import java.util.Properties;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;*/

@SpringBootApplication
public class CourseCatalogueServerAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CourseCatalogueServerAppApplication.class, args);
	}
	/*@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
	  webServerFactoryCustomizer() {
	    return factory -> factory.setContextPath("/");
	}*/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	String configLocation = System.getProperty("global.appconf.dir"); //get the default config directory location
	    String configPath = configLocation + File.separator + "springApplication"  + File.separator + "application-docker.properties"; //set the configpath of this application instance exclusively
	    System.out.println("Configpath: " + configPath);
	    System.out.println("Starting to run Spring boot app...");
	    if(configLocation != null && !configLocation.isEmpty()) {
	    Properties props = new Properties();
	    props.setProperty("spring.config.location", configPath); //set the config file to use    
	    application.application().setDefaultProperties(props);
	    }else{
	    	 System.out.println("No global.appconf.dir property found, starting with default on classpath");
	    }
	return application.sources(SpringApplication.class);
	}

}
