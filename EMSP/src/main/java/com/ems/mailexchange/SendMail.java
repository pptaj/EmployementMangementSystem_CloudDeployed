package com.ems.mailexchange;

/**
 * Created by Christopher Dsouza on 3/18/2017.
 */
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.ems.doa.PasswordDAO;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class SendMail {
    PasswordDAO passwordDAO = new PasswordDAO();
    private AWSCredentials createAwsCredentials() throws IOException {
        Properties properties = new Properties();
        InputStream input;
        properties.load(getClass().getClassLoader().getResourceAsStream("aws.properties"));
        AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("accessKey"), properties.getProperty("secretKey"));
        return credentials;
    }
    private AmazonSimpleEmailService createSimpleEmailService() throws IOException {
        return new AmazonSimpleEmailServiceClient(createAwsCredentials());
    }
    public void sendTestEmail(String Email_to) throws IOException {
        String tempPassword= RandomAlphaNum.gen(10);
        PostMan postMan = new AWSPostMan(createSimpleEmailService());
        postMan.withFrom("christopherdsouza14@gmail.com").withTo(Email_to)
                .withSubject("Reset Password").withBody(
                "Login to EMS with password "+tempPassword).send();
        passwordDAO.updateTempPassword(Email_to,tempPassword);
    }
  /*  public static void main(String[] args) throws IOException {
        new SendMail().sendTestEmail();
    }*/
}
