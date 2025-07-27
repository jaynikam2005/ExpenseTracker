// src/main/java/dao/ExpenseDAO.java
package dao;

import model.Expense;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/expense_tracker";
    private final String username = "expense_user";
    private final String password = "password123";

    public void addExpense(Expense expense) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO expenses (amount, category, date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, expense.getAmount());
            stmt.setString(2, expense.getCategory());
            stmt.setDate(3, Date.valueOf(expense.getDate()));
            stmt.setString(4, expense.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding expense: " + e.getMessage(), e);
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM expenses ORDER BY date DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // Use the constructor that matches your GUI: (id, amount, category, date, description)
                Expense expense = new Expense(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("description")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving expenses: " + e.getMessage(), e);
        }
        return expenses;
    }

    public void deleteExpense(int id) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "DELETE FROM expenses WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No expense found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting expense: " + e.getMessage(), e);
        }
    }

    public void updateExpense(Expense expense) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "UPDATE expenses SET amount = ?, category = ?, date = ?, description = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, expense.getAmount());
            stmt.setString(2, expense.getCategory());
            stmt.setDate(3, Date.valueOf(expense.getDate()));
            stmt.setString(4, expense.getDescription());
            stmt.setInt(5, expense.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No expense found with ID: " + expense.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating expense: " + e.getMessage(), e);
        }
    }

    public Expense getExpenseById(int id) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM expenses WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Expense(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("description")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving expense: " + e.getMessage(), e);
        }
    }

    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM expenses WHERE category = ? ORDER BY date DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("description")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving expenses by category: " + e.getMessage(), e);
        }
        return expenses;
    }

    public double getTotalExpenses() {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT SUM(amount) as total FROM expenses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("total");
            }
            return 0.0;
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating total expenses: " + e.getMessage(), e);
        }
    }
}