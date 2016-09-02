package com.epam.javalab13.util;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Util class for hashing passwords by SHA-256 algorithm
 */
public class PasswordHash {
    private static Logger logger = Logger.getLogger(PasswordHash.class);

    /**
     * Hash password by SHA-256
     * @param input input password
     * @return hashed password
     */
    public static String SHA_256(String input){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(input.getBytes());
            digest = messageDigest.digest();
        }catch (Exception e){
            logger.error("Cant hash password:",e);
        }

        BigInteger bigInteger = new BigInteger(1,digest);
        String sha_256 = bigInteger.toString(16);
        while(sha_256.length()<64){
            sha_256 = "0" + sha_256;
        }
        return sha_256;
    }

}
