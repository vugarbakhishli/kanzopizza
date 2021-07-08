package az.crbn.kanzopizza.ms.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KanzoPizzaMsCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanzoPizzaMsCustomerApplication.class, args);
    }

}
