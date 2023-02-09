package com.codecool.grannymanager;

import com.codecool.grannymanager.service.SessionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SessionConfig {

    @Bean
    @Scope("singleton")
    public SessionService getSessionService(){
        return new SessionService();
    }

}
