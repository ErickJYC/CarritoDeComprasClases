package edu.ec.ups.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;

public class MiDesktopPane extends JDesktopPane {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();

        // Fondo degradado
        GradientPaint fondo = new GradientPaint(0, 0, new Color(255, 240, 220), 0, h, new Color(255, 210, 180));
        g2d.setPaint(fondo);
        g2d.fillRect(0, 0, w, h);

        // Curvas decorativas
        g2d.setStroke(new BasicStroke(2f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 160, 90, 80));
        for (int i = 0; i < 4; i++) {
            int yOffset = 120 + i * 60;
            Shape curva = new CubicCurve2D.Float(0, yOffset, w / 3, yOffset - 30, 2 * w / 3, yOffset + 30, w, yOffset);
            g2d.draw(curva);
        }

        // Título centrado
        String titulo = "Ñemu";
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 72));
        FontMetrics fmTitulo = g2d.getFontMetrics();
        int xTitulo = (w - fmTitulo.stringWidth(titulo)) / 2;
        int yCentro = h / 3;

        g2d.setColor(new Color(255, 140, 0)); // sombra
        g2d.drawString(titulo, xTitulo + 4, yCentro + 4);
        g2d.setColor(Color.WHITE);
        g2d.drawString(titulo, xTitulo, yCentro);

        // Slogan
        String slogan = "Compra fácil. Vive mejor.";
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 24));
        FontMetrics fmSlogan = g2d.getFontMetrics();
        int xSlogan = (w - fmSlogan.stringWidth(slogan)) / 2;
        g2d.setColor(new Color(90, 90, 90));
        g2d.drawString(slogan, xSlogan, yCentro + 40);

        // 🛒 Carrito grande en la izquierda
        int cartX = 60;
        int cartY = h - 180;
        g2d.setColor(new Color(255, 130, 40));
        g2d.fillRoundRect(cartX, cartY, 120, 60, 15, 15); // cuerpo
        g2d.setColor(new Color(230, 90, 20));
        g2d.fillOval(cartX + 10, cartY + 55, 20, 20);
        g2d.fillOval(cartX + 90, cartY + 55, 20, 20);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawLine(cartX + 120, cartY, cartX + 150, cartY - 40); // asa

        // 📦 Caja grande en la derecha
        int boxX = w - 200;
        int boxY = h - 190;
        g2d.setColor(new Color(210, 120, 50));
        g2d.fillRect(boxX, boxY, 100, 70); // caja base
        g2d.setColor(new Color(180, 90, 30));
        g2d.drawLine(boxX, boxY, boxX + 50, boxY - 30);
        g2d.drawLine(boxX + 100, boxY, boxX + 50, boxY - 30);
        g2d.drawLine(boxX + 50, boxY - 30, boxX + 50, boxY + 20); // tapa

        // 🛍 Bolsa central abajo
        int bagX = w / 2 - 40;
        int bagY = h - 170;
        g2d.setColor(new Color(255, 200, 120));
        g2d.fillRoundRect(bagX, bagY, 80, 100, 20, 20);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawArc(bagX + 10, bagY - 15, 60, 30, 0, 180); // asas

        g2d.dispose();
    }
}
