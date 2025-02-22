<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Student Login</h1>
        </header>
        <form action="StudentLoginServlet" method="post">
            <label>Username:</label> <input type="text" name="username"><br>
            <label>Password:</label> <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
        <p><a href="register.jsp">Not registered? Register here</a></p>
        <p><a href="index.jsp">Back to Home</a></p>
        <% String error = request.getParameter("error"); if (error != null) { %>
            <p class="error"><%= error %></p>
        <% } %>
        <footer>
            <p>&copy; 2025 SMS</p>
        </footer>
    </div>
</body>
</html>