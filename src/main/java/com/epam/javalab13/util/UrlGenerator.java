package com.epam.javalab13.util;

import java.util.Date;

/**
 * Created by Vikno on 9/7/2016.
 * For generating unique hash for restoring password
 */
public class UrlGenerator {

    public static String getFixedUrl(String login, Date currentDate){
        return PasswordHash.SHA_256(login + currentDate.toString());
    }
}
