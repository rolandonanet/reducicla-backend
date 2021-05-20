package br.com.reducicla;

import br.com.reducicla.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ReduciclaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReduciclaApplication.class, args);
    }


}
