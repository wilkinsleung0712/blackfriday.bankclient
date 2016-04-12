package blackfriday.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import blackfriday.bankclient.BankClient;

@Configuration
public class WebConfig {
    
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("blackfriday.wsdl.generated");
        return marshaller;
    }
    
    @Bean
    public BankClient bankClient(final Jaxb2Marshaller marshaller) {
        BankClient bankClient = new BankClient();
        bankClient.setDefaultUri("http://localhost:8080/ws");
        bankClient.setMarshaller(marshaller);
        bankClient.setUnmarshaller(marshaller);
        return bankClient;
    }
}
