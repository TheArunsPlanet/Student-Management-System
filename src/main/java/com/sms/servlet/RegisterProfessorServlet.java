package com.sms.servlet;

import com.sms.dao.ProfessorDAO;
import com.sms.model.Professor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterProfessorServlet")
public class RegisterProfessorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        Professor professor = new Professor(username, password, name);
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            professorDAO.registerProfessor(professor);
            response.sendRedirect("professor_login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}