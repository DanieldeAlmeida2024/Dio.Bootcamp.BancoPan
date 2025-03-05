package dio.spring_ioc_di_beans_autowire;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringIocDiBeansAutowireApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIocDiBeansAutowireApplication.class, args);
    }
    @Bean
    public CommandLineRunner run(ConversorJson conversor) throws Exception{
		return args ->{
            String json = "{\"cep\": \"010101-010\",\"logradouro\": \"Praça da Sé\",\"localidade\": \"São Paulo\"}";
            ViaCepResponse response = conversor.converter(json);
            System.out.println("Dados do CEP: "+response);
        };
    }

}
