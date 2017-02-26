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
 * Created by icondor on 18/02/17.
 */


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read user and password introduced by the user in the UI
        String user = request.getParameter("u");
        String passwd = request.getParameter("p");

        // static & simulated db row
      //  final String dbu="csongor";
      //  final String dbp="szekely13";
      //  final String userid="356";


        UserAccessList userAccesss = new UserAccessList();


        int iduser = -1;

        iduser = userAccesss.checkUserCredentials(user, passwd);
        if(iduser != -1) {
            System.out.println(user + "000");
            // userul exista in db, deci il autentific
            HttpSession session = request.getSession(true);
            session.setAttribute("username",user);
            session.setAttribute("userid",iduser);

            String success = "/todolist.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(success);
            dispatcher.forward(request, response);
        }
        else {
            System.out.println("nu exista acest user in db, deci nu fac nimic ");
            String back = "/login.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }

        System.out.println("login service called...");

    }
}

