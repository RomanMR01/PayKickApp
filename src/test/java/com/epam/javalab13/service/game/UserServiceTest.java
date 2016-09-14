package com.epam.javalab13.service.game;

import com.epam.javalab13.model.Gender;
import com.epam.javalab13.model.Role;
import com.epam.javalab13.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vikno on 9/14/2016.
 */
public class UserServiceTest {

    private static UserService userService = null;

    /*
    All three fields must be hardcoded,
    because we use them at other tests.
    (And if user with such fields already is in DB, everything will be alright)
     */
    private static String bookmakerLogin = "junit4";
    private static String bookmakerName = "JUnit Four";
    private static String bookmakerEmail = "junit@four.com";

    @BeforeClass
    public static void init() {
        userService = new UserService();
    }

    @Test
    public void changeUserRole(){
        User bookmaker = userService.getUserByLogin(bookmakerLogin);//Get create user

        //If user are new, his role will be CLIENT,
        //but we need set him role to BOOKMAKER, and after that check it
        userService.changeUserRole(bookmaker.getId(), "BOOKMAKER");
        bookmaker = userService.getUserByLogin(bookmakerLogin);//Get user with updated role

        //Test, role must be BOOKMAKER!
        assertEquals(Role.BOOKMAKER,bookmaker.getRole());

        System.out.println("changeUserRole test passed!");
    }

    @Test
    public void addUser(){

        //1. We need to create new user first.
        User user = new User();
        user.setLogin(bookmakerLogin);
        user.setFullName(bookmakerName);
        user.setEmail(bookmakerEmail);
        //Meaning of this fields not important
        user.setAge(25);
        user.setGender(Gender.MALE);
        user.setPassword("junit");//Does not need hashing for testing

        //2. Adding new user to DB
        userService.addUser(user);

        //3. And now test
        //If user not exist - test failed (because userService return null)
        assertNotNull(userService.getUserByLogin(bookmakerLogin));

        System.out.println("addUser test passed!");
    }



}