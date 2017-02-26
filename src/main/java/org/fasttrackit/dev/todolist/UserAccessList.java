package org.fasttrackit.dev.todolist;

import org.fasttrackit.dev.todolist.ToDoBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Csongor on 2/26/2017.
 */
public class UserAccessList {

    public void UserAccessList() {

    }

    public int checkUserCredentials(String username, String password) {


        int iduser = -1;
        try {

            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            // Statement st = conn.createStatement();

            String query = "SELECT userid FROM useri where username=? and password=?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            //5. execute the query
            ResultSet rs = statement.executeQuery();

            //6. iterate the result set and print the values
            while (rs.next()) {
                iduser = rs.getInt("userid");
            }

            // 7. close the objects
            rs.close();
            statement.close();
            conn.close();

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("iese cu idu:" + iduser);
        return iduser;
    }


    public void insertUser(String username, String password){

        try {


            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5:5432/5csongor";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            // Statement st = conn.createStatement();

            String query = "INSERT INTO useri (username, password) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            //5. execute the query
            statement.executeUpdate();

           // 7. close the objects
           statement.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("iese din register:");
    }
}
