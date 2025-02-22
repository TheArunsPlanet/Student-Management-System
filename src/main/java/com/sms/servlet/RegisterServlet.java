package com.sms.servlet;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        Student student = new Student(username, password, name);
        StudentDAO studentDAO = new StudentDAO();
        try {
            studentDAO.registerStudent(student);
            response.sendRedirect("student_login.jsp"); // Updated from login.jsp
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}