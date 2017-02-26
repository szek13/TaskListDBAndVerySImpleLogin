package org.fasttrackit.dev.todolist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by icondor on 18/02/17.
 */
public class AccessTaskList {

    public List getTaskList() {

        List listaTaskList = new ArrayList<ToDoBean>();


        try {

            UserAccessList userAccess = new UserAccessList();


            Class.forName("org.postgresql.Driver");


            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            Statement st = conn.createStatement();

            String query = "SELECT * FROM tasklist order by taskname asc";
            // 5. execute a query
            ResultSet rs = st.executeQuery(query);

            // 6. iterate the result set and print the values
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("taskname");
                boolean isdone = rs.getBoolean("isdone");

                ToDoBean randcurent = new ToDoBean(id, name, isdone);


                listaTaskList.add(randcurent);

                // 7. close the objects

            }
            rs.close();
            st.close();
            conn.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaTaskList;
    }

    public void insertTaskList(String nameOfTheTask) {

        // presupun ca am tabela faq faq (id, intrebare, raspuns)

        // conectez la db
        // prep statememt
        //rules insert into
        //inchid ps
        // inchid conex


        final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO tasklist (taskname,isdone) VALUES (?,?)");
            pSt.setString(1, nameOfTheTask);
            pSt.setBoolean(2, false);

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void markDone(int id) {

        // presupun ca am tabela faq faq (id, intrebare, raspuns)

        // conectez la db
        // prep statememt
        //rules insert into
        //inchid ps
        // inchid conex


        final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
        final String USERNAME = "fasttrackit_dev";

        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("update tasklist set isdone=true where id=?");
            pSt.setInt(1, id);


            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


 /*  private void loginToDB(String username, String password){


        final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            PreparedStatement pSt = conn.prepareStatement("SELECT id FROM useri WHERE username=request.getParameter('u') " +
                    "and password=request.getParameter('p')");

            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }  */
}
