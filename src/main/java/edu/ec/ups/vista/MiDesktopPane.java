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

        // 🎨 Líneas curvas decorativas
        g2d.setStroke(new BasicStroke(2f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 160, 90, 100));

        for (int i = 0; i < 5; i++) {
            int yOffset = 150 + i * 60;
            Shape curva = new CubicCurve2D.Float(0, yOffset, w / 3, yOffset - 30, 2 * w / 3, yOffset + 30, w, yOffset);
            g2d.draw(curva);
        }

        // Configuración del texto
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Título "Ñemu" centrado
        String titulo = "Ñemu";
        g2d.setFont(new Font("SansSerif", Font.BOLD, 72));
        FontMetrics fmTitulo = g2d.getFontMetrics();
        int xTitulo = (w - fmTitulo.stringWidth(titulo)) / 2;
        int yCentro = h / 2;

        g2d.setColor(new Color(255, 140, 0)); // sombra
        g2d.drawString(titulo, xTitulo + 3, yCentro + 3);
        g2d.setColor(Color.WHITE);
        g2d.drawString(titulo, xTitulo, yCentro);

        // Slogan estilo Temu
        String slogan = "Compra fácil. Vive mejor.";
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 22));
        FontMetrics fmSlogan = g2d.getFontMetrics();
        int xSlogan = (w - fmSlogan.stringWidth(slogan)) / 2;
        int ySlogan = yCentro + 40;

        g2d.setColor(new Color(90, 90, 90));
        g2d.drawString(slogan, xSlogan, ySlogan);

        g2d.dispose();

    }

}
