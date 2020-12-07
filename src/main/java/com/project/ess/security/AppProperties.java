package com.project.ess.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties { //untuk bisa read data dari application.properties dan check SecurityConstant

    @Autowired
    private Environment env;


    public String getTokenSecret(){
        return env.getProperty("tokenSecret");
    }

    public String getExpirationTime(){
        return env.getProperty("expiration.time");
    }

    public String getTokenPrefix(){
        return env.getProperty("token.prefix");
    }

    public String getHeaderString(){
        return env.getProperty("header.string");
    }

}
