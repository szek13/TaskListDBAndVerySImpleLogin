package org.fasttrackit.dev.todolist.servlet;

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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        session.removeAttribute("username");
        session.removeAttribute("userid");


        String back = "/todolist.html";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
        dispatcher.forward(request, response);
    }


}
