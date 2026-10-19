package com.mycompany.l01;

import javax.swing.*;
import java.awt.*;

public class Lab5 extends JFrame {
    private JList<String> listPeople;
    private JList<String> listWishes;
    private final JTextArea txtGreetings;

    public Lab5() {
        setTitle("Java-да жолдармен жұмыс (5-зертханалық жұмыс)");
        setSize(680, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1. Сол жақ: Туған күн иелерінің тізімі
        JLabel lblP = new JLabel("Туған күн иелерінің тізімі:");
        lblP.setBounds(20, 10, 200, 20);
        add(lblP);

        String[] people = {"Иванов", "Петров", "Сидоров", "Иваненко", "Петренко", "Сидоренко", "Иванченко", "Петренченко", "Сидоренченко"};
        DefaultListModel<String> mP = new DefaultListModel<>();
        for (String p : people) mP.addElement(p);
        listPeople = new JList<>(mP);
        listPeople.setBackground(new Color(230, 230, 255)); // күлгін-көк фон

        JScrollPane spPeople = new JScrollPane(listPeople);
        spPeople.setBounds(20, 35, 300, 150);
        add(spPeople);

        JButton btnClearP = new JButton("Тазалау");
        btnClearP.setBounds(110, 190, 110, 25);
        btnClearP.addActionListener(e -> listPeople.clearSelection());
        add(btnClearP);

        // 2. Оң жақ: Тілектер тізімі
        JLabel lblW = new JLabel("Тілектер тізімі:");
        lblW.setBounds(345, 10, 200, 20);
        add(lblW);

        String[] wishes = {"Бақыт", "Денсаулық", "Жетістік", "Махаббат", "Сәттілік", "Барлық жақсылық", "Жақындардың амандығы", "Бизнестегі табыс", "Оқудағы сәттілік", "Жеке өмірдегі бақыт"};
        DefaultListModel<String> mW = new DefaultListModel<>();
        for (String w : wishes) mW.addElement(w);
        listWishes = new JList<>(mW);
        listWishes.setBackground(new Color(255, 230, 230)); // ашық қызғылт фон

        JScrollPane spWishes = new JScrollPane(listWishes);
        spWishes.setBounds(345, 35, 300, 150);
        add(spWishes);

        JButton btnClearW = new JButton("Тазалау");
        btnClearW.setBounds(440, 190, 110, 25);
        btnClearW.addActionListener(e -> listWishes.clearSelection());
        add(btnClearW);

        // 3. Ортада: Сары фонды құттықтау аймағы
        txtGreetings = new JTextArea();
        txtGreetings.setBackground(new Color(255, 255, 215)); // сары фон
        txtGreetings.setForeground(new Color(150, 0, 0)); // қоңыр-қызыл қаріп
        txtGreetings.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        txtGreetings.setEditable(false);

        JScrollPane spText = new JScrollPane(txtGreetings);
        spText.setBounds(20, 225, 625, 200);
        add(spText);

        // 4. Төменгі батырмалар
        JButton btnCongrat = new JButton("Құттықтау");
        btnCongrat.setBounds(415, 435, 110, 30);
        btnCongrat.addActionListener(e -> generateGreetings(mP, mW));
        add(btnCongrat);

        JButton btnClose = new JButton("Жабу");
        btnClose.setBounds(535, 435, 110, 30);
        btnClose.addActionListener(e -> System.exit(0));
        add(btnClose);
    }

    private void generateGreetings(DefaultListModel<String> mP, DefaultListModel<String> mW) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mP.size(); i++) {
            String name = mP.get(i);
            String wish = mW.get((i * 3 + 2) % mW.size());
            sb.append(String.format("Құрметті %s! Сізге %s тілейміз!\n", name, wish.toLowerCase()));
        }
        txtGreetings.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab5().setVisible(true));
    }
}