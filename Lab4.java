package com.mycompany.l01;

import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class Lab4 extends JFrame {
    private DefaultTableModel model = new DefaultTableModel(new String[]{"K(i)", "Y(i)"}, 10);
    private final JTable table = new JTable(model);

    public Lab4() {
        setTitle("JAVA-да күрделі кестелік есептеулер (4-жұмыс)");
        setSize(580, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Сол жақтағы 10x2 кесте
        table.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 170, 280);
        add(scroll);

        // 2. Оң жақ жоғарыдағы формула жақтауы
        JLabel lblFormula = new JLabel("<html><div style='border:1px solid gray; padding:10px; background:white; font-size:12px;'>"
                + "Y<sub>i</sub> = [ &radic;( &prod;(K<sub>i</sub>) - &sum;(K<sub>i</sub>) ) / (i! / i) ] &times; (tg(i/2))<sup>2</sup>"
                + "</div></html>");
        lblFormula.setBounds(210, 20, 335, 100);
        add(lblFormula);

        // 3. Оң жақ төмендегі 4 батырма
        JButton btnRandom = new JButton("Кездейсоқ толтыру");
        btnRandom.setBounds(210, 140, 160, 32);
        btnRandom.addActionListener(e -> fillRandom());
        add(btnRandom);

        JButton btnClear = new JButton("Тазалау");
        btnClear.setBounds(385, 140, 160, 32);
        btnClear.addActionListener(e -> {
            for (int i = 0; i < 10; i++) {
                model.setValueAt("", i, 0);
                model.setValueAt("", i, 1);
            }
        });
        add(btnClear);

        JButton btnRun = new JButton("Тапсырманы орындау");
        btnRun.setBounds(210, 190, 160, 32);
        btnRun.addActionListener(e -> calculate());
        add(btnRun);

        JButton btnExit = new JButton("Шығу");
        btnExit.setBounds(385, 190, 160, 32);
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);

        fillRandom();
    }

    private void fillRandom() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            model.setValueAt(r.nextInt(80) + 20, i, 0);
            model.setValueAt("", i, 1);
        }
    }

    private void calculate() {
        try {
            double[] k = new double[10];
            for (int i = 0; i < 10; i++) {
                Object val = model.getValueAt(i, 0);
                if (val == null || val.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Барлық K(i) мәндерін енгізіңіз!");
                    return;
                }
                k[i] = Double.parseDouble(val.toString().trim().replace(',', '.'));
            }

            // Формула бойынша Y(i) есептеу
            for (int i = 1; i <= 10; i++) {
                double prod = 1.0;
                double sum = 0.0;
                for (int j = 0; j < i; j++) {
                    prod *= k[j];
                    sum += k[j];
                }

                double diff = Math.abs(prod - sum);
                // i! / i = (i - 1)!
                double fact = 1.0;
                for (int f = 1; f < i; f++) fact *= f;

                double tanVal = Math.pow(Math.tan((double) i / 2.0), 2);
                double yi = (Math.sqrt(diff) / fact) * tanVal;

                model.setValueAt(String.format("%.2f", yi), i - 1, 1);
            }

        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Есептеу қатесі: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab4().setVisible(true));
    }
}