package ruffkat.swing.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class Textures {

    public static TexturePaint createVerticalPinstripe(Color back, Color fore) {
        BufferedImage image = new BufferedImage(1, 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        try {
            g.setColor(back);
            g.fillRect(0, 0, 1, 2);
            g.setColor(fore);
            g.fillRect(0, 1, 1, 1);
        } finally {
            g.dispose();
        }
        return new TexturePaint(image, new Rectangle(0, 0, 1, 2));
    }
}
