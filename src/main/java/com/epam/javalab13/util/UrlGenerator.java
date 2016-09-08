package com.epam.javalab13.util;

import java.util.Date;

/**
 * Created by Vikno on 9/7/2016.
 */
public class UrlGenerator {

    public static String getFixedUrl(String login, Date currentDate){
        System.out.println(currentDate.toString());
        return PasswordHash.SHA_256(login + currentDate.toString());
    }
}
