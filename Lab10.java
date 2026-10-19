package com.mycompany.l01;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

// Тапсырма талабы бойынша массивті өңдейтін жеке класс
class ArrayProcessor {
    public static double factorial(int n) {
        double f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }

    public static double calculateYi(double[] k, int i) {
        // i: 1-ден 10-ға дейін
        double curK = k[i - 1];
        double prevK = (i > 1) ? k[i - 2] : 0.0;

        // 1. Алым бөлігіндегі түбір іші
        double numeratorSqrt = Math.pow(Math.cos(curK), 2);
        double denominatorSqrt = factorial(i) + Math.pow(Math.sin(prevK), 2);
        double sqrtPart = Math.sqrt(numeratorSqrt / denominatorSqrt);

        // 2. Барлық алдыңғы элементтердің көбейтіндісі мен қосындысы
        double prod = 1.0;
        double sum = 0.0;
        for (int j = 0; j < i; j++) {
            prod *= k[j];
            sum += k[j];
        }

        if (sum == 0) return 0.0;
        return (sqrtPart * prod) / sum;
    }
}

public class Lab10 extends JFrame {
    private DefaultTableModel model = new DefaultTableModel(new String[]{"K(i)", "Y(i)"}, 10);
    private JTable table = new JTable(model);

    public Lab10() {
        setTitle("Java-да кластармен жұмыс (10-зертханалық жұмыс)");
        setSize(560, 410);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Сол жақтағы 10x2 кесте
        table.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 15, 230, 245);
        add(scroll);

        // 2. Оң жақтағы 4 батырма
        JButton btnRandom = new JButton("Кездейсоқ сандармен толтыру");
        btnRandom.setBounds(270, 20, 245, 30);
        btnRandom.addActionListener(e -> fillRandom());
        add(btnRandom);

        JButton btnCalc = new JButton("Есептеу");
        btnCalc.setBounds(270, 60, 245, 30);
        btnCalc.addActionListener(e -> calculate());
        add(btnCalc);

        JButton btnClear = new JButton("Тазалау");
        btnClear.setBounds(270, 100, 245, 30);
        btnClear.addActionListener(e -> {
            for (int i = 0; i < 10; i++) {
                model.setValueAt("", i, 0);
                model.setValueAt("", i, 1);
            }
        });
        add(btnClear);

        JButton btnExit = new JButton("Шығу");
        btnExit.setBounds(270, 140, 245, 30);
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);

        // 3. Төмендегі формула белгісі
        JLabel lblFormula = new JLabel("<html><div style='border:1px solid gray; padding:10px; background:white; font-size:12px;'>"
                + "<center><b>Формула:</b><br>"
                + "Y<sub>i</sub> = [ &radic;( cos<sup>2</sup>(K<sub>i</sub>) / (i! + sin<sup>2</sup>(K<sub>i-1</sub>)) ) &times; &prod;(K<sub>i</sub>) ] / &sum; K<sub>i</sub>"
                + "</center></div></html>");
        lblFormula.setBounds(20, 275, 495, 80);
        add(lblFormula);

        fillRandom();
    }

    private void fillRandom() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            model.setValueAt(r.nextInt(8) + 1, i, 0); // 1 мен 8 арасындағы бүтін сандар
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

            // Жеке класс (ArrayProcessor) арқылы есептеу
            for (int i = 1; i <= 10; i++) {
                double yi = ArrayProcessor.calculateYi(k, i);
                model.setValueAt(String.format("%.3f", yi), i - 1, 1);
            }
        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Қате: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab10().setVisible(true));
    }
}