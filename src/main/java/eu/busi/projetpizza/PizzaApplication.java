package eu.busi.projetpizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

/**
 * SecurityAutoConfiguration.class  permet de Desactive le congfig par defaut de Loging génére par Spring Security
 * lors de la declaration dependencie "spring Security"
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }


}
