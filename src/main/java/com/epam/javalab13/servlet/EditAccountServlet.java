package com.epam.javalab13.servlet;

import com.epam.javalab13.model.User;
import com.epam.javalab13.service.game.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vikno on 9/9/2016.
 */
public class EditAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EDIT");
        HttpSession session = req.getSession(false);

        String userRole = (String) session.getAttribute("role");
        String userLogin = (String) session.getAttribute("login");

        UserService service = new UserService();
        User user = service.getUserByLogin(userLogin);
        req.setAttribute("user",user);

        req.getRequestDispatcher("/WEB-INF/view/" + userRole +  "/edit.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userID");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        System.out.println("uid: " + userId);
        System.out.println("name: " + name);
        System.out.println("surname: " + surname);
        System.out.println("age: " + age);
        System.out.println("gender: " + gender);

        UserService userService = new UserService();
        List<User> allUsers = userService.getAllUsers();

        int userID = Integer.parseInt(userId);
        String fullName = name + " " + surname;

        for(User user:allUsers){
            //If user with the same exist
            if(fullName.equalsIgnoreCase(user.getFullName())){
                if(userID == user.getId()){
                    //The same user
                    //update
                    System.out.println("the same user");
                    //OK
                    return;
                }else{
                    //User with such name exist
                    System.out.println("user exist");
                    //FAIL
                    return;
                }
            }
        }


        System.out.println("name unique");
        //If we are here than such name unique
        //update user
        //OK
        return;
        //if no such name we update this
    }
}
