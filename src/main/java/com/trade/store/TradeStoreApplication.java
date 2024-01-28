/**
 * The main entry point for the Trade Store application.
 * The application is implemented as a Spring Boot application,
 * utilizing the SpringApplication class to bootstrap and run the application.
 */
package com.trade.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code TradeStoreApplication} class contains the main method to start the Trade Store application.
 * It is annotated with {@code @SpringBootApplication}, indicating that it is a Spring Boot application and
 * enabling auto-configuration and component scanning.
 */
@SpringBootApplication
public class TradeStoreApplication {

    /**
     * The main method that kicks off the execution of the Trade Store application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application
        SpringApplication.run(TradeStoreApplication.class, args);
    }

}
