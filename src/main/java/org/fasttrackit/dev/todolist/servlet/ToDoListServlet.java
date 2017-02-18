package org.fasttrackit.dev.todolist.servlet;


import org.fasttrackit.dev.todolist.AccessTaskList;
import org.fasttrackit.dev.todolist.MyListOfToDoMock;
import org.fasttrackit.dev.todolist.ToDoBean;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by condor on April 04, 2015
 * FastTrackIT, 2015
 */


/* didactic purposes

Some items are intentionally not optimized or not coded in the right way

FastTrackIT 2015

*/

@WebServlet("/items")
public class ToDoListServlet extends HttpServlet {

    private static final String ACTION = "action";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String DONE_ACTION = "done";
    private static final String ID_TASK = "id";
    private static final String VALUE_NEWTASK = "value";

    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("mytask list service called now.");



        HttpSession session = request.getSession(true);

        String username=(String)session.getAttribute("username");
        if(username==null) // nu esti logat
        {
            System.out.println("nu esti logat nene");
            notLoginAction(request, response, true);

//            try {
//                System.out.println("nu exista acest user in db, deci nu fac nimic ");
//                String back = "/login.html";
//                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
        else { // esti logat

            String action = request.getParameter(ACTION);


            if (action != null && action.equals(LIST_ACTION)) {
                listAction(request, response);
            } else if (action != null && action.equals(ADD_ACTION)) {
                addAction(request, response);
            } else if (action != null && action.equals(DONE_ACTION)) {
                doneAction(request, response);
            }
            else if (action != null && action.equals("seeLogin")) {
                notLoginAction(request, response, false);
            }

        }
    }



    private void listAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("list action");
        HttpSession session = request.getSession(true);

        // call db

//        MyListOfToDoMock myListObject = MyListOfToDoMock.getInstance();
//        myListObject.printList();
//        List<ToDoBean> l = myListObject.getList();


        AccessTaskList atl = new AccessTaskList();
        List<ToDoBean> l = atl.getTaskList();



        // put the list in a json
        JsonObjectBuilder jObjBuilder = Json.createObjectBuilder();
        JsonArrayBuilder jArrayBuilder = Json.createArrayBuilder();
        for (ListIterator<ToDoBean> iterator = l.listIterator(); iterator.hasNext(); ) {
            ToDoBean element = iterator.next();
            if (!element.isDone()) {
                System.out.print(element.getId() + ":");
                System.out.println(element.getWhatToDo());
                jArrayBuilder.add(Json.createObjectBuilder()
                                .add("name", element.getWhatToDo())
                                .add("done", false)
                                .add("id", element.getId())
                );

            }
        }
        jObjBuilder.add("tasks", jArrayBuilder);
        JsonObject jSonFinal = jObjBuilder.build();

        System.out.println("json pe list: " + jSonFinal.toString());

        returnJsonResponse(response, jSonFinal.toString());
        System.out.println("end list action");
    }


    private void doneAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("enter pe done");

        HttpSession session = request.getSession(true);

        String idS = request.getParameter(ID_TASK);
        int id = Integer.parseInt(idS);

        AccessTaskList atl = new AccessTaskList();
        atl.markDone(id);

//        MyListOfToDoMock myListObject = MyListOfToDoMock.getInstance();
//
//        myListObject.printList();
//
//
//        List<ToDoBean> l = myListObject.getList();
//        for (ListIterator<ToDoBean> iterator = l.listIterator(); iterator.hasNext(); ) {
//            ToDoBean element = iterator.next();
//
//            if (element.getId() == id) {
//                System.out.println("found it, now canceling");
//                element.setDone(true);
//                iterator.set(element);
//            }
//        }

        System.out.println("i am done");
    }

    private void addAction(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("add action");

        HttpSession session = request.getSession(true);

        String value = request.getParameter(VALUE_NEWTASK);


        MyListOfToDoMock myListObject = MyListOfToDoMock.getInstance();
        myListObject.printList();
        myListObject.addItem(value);



        AccessTaskList atl = new AccessTaskList();
        atl.insertTaskList(value);

        System.out.println("now I am done");

    }

    /**/
    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

    private void notLoginAction(HttpServletRequest request, HttpServletResponse response, boolean notLogin)
    {
        String  jsonResponse;
        if(notLogin)
            jsonResponse = "{\"keyError\":\"You are not logged in. \"}";
        else {
            String username=(String)request.getSession().getAttribute("username");
            System.out.println("username in verificare:"+username);
            jsonResponse = "{\"keyError\":\"Hello my friend "+username+"\"}";
        }
        returnJsonResponse(response, jsonResponse.toString());
        System.out.println("end list action");
    }

}
