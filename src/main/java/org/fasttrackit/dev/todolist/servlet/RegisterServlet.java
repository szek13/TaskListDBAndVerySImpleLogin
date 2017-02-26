package org.fasttrackit.dev.todolist.servlet;

import org.fasttrackit.dev.todolist.UserAccessList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Csongor on 2/26/2017.
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String username = request.getParameter("u");
        String password = request.getParameter("p");

        UserAccessList ual = new UserAccessList();

        ual.insertUser(username, password);

        // back to login
        String back = "/login.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
        dispatcher.forward(request, response);

    }

}
