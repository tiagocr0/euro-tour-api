package ifsc.edu.br.eurotour.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
/*
 * Heroku caminho aplicação - https://euro-tour-teste.herokuapp.com/
 * Heroku swagger - https://euro-tour-teste.herokuapp.com/swagger-ui.html
 * 
 * */
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
