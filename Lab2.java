package com.mycompany.l01;

import javax.swing.*;
import java.awt.*;

public class Lab2 extends JFrame {
    private JTextField txtX = new JTextField("1", 5);
    private JTextField txtB = new JTextField("2", 5);
    private JTextField txtD = new JTextField("33", 5);
    private JLabel lblResult = new JLabel("ЖАУАБЫ: 136,00");

    public Lab2() {
        setTitle("JAVA-да қарапайым визуалды бағдарлама құру");
        setSize(520, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Сол жақ: X, B, D енгізу өрістері
        JLabel lblX = new JLabel("X");
        lblX.setBounds(20, 20, 20, 25);
        txtX.setBounds(40, 20, 60, 25);
        add(lblX);
        add(txtX);

        JLabel lblB = new JLabel("B");
        lblB.setBounds(20, 50, 20, 25);
        txtB.setBounds(40, 50, 60, 25);
        add(lblB);
        add(txtB);

        JLabel lblD = new JLabel("D");
        lblD.setBounds(20, 80, 20, 25);
        txtD.setBounds(40, 80, 60, 25);
        add(lblD);
        add(txtD);

        // 2. Ортада: Java SE + Swing блогы
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        infoPanel.setBounds(115, 20, 110, 85);
        infoPanel.add(new JLabel("Java SE", SwingConstants.CENTER));
        infoPanel.add(new JLabel("+ Swing", SwingConstants.CENTER));
        add(infoPanel);

        // 3. Оң жақта: Формула блогы
        JLabel lblFormula = new JLabel("<html><div style='border:1px solid gray; padding:6px;'>"
                + "y = { (x - 2) / x<sup>2</sup>, &nbsp;&nbsp;&nbsp;&nbsp; x &ge; 8<br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ b<sup>2</sup>d + 4x<sup>3</sup>, &nbsp;&nbsp;&nbsp;&nbsp; x &lt; 8"
                + "</div></html>");
        lblFormula.setBounds(235, 20, 250, 85);
        add(lblFormula);

        // 4. Төменде: 3 батырма
        JButton btnSolve = new JButton("Шешу");
        btnSolve.setBounds(30, 125, 130, 32);
        btnSolve.addActionListener(e -> calculate());
        add(btnSolve);

        JButton btnClear = new JButton("Тазалау");
        btnClear.setBounds(180, 125, 130, 32);
        btnClear.addActionListener(e -> {
            txtX.setText("");
            txtB.setText("");
            txtD.setText("");
            lblResult.setText("ЖАУАБЫ: ");
        });
        add(btnClear);

        JButton btnExit = new JButton("Шығу");
        btnExit.setBounds(330, 125, 130, 32);
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);

        // 5. Ең астында: Нәтиже
        lblResult.setFont(new Font("Arial", Font.BOLD, 13));
        lblResult.setBounds(30, 175, 400, 25);
        add(lblResult);
    }

    private void calculate() {
        try {
            double x = Double.parseDouble(txtX.getText().trim());
            double b = Double.parseDouble(txtB.getText().trim());
            double d = Double.parseDouble(txtD.getText().trim());
            double y;

            if (x >= 8) {
                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "Нөлге бөлуге болмайды!");
                    return;
                }
                y = (x - 2.0) / (x * x);
            } else {
                y = (b * b * d) + 4.0 * Math.pow(x, 3);
            }

            lblResult.setText(String.format("ЖАУАБЫ: %.2f", y));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Тек сандарды енгізіңіз!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab2().setVisible(true));
    }
}