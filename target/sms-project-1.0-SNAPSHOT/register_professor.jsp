<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Professor Registration</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Professor Registration</h1>
        </header>
        <form action="RegisterProfessorServlet" method="post">
            <input type="text" name="username" placeholder="Username"><br>
            <input type="password" name="password" placeholder="Password"><br>
            <input type="text" name="name" placeholder="Name"><br>
            <input type="submit" value="Register">
        </form>
        <p><a href="professor_login.jsp">Login here</a></p>
        <p><a href="register.jsp">Student Register</a></p>
        <p><a href="index.jsp">Back to Home</a></p>
        <footer>
            <p>&copy; 2025 SMS</p>
        </footer>
    </div>
</body>
</html>
