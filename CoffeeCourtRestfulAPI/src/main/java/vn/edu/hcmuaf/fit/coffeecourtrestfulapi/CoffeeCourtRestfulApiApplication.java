package vn.edu.hcmuaf.fit.coffeecourtrestfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CoffeeCourtRestfulApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoffeeCourtRestfulApiApplication.class, args);
    }

}
