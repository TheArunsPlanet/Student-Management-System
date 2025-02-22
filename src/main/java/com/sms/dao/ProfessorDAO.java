package com.sms.dao;

import com.sms.model.Professor;
import com.sms.util.DBConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class ProfessorDAO {
    public void registerProfessor(Professor professor) throws SQLException {
        String hashedPassword = BCrypt.hashpw(professor.getPassword(), BCrypt.gensalt());
        String sql = "INSERT INTO professors (username, password, name) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professor.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, professor.getName());
            stmt.executeUpdate();
        }
    }

    public Professor loginProfessor(String username, String password) throws SQLException {
        String sql = "SELECT * FROM professors WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(password, storedHash)) {
                    Professor professor = new Professor(rs.getString("username"), password, rs.getString("name"));
                    professor.setId(rs.getInt("id"));
                    return professor;
                }
            }
        }
        return null;
    }

    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }
}