// src/main/java/gui/ExpenseTrackerGUI.java
package gui;

import dao.ExpenseDAO;
import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ExpenseTrackerGUI {
    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    public ExpenseTrackerGUI() {
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add Expense Form
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField amountField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField descriptionField = new JTextField();
        JButton addButton = new JButton("Add Expense");

        formPanel.add(new JLabel("Amount:"));
        formPanel.add(amountField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);
        formPanel.add(new JLabel(""));
        formPanel.add(addButton);

        // Expense List
        JTextArea expenseListArea = new JTextArea();
        expenseListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(expenseListArea);

        // Add action listeners
        addButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String category = categoryField.getText().trim();
                String description = descriptionField.getText().trim();
                
                // Validate input
                if (category.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
                    return;
                }
                
                // Create expense (adjust constructor parameters based on your Expense class)
                Expense expense = new Expense(0, amount, category, LocalDate.now(), description);
                expenseDAO.addExpense(expense);
                updateExpenseList(expenseListArea);
                
                // Clear fields
                amountField.setText("");
                categoryField.setText("");
                descriptionField.setText("");
                
                JOptionPane.showMessageDialog(frame, "Expense added successfully!");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount! Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error adding expense: " + ex.getMessage());
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        // Load initial data
        updateExpenseList(expenseListArea);
    }

    private void updateExpenseList(JTextArea textArea) {
        try {
            List<Expense> expenses = expenseDAO.getAllExpenses();
            StringBuilder sb = new StringBuilder();
            if (expenses.isEmpty()) {
                sb.append("No expenses recorded yet.");
            } else {
                for (Expense expense : expenses) {
                    sb.append(String.format(
                        "ID: %d | Amount: $%.2f | Category: %s | Date: %s | Desc: %s\n",
                        expense.getId(), expense.getAmount(), expense.getCategory(),
                        expense.getDate(), expense.getDescription()
                    ));
                }
            }
            textArea.setText(sb.toString());
        } catch (Exception ex) {
            textArea.setText("Error loading expenses: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerGUI());
    }
}