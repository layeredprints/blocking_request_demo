package space.ballistix.demo.worker.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

/**
 *
 * @author Brecht
 */
// Code structure follows these principles: http://www.robertomarchetto.com/java_rest_api_best_practices_spring_boot
@SpringBootApplication
@ComponentScan({"space.ballistix"})
@EntityScan({"space.ballistix"})
@EnableJms
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
