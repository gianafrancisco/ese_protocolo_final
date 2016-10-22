package ese.protocolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ese.protocolo")
public class ProtocoloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtocoloApplication.class, args);
	}
}
