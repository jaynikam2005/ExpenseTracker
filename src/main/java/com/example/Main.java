package com.example;

import javax.swing.SwingUtilities;
import gui.ExpenseTrackerGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerGUI());
    }
}