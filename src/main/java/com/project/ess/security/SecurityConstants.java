package com.project.ess.security;

import com.project.ess.SpringApplicationContext;

public class SecurityConstants {

    public static String getTokenSecret(){
        AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }

    public static String getExpirationTime(){
        AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getExpirationTime();
    }

    public static String getTokenPrefix(){
        AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenPrefix();
    }

    public static String getHeaderString(){
        AppProperties appProperties=(AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getHeaderString();
    }
}
