package com.mycompany.l01;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Lab7 extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    // Пайдаланушының таңдауларын сақтау
    private String q2 = "Сүйікті ойыншық", a2 = "Құрастырғыш Lego";
    private String q3 = "Сүйікті пән", a3 = "Информатика";
    private String q4 = "Мамандық таңдау", a4 = "IT бағдарламашы";

    private DefaultTableModel summaryModel;

    public Lab7() {
        setTitle("Көп терезелі бағдарлама (7-зертханалық жұмыс)");
        setSize(540, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel.add(makeScreen1(), "1");
        mainPanel.add(makeScreen2(), "2");
        mainPanel.add(makeScreen3(), "3");
        mainPanel.add(makeScreen4(), "4");
        mainPanel.add(makeScreen5(), "5");
        add(mainPanel);
    }

    // 1-терезе: Приветствие
    private JPanel makeScreen1() {
        JPanel p = new JPanel(new BorderLayout(15, 15));
        p.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel lbl = new JLabel("<html><center><h2>Жылдам сауалнама жүйесіне қош келдіңіз!</h2><p>Тақырып: Өмірлік кезеңдер</p></center></html>", SwingConstants.CENTER);
        p.add(lbl, BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnExit = new JButton("Шығу");
        btnExit.addActionListener(e -> System.exit(0));
        JButton btnStart = new JButton("Бастау ->");
        btnStart.addActionListener(e -> cardLayout.show(mainPanel, "2"));

        nav.add(btnExit);
        nav.add(btnStart);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }

    // 2-терезе: Балалық шақ (JList қолдану)
    private JPanel makeScreen2() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        p.add(new JLabel("2-терезе: Балалық шақ (7 жасқа дейін) - Тізімнен таңдаңыз:", SwingConstants.CENTER), BorderLayout.NORTH);

        String[] items = {"1. Сүйікті ойыншық (Lego)", "2. Ұнатқан ертегі (Алдар Көсе)", "3. Балабақшадағы дос (Арман)"};
        JList<String> list = new JList<>(items);
        list.setSelectedIndex(0);
        list.addListSelectionListener(e -> {
            int idx = list.getSelectedIndex();
            switch (idx) {
                case 0:
                    q2 = "Сүйікті ойыншық";
                    a2 = "Lego құрастырғышы";
                    break;
                case 1:
                    q2 = "Ұнатқан ертегі";
                    a2 = "Алдар Көсе";
                    break;
                default:
                    q2 = "Балабақшадағы дос";
                    a2 = "Арман";
                    break;
            }
        });
        p.add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton b = new JButton("<- Артқа"); b.addActionListener(e -> cardLayout.show(mainPanel, "1"));
        JButton n = new JButton("Алға ->"); n.addActionListener(e -> cardLayout.show(mainPanel, "3"));
        nav.add(b); nav.add(n);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }

    // 3-терезе: Отрочество (JComboBox қолдану)
    private JPanel makeScreen3() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        p.add(new JLabel("3-терезе: Жасөспірімдік шақ (7-16 жас) - Сұрақты таңдаңыз:", SwingConstants.CENTER), BorderLayout.NORTH);

        String[] items = {"Сүйікті пән (Информатика)", "Спорт секциясы (Жүзу)", "Оқыған кітап (Шерлок Холмс)"};
        JComboBox<String> cb = new JComboBox<>(items);
        JLabel lblAns = new JLabel("Таңдалған жауап: Информатика", SwingConstants.CENTER);

        cb.addActionListener(e -> {
            int idx = cb.getSelectedIndex();
            switch (idx) {
                case 0:
                    q3 = "Сүйікті пән";
                    a3 = "Информатика";
                    break;
                case 1:
                    q3 = "Спорт секциясы";
                    a3 = "Жүзу";
                    break;
                default:
                    q3 = "Оқыған кітап";
                    a3 = "Шерлок Холмс";
                    break;
            }
            lblAns.setText("Таңдалған жауап: " + a3);
        });

        JPanel c = new JPanel(new GridLayout(2, 1, 10, 10));
        c.add(cb); c.add(lblAns);
        p.add(c, BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton b = new JButton("<- Артқа"); b.addActionListener(e -> cardLayout.show(mainPanel, "2"));
        JButton n = new JButton("Алға ->"); n.addActionListener(e -> cardLayout.show(mainPanel, "4"));
        nav.add(b); nav.add(n);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }

    // 4-терезе: Юность (JRadioButton қолдану)
    private JPanel makeScreen4() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        p.add(new JLabel("4-терезе: Жастық шақ (16-23 жас) - Біреуін белгілеңіз:", SwingConstants.CENTER), BorderLayout.NORTH);

        JRadioButton r1 = new JRadioButton("Мамандық таңдау: IT бағдарламашы", true);
        JRadioButton r2 = new JRadioButton("Оқу гранты: Иә, грант иегері");
        JRadioButton r3 = new JRadioButton("Болашақ жоспар: Жеке стартап ашу");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2); bg.add(r3);

        r1.addActionListener(e -> { q4 = "Мамандық таңдау"; a4 = "IT бағдарламашы"; });
        r2.addActionListener(e -> { q4 = "Оқу гранты"; a4 = "Иә, грант иегері"; });
        r3.addActionListener(e -> { q4 = "Болашақ жоспар"; a4 = "Жеке стартап ашу"; });

        JPanel c = new JPanel(new GridLayout(3, 1));
        c.add(r1); c.add(r2); c.add(r3);
        p.add(c, BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton b = new JButton("<- Артқа"); b.addActionListener(e -> cardLayout.show(mainPanel, "3"));
        JButton n = new JButton("Алға ->"); n.addActionListener(e -> {
            updateSummaryTable();
            cardLayout.show(mainPanel, "5");
        });
        nav.add(b); nav.add(n);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }

    // 5-терезе: Спасибо! (JTable кестесінде қорытындылау)
    private JPanel makeScreen5() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        p.add(new JLabel("5-терезе: Сауалнама нәтижесі (Рахмет!)", SwingConstants.CENTER), BorderLayout.NORTH);

        summaryModel = new DefaultTableModel(new String[]{"Сұрақ / Кезең", "Сіздің жауабыңыз"}, 3);
        JTable table = new JTable(summaryModel);
        table.setRowHeight(25);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton b = new JButton("<- Артқа"); b.addActionListener(e -> cardLayout.show(mainPanel, "4"));
        JButton exit = new JButton("Шығу"); exit.addActionListener(e -> System.exit(0));
        nav.add(b); nav.add(exit);
        p.add(nav, BorderLayout.SOUTH);
        return p;
    }

    private void updateSummaryTable() {
        summaryModel.setValueAt(q2, 0, 0); summaryModel.setValueAt(a2, 0, 1);
        summaryModel.setValueAt(q3, 1, 0); summaryModel.setValueAt(a3, 1, 1);
        summaryModel.setValueAt(q4, 2, 0); summaryModel.setValueAt(a4, 2, 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab7().setVisible(true));
    }
}