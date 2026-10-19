package com.mycompany.l01;

import javax.swing.*;
import java.awt.*;

public class Lab1 extends JFrame {
    private JTextField txtX = new JTextField("12", 10);
    private JTextField txtA = new JTextField("33", 10);
    private JTextField txtB = new JTextField("56", 10);
    private JLabel lblResult = new JLabel("Нәтиже Y = ");

    public Lab1() {
        setTitle("JAVA-да қарапайым есептеу (1-зертханалық жұмыс)");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        // 1. X енгізу
        JPanel pX = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pX.add(new JLabel("X мәнін енгізіңіз (сан):"));
        pX.add(txtX);
        add(pX);

        // 2. A енгізу
        JPanel pA = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pA.add(new JLabel("A мәнін енгізіңіз (нөлден үлкен):"));
        pA.add(txtA);
        add(pA);

        // 3. B енгізу
        JPanel pB = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pB.add(new JLabel("B мәнін енгізіңіз (нөлден үлкен):"));
        pB.add(txtB);
        add(pB);

        // 4. Батырмалар
        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnCalc = new JButton("Есептеу");
        JButton btnClear = new JButton("Тазалау");
        JButton btnExit = new JButton("Шығу");

        btnCalc.addActionListener(e -> calculate());
        btnClear.addActionListener(e -> {
            txtX.setText("");
            txtA.setText("");
            txtB.setText("");
            lblResult.setText("Нәтиже Y = ");
        });
        btnExit.addActionListener(e -> System.exit(0));

        pBtn.add(btnCalc);
        pBtn.add(btnClear);
        pBtn.add(btnExit);
        add(pBtn);

        // 5. Нәтиже
        lblResult.setFont(new Font("Arial", Font.BOLD, 14));
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblResult);
    }

    private void calculate() {
        try {
            double x = Double.parseDouble(txtX.getText().trim());
            double a = Double.parseDouble(txtA.getText().trim());
            double b = Double.parseDouble(txtB.getText().trim());

            // 0-ден үлкен болуын тексеру
            if (a <= 0 || b <= 0) {
                JOptionPane.showMessageDialog(this, "A және B мәндері 0-ден үлкен болуы тиіс!", "Қате", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Есептеу (бөлімі 0 болмауын тексеру)
            double y = (x + Math.sqrt(a)) / Math.pow(b, 2);
            lblResult.setText(String.format("Нәтиже Y = %.3f", y));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Тек сандарды енгізіңіз!", "Қате", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab1().setVisible(true));
    }
}