<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to SMS</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Welcome to the Student Management System (SMS)</h1>
        <p>Please select your role:</p>
        <div class="student-section">
            <h2>Are you a Student?</h2>
            <p><a href="student_login.jsp">Login</a> | <a href="register.jsp">Register</a></p>
        </div>
        <div class="professor-section">
            <h2>Are you a Professor?</h2>
            <p><a href="professor_login.jsp">Login</a> | <a href="register_professor.jsp">Register</a></p>
        </div>
        <footer>
            <p>© 2025 SMS</p>
        </footer>
    </div>
</body>
</html>