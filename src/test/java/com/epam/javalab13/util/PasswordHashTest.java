package com.epam.javalab13.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing password hashing
 */
public class PasswordHashTest {
    @Test
    public void SHA_256() throws Exception {
        String userPassword = "qwerty";//Password that user entered
        String userHashedPassword = PasswordHash.SHA_256(userPassword);//Hashed password that stored at DB

        //We get password from DB and compare it with newly entered password
        //It's must be the same
        assertEquals(userHashedPassword,PasswordHash.SHA_256("qwerty"));

        //But this not
        assertNotEquals(userHashedPassword,PasswordHash.SHA_256("qwerty2"));
    }

}