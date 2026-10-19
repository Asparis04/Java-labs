package com.mycompany.l01;

import javax.swing.*;
import java.awt.*;

public class Lab6 extends JFrame {

    // Графика салынатын жеке панель
    static class BobberPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 1. Артқы сұр фон
            g2.setColor(new Color(110, 115, 125));
            g2.fillRect(0, 0, getWidth(), getHeight());

            // 2. Жоғарғы "Java!" жазуы
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 32));
            int strW = g2.getFontMetrics().stringWidth("Java!");
            g2.drawString("Java!", (getWidth() - strW) / 2, 45);

            int cx = getWidth() / 2;
            int cy = 135;

            // 3. Жоғарғы жіңішке стержень және қызыл шар
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(cx - 2, cy - 35, 4, 35);
            g2.setColor(Color.RED);
            g2.fillOval(cx - 6, cy - 42, 12, 12);
            g2.setColor(Color.BLACK);
            g2.drawOval(cx - 6, cy - 42, 12, 12);

            // 4. Қалтқының негізгі денесі (дөңгелек)
            int d = 110;
            int x = cx - d / 2;
            int y = cy - 5;

            // Жоғарғы ақ жарты шар
            g2.setColor(Color.WHITE);
            g2.fillArc(x, y, d, d, 0, 180);

            // Төменгі қызыл жарты шар
            g2.setColor(new Color(220, 30, 30));
            g2.fillArc(x, y, d, d, 180, 180);

            // Дене контуры мен ортаңғы сызық
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(x, y, d, d);
            g2.drawLine(x, y + d / 2, x + d, y + d / 2);

            // 5. Төменгі ілмек
            g2.setColor(Color.WHITE);
            g2.fillOval(cx - 5, y + d - 3, 10, 10);
            g2.setColor(Color.BLACK);
            g2.drawOval(cx - 5, y + d - 3, 10, 10);

            // 6. Автор және нұсқа нөмірі (талап бойынша)
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 13));
            String sign = "А.Б. - 22 нұсқа";
            int signW = g2.getFontMetrics().stringWidth(sign);
            g2.drawString(sign, (getWidth() - signW) / 2, y + d + 30);
        }
    }

    public Lab6() {
        setTitle("Java-да графикамен жұмыс (6-зертханалық жұмыс)");
        setSize(380, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Ортаға сурет панелін орналастыру
        add(new BobberPanel(), BorderLayout.CENTER);

        // Төменгі жазу
        JLabel lblBottom = new JLabel("Бұл сурет пен жазу бағдарламалық түрде салынған", SwingConstants.CENTER);
        lblBottom.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBottom.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
        add(lblBottom, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab6().setVisible(true));
    }
}