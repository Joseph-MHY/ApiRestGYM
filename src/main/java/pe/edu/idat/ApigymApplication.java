package pe.edu.idat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.idat.Repositories.UsuarioRepository;

@Slf4j
@SpringBootApplication
public class ApigymApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigymApplication.class, args);
	}

}
