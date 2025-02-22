package com.sms.servlet;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        StudentDAO studentDAO = new StudentDAO();
        try {
            Student student = studentDAO.loginStudent(username, password);
            if (student != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", student);
                session.setAttribute("role", "student");
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("student_login.jsp?error=Invalid+credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}