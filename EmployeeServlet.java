package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int empId = Integer.parseInt(request.getParameter("emp_id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company_db", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM employee WHERE emp_id=?");

            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>Employee Details</h2>");
                out.println("ID: " + rs.getInt("emp_id") + "<br>");
                out.println("Name: " + rs.getString("emp_name") + "<br>");
                out.println("Department: " + rs.getString("department") + "<br>");
                out.println("Salary: " + rs.getDouble("salary") + "<br>");
            } else {
                out.println("<h3>No Employee Found</h3>");
            }

            con.close();

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
