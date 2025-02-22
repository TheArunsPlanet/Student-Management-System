package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class StudentDAO {
    public void registerStudent(Student student) throws SQLException {
        String hashedPassword = BCrypt.hashpw(student.getPassword(), BCrypt.gensalt());
        String sql = "INSERT INTO students (username, password, name) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getUsername());
            stmt.setString(2, hashedPassword); // Store hashed password
            stmt.setString(3, student.getName());
            stmt.executeUpdate();
        }
    }

    public Student loginStudent(String username, String password) throws SQLException {
        String sql = "SELECT * FROM students WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(password, storedHash)) { // Verify plain-text password against hash
                    Student student = new Student(rs.getString("username"), password, rs.getString("name"));
                    student.setId(rs.getInt("id"));
                    return student;
                }
            }
        }
        return null;
    }

    public void updateStudent(Student student) throws SQLException {
        String hashedPassword = BCrypt.hashpw(student.getPassword(), BCrypt.gensalt());
        String sql = "UPDATE students SET name = ?, password = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, hashedPassword); // Store hashed password
            stmt.setInt(3, student.getId());
            stmt.executeUpdate();
        }
    }
}