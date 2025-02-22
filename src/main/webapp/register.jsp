<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Student Registration</h1>
        </header>
        <form action="RegisterServlet" method="post">
            <input type="text" name="username" placeholder="Username"><br>
            <input type="password" name="password" placeholder="Password"><br>
            <input type="text" name="name" placeholder="Name"><br>
            <input type="submit" value="Register">
        </form>
        <p><a href="student_login.jsp">Login here</a></p>
        <p><a href="register_professor.jsp">Professor Register</a></p>
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
