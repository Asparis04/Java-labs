package com.mycompany.l01;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;

public class Lab3 extends JFrame {
    private final DefaultTableModel model = new DefaultTableModel(4, 5);
    private final JTable table = new JTable(model);
    private final JLabel lblResult = new JLabel("Ең үлкен элемент -  [ , ]  Қосындысы - ");

    public Lab3() {
        setTitle("JAVA-да визуалды кестелік деректермен жұмыс (3-жұмыс)");
        setSize(650, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Сол жақтағы 4x5 кесте
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 380, 130);
        add(scroll);

        // 2. Оң жақтағы екі батырма
        JButton btnRandom = new JButton("Кездейсоқ сандармен толтыру");
        btnRandom.setBounds(415, 30, 205, 35);
        btnRandom.addActionListener(e -> fillRandom());
        add(btnRandom);

        JButton btnRun = new JButton("Тапсырманы орындау");
        btnRun.setBounds(415, 85, 205, 35);
        btnRun.addActionListener(e -> processTable());
        add(btnRun);

        // 3. Төмендегі нәтиже жолы
        lblResult.setFont(new Font("Arial", Font.BOLD, 13));
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblResult.setBounds(20, 165, 600, 25);
        add(lblResult);

        // 4. Тапсырманың мәтіні жазылған жақтау
        JLabel lblTask = new JLabel("<html><center>"
                + "Кестенің ең үлкен элементінің алдында орналасқан элементтердің қосындысын табу,<br>"
                + "және кестенің барлық нөлдік элементтерін осы мәнге ауыстыру"
                + "</center></html>", SwingConstants.CENTER);
        lblTask.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblTask.setBounds(20, 200, 600, 90);
        add(lblTask);

        fillRandom();
    }

    // 0-ден 100-ге дейінгі сандармен толтыру
    private void fillRandom() {
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int val = r.nextInt(101);
                if (r.nextInt(5) == 0) val = 0; // нөлдер кездесуі үшін
                model.setValueAt(val, i, j);
            }
        }
        lblResult.setText("Кесте толтырылды.");
    }

    // Есептеу логикасы
    private void processTable() {
        int maxVal = Integer.MIN_VALUE;
        int maxR = -1, maxC = -1;

        // 1. Ең үлкен элементті және оның орнын табу
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(model.getValueAt(i, j).toString());
                if (val > maxVal) {
                    maxVal = val;
                    maxR = i;
                    maxC = j;
                }
            }
        }

        // 2. Оған дейінгі элементтердің қосындысын табу
        int sum = 0;
        boolean reached = false;
        for (int i = 0; i < 4 && !reached; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == maxR && j == maxC) {
                    reached = true;
                    break;
                }
                sum += Integer.parseInt(model.getValueAt(i, j).toString());
            }
        }

        // 3. Нөлдік элементтерді осы қосындыға ауыстыру
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(model.getValueAt(i, j).toString());
                if (val == 0) {
                    model.setValueAt(sum, i, j);
                }
            }
        }

        lblResult.setText(String.format("Ең үлкен элемент - %d [%d,%d]  Қосындысы - %d", maxVal, maxR + 1, maxC + 1, sum));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab3().setVisible(true));
    }
}