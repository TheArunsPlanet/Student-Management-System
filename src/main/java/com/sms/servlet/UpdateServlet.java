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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Student student = (Student) session.getAttribute("user");
        String newName = request.getParameter("name");
        String newPassword = request.getParameter("password");

        // Update the student object
        student.setName(newName);
        student.setPassword(newPassword);

        StudentDAO studentDAO = new StudentDAO();
        try {
            studentDAO.updateStudent(student);
            // Update session with new data
            session.setAttribute("user", student);
            response.sendRedirect("dashboard.jsp?success=Update+successful");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?error=Update+failed");
        }
    }
}