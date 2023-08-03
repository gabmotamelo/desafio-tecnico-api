package gabriel.api.banco;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Sistema Bancário Api",
				version = "1.0.0",
				description = "APi para um sistema bancário com algumas funcionalidades.",
				contact = @Contact(
						name = "Gabriel Mota Melo",
						email = "gabrielmota6@hotmail.com"
				)
		)
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
