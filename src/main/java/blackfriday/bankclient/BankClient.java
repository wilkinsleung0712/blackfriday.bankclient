package blackfriday.bankclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import blackfriday.wsdl.generated.User;
import blackfriday.wsdl.generated.UserLoginRequest;
import blackfriday.wsdl.generated.UserLoginResponse;

public class BankClient extends WebServiceGatewaySupport {
    private static final Logger logger = LoggerFactory.getLogger(BankClient.class.getName());
    
    public UserLoginResponse getUserLoginById (final String id) {
        logger.info("-> getUserLoginById()");
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId(id);
        logger.info("Requesting user details for " + id);
        UserLoginResponse response 
            = (UserLoginResponse) this.getWebServiceTemplate()
              .marshalSendAndReceive("http://localhost:8080/ws/useroperation.wsdl",
                                     request,
                                     new SoapActionCallback("http://localhost:8080/ws/UserLoginResponse"));
        
        return response;
    }
    
    public void printResponse(final UserLoginResponse response) {
        User loginReturnUser = response.getUserDetails();
        if (null != loginReturnUser) {
            logger.info(String.format("Name:[%s] Status:[%s] Gender:[%s]-Id:[%s]",loginReturnUser.getUserName(),
                                       loginReturnUser.getUserStatus(),
                                       loginReturnUser.getUserGender(),
                                       loginReturnUser.getUserId()));
        } else {
            logger.info("No user return");
        }
    }
}
