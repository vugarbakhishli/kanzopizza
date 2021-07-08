package az.crbn.kanzopizza.ms.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KanzoPizzaMsOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanzoPizzaMsOrderApplication.class, args);
	}

}
