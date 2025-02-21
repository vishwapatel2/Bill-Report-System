package com.example.BillGenerator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioSmsConfig {

    private String authSid;

    private String authToken;

    private String phoneNumber;


    public String getAuthSid() {
        return authSid;
    }

    public void setAuthSid(String authSid) {
        System.out.println("setter method is auth sid");
        this.authSid = authSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        System.out.println("setter method is auth token");
        this.authToken = authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        System.out.println("setter method is auth phone number");
        this.phoneNumber = phoneNumber;
    }


}
