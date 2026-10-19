package com.mycompany.l01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Lab8 extends JFrame {
    private JComboBox<Integer> cbStart = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
    private JComboBox<Integer> cbEnd = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6, 7});

    // 22-нұсқадағы аялдамалардың 7 нүктесі (Вариант 22 координаты)
    static final Point[] STOPS = {
            new Point(90, 80),    // 1-аялдама
            new Point(90, 240),   // 2-аялдама
            new Point(200, 240),  // 3-аялдама
            new Point(200, 140),  // 4-аялдама
            new Point(330, 80),   // 5-аялдама
            new Point(330, 240),  // 6-аялдама
            new Point(430, 240)   // 7-аялдама
    };

    static class BusRoutePanel extends JPanel {
        private int busX = STOPS[0].x, busY = STOPS[0].y;
        private int currentLeg = 0, targetLeg = 0;
        private Timer timer;

        public BusRoutePanel() {
            setBackground(new Color(175, 215, 60)); // жасыл фон
        }

        public void startTrip(int startStop, int endStop) {
            if (timer != null && timer.isRunning()) timer.stop();
            currentLeg = startStop - 1;
            targetLeg = endStop - 1;
            busX = STOPS[currentLeg].x;
            busY = STOPS[currentLeg].y;

            // Swing таймері (әр 20 мс сайын автобус жылжиды)
            timer = new Timer(20, (ActionEvent e) -> {
                Point target = STOPS[currentLeg + 1];
                int dx = Integer.compare(target.x, busX);
                int dy = Integer.compare(target.y, busY);

                busX += dx * 3;
                busY += dy * 3;

                // Келесі аялдамаға жеткенін тексеру
                if (Math.abs(busX - target.x) <= 3 && Math.abs(busY - target.y) <= 3) {
                    busX = target.x;
                    busY = target.y;
                    currentLeg++;
                    if (currentLeg >= targetLeg) {
                        timer.stop();
                    }
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 1. Жол сызығы (қара қалың жол)
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(6));
            for (int i = 0; i < STOPS.length - 1; i++) {
                g2.drawLine(STOPS[i].x, STOPS[i].y, STOPS[i + 1].x, STOPS[i + 1].y);
            }

            // 2. 7 аялдаманы салу (сары дөңгелектер мен нөмірлер)
            g2.setStroke(new BasicStroke(3));
            for (int i = 0; i < STOPS.length; i++) {
                Point p = STOPS[i];
                g2.setColor(Color.YELLOW);
                g2.fillOval(p.x - 14, p.y - 14, 28, 28);
                g2.setColor(Color.BLACK);
                g2.drawOval(p.x - 14, p.y - 14, 28, 28);

                g2.setColor(Color.RED);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                g2.drawString(String.valueOf(i + 1), p.x + 16, p.y - 6);
            }

            // 3. Автобус (көк тіктөртбұрыш)
            g2.setColor(new Color(20, 80, 220));
            g2.fillRect(busX - 12, busY - 12, 24, 24);
            g2.setColor(Color.WHITE);
            g2.drawRect(busX - 12, busY - 12, 24, 24);
        }
    }

    public Lab8() {
        setTitle("Swing таймерлерімен жұмыс (8-зертханалық жұмыс)");
        setSize(540, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        BusRoutePanel routePanel = new BusRoutePanel();
        add(routePanel, BorderLayout.CENTER);

        // Төменгі қызғылт басқару тақтасы
        JPanel ctrl = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        ctrl.setBackground(new Color(245, 195, 205));

        JButton btnStart = new JButton("Қозғалыс");
        btnStart.addActionListener(e -> {
            int s = (Integer) cbStart.getSelectedItem();
            int end = (Integer) cbEnd.getSelectedItem();
            if (s >= end) {
                JOptionPane.showMessageDialog(this, "Соңғы аялдама бастапқы аялдамадан үлкен болуы тиіс!");
                return;
            }
            routePanel.startTrip(s, end);
        });

        ctrl.add(btnStart);
        ctrl.add(new JLabel("Бастапқы нүкте:")); ctrl.add(cbStart);
        ctrl.add(new JLabel("Соңғы нүкте:")); ctrl.add(cbEnd);
        add(ctrl, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab8().setVisible(true));
    }
}