package blackfriday.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import blackfriday.bankclient.BankClient;
import blackfriday.wsdl.generated.UserLoginResponse;

@SpringBootApplication
@ComponentScan(basePackages = { "blackfriday" })
public class Startup {
    public static void main(String[] args) {
        SpringApplication.run(Startup.class);
    }

    @Bean
    CommandLineRunner lookup(BankClient bankclient) {
        return args -> {
            UserLoginResponse response = bankclient.getUserLoginById("123");
            bankclient.printResponse(response);
        };
    }
}
