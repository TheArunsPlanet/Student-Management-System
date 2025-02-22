package com.sms.servlet;

import com.sms.dao.ProfessorDAO;
import com.sms.model.Professor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ProfessorLoginServlet")
public class ProfessorLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            Professor professor = professorDAO.loginProfessor(username, password);
            if (professor != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", professor);
                session.setAttribute("role", "professor");
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("professor_login.jsp?error=Invalid+credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}