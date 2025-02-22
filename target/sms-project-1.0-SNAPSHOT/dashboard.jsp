<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sms.model.Student" %>
<%@ page import="com.sms.model.Professor" %>
<%@ page import="com.sms.dao.StudentDAO" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Dashboard</h1>
        </header>
        <%
            HttpSession sess = request.getSession(false);
            if (sess == null || sess.getAttribute("user") == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            Object user = sess.getAttribute("user");
            String role = (String) sess.getAttribute("role");
            String name = (user instanceof Student) ? ((Student) user).getName() : ((Professor) user).getName();
            String username = (user instanceof Student) ? ((Student) user).getUsername() : ((Professor) user).getUsername();
        %>
        <h2>Welcome, <%= name %>!</h2>
        <p>Role: <%= role %></p>
        <p>Username: <%= username %></p>

        <% if ("student".equals(role)) { %>
            <div class="student-section">
                <h3>Update Your Details</h3>
                <form action="UpdateServlet" method="post">
                    <label>Name:</label> <input type="text" name="name" value="<%= name %>"><br>
                    <label>Password:</label> <input type="password" name="password" value="<%= (user instanceof Student) ? ((Student) user).getPassword() : ((Professor) user).getPassword() %>"><br>
                    <input type="submit" value="Update">
                </form>
            </div>
        <% } else if ("professor".equals(role)) { %>
            <div class="professor-section">
                <h3>Student List</h3>
                <table>
                    <tr><th>ID</th><th>Username</th><th>Name</th><th>Action</th></tr>
                    <%
                        StudentDAO studentDAO = new StudentDAO();
                        try (Connection conn = com.sms.util.DBConnection.getConnection();
                             Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
                            while (rs.next()) {
                    %>
                        <tr>
                            <td><%= rs.getInt("id") %></td>
                            <td><%= rs.getString("username") %></td>
                            <td><%= rs.getString("name") %></td>
                            <td><a href="DeleteServlet?studentId=<%= rs.getInt("id") %>">Delete</a></td>
                        </tr>
                    <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    %>
                </table>
            </div>
        <% } %>

        <% 
            String success = request.getParameter("success");
            String error = request.getParameter("error");
            if (success != null) { 
        %>
            <p class="success"><%= success %></p>
        <% 
            } else if (error != null) { 
        %>
            <p class="error"><%= error %></p>
        <% 
            } 
        %>
        <p><a href="LogoutServlet">Logout</a></p>
        <p><a href="index.jsp">Back to Home</a></p>
        <footer>
            <p>&copy; 2025 SMS</p>
        </footer>
    </div>
</body>
</html>