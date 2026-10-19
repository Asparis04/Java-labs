package com.mycompany.l01;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Lab9 extends JFrame {
    private final JTextArea txtInput = new JTextArea(6, 25);
    private final JTextArea txtOutput = new JTextArea(6, 25);
    private final int[][] matrix = new int[5][6];

    public Lab9() {
        setTitle("Файлдармен және массивтермен жұмыс (9-жұмыс)");
        setSize(460, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Бастапқы деректер аймағы
        JLabel lblIn = new JLabel("Файлдан алынған бастапқы деректер:");
        lblIn.setBounds(25, 10, 400, 20);
        add(lblIn);

        txtInput.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtInput.setEditable(false);
        JScrollPane spIn = new JScrollPane(txtInput);
        spIn.setBounds(25, 32, 395, 95);
        add(spIn);

        // 2. Өңделген деректер аймағы
        JLabel lblOut = new JLabel("Өңделген деректер:");
        lblOut.setBounds(25, 135, 400, 20);
        add(lblOut);

        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtOutput.setEditable(false);
        JScrollPane spOut = new JScrollPane(txtOutput);
        spOut.setBounds(25, 157, 395, 95);
        add(spOut);

        // 3. Үш батырма
        JButton btnLoad = new JButton("Файлдан бастапқы деректерді жүктеу");
        btnLoad.setBounds(25, 265, 395, 30);
        btnLoad.addActionListener(e -> loadData());
        add(btnLoad);

        JButton btnProcess = new JButton("Деректерді өңдеу");
        btnProcess.setBounds(25, 302, 395, 30);
        btnProcess.addActionListener(e -> processData());
        add(btnProcess);

        JButton btnSave = new JButton("Өңделген деректерді файлға сақтау");
        btnSave.setBounds(25, 339, 395, 30);
        btnSave.addActionListener(e -> saveData());
        add(btnSave);

        // 4. Тапсырма шарты
        JLabel lblRule = new JLabel("<html><center style='color:darkblue; font-size:11px;'>"
                + "Егер 2-бағанның барлық элементтері 1-ге тең болса,<br>"
                + "кестенің ең үлкен элементін 100-ге, ал ең кіші элементін 5-ке ауыстыру"
                + "</center></html>", SwingConstants.CENTER);
        lblRule.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblRule.setBounds(25, 378, 395, 55);
        add(lblRule);

        loadData();
    }

    private void loadData() {
        File file = new File("input.txt");
        // Файл жоқ болса, үлгі файл автоматты түрде құрылады
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                pw.println(" 1  1  0  4  5  6");
                pw.println(" 7  1  9 10 11 12");
                pw.println("13  1 15 16 17 18");
                pw.println("19  1 21 22 23 24");
                pw.println("25  1 27 28 29 30");
            } catch (IOException ignored) {}
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                String line = br.readLine();
                if (line == null) break;
                String[] parts = line.trim().split("\\s+");
                for (int j = 0; j < 6 && j < parts.length; j++) {
                    matrix[i][j] = Integer.parseInt(parts[j]);
                    sb.append(String.format("%4d", matrix[i][j]));
                }
                sb.append("\n");
            }
            txtInput.setText(sb.toString());
            txtOutput.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Файлды оқу қатесі: " + ex.getMessage());
        }
    }

    private void processData() {
        // 1. Екінші баған элементтері 1-ге тең бе екенін тексеру
        boolean allOnes = true;
        for (int i = 0; i < 5; i++) {
            if (matrix[i][1] != 1) {
                allOnes = false;
                break;
            }
        }

        if (!allOnes) {
            JOptionPane.showMessageDialog(this, "2-баған элементтері 1-ге тең емес!");
            return;
        }

        // 2. Ең үлкен және ең кіші мәндерді табу
        int maxV = Integer.MIN_VALUE, minV = Integer.MAX_VALUE;
        int maxR = 0, maxC = 0, minR = 0, minC = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (matrix[i][j] > maxV) { maxV = matrix[i][j]; maxR = i; maxC = j; }
                if (matrix[i][j] < minV) { minV = matrix[i][j]; minR = i; minC = j; }
            }
        }

        // 3. Ауыстыру: max -> 100, min -> 5
        matrix[maxR][maxC] = 100;
        matrix[minR][minC] = 5;

        // Өңделген мәтінді шығару
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                sb.append(String.format("%4d", matrix[i][j]));
            }
            sb.append("\n");
        }
        txtOutput.setText(sb.toString());
        JOptionPane.showMessageDialog(this, "Сәтті өңделді! Max -> 100, Min -> 5");
    }

    private void saveData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
            pw.print(txtOutput.getText());
            JOptionPane.showMessageDialog(this, "Деректер output.txt файлына сақталды!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Сақтау қатесі: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab9().setVisible(true));
    }
}