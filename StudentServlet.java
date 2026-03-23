package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String course = request.getParameter("course");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company_db", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student(name, age, course) VALUES (?, ?, ?)");

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);

            ps.executeUpdate();

            response.getWriter().println("Student Registered Successfully");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
