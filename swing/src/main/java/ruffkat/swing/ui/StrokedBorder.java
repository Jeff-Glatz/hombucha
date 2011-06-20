package ruffkat.swing.ui;

import javax.swing.border.AbstractBorder;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;

public class StrokedBorder extends AbstractBorder {

    public static enum Side {
        RIGHT() {
            public void draw(Graphics2D g, int x, int y, int width, int height) {
                g.draw(new Line2D.Double(x + width - 1, y, x + width - 1, height));
            }
        },
        LEFT() {
            public void draw(Graphics2D g, int x, int y, int width, int height) {
                g.draw(new Line2D.Double(x, y, x, height));
            }
        },
        TOP() {
            public void draw(Graphics2D g, int x, int y, int width, int height) {
                g.draw(new Line2D.Double(x, y, x + width, y));
            }
        },
        BOTTOM() {
            public void draw(Graphics2D g, int x, int y, int width, int height) {
                g.draw(new Line2D.Double(x, y + height - 1, x + width, y + height - 1));
            }
        };

        public abstract void draw(Graphics2D g, int x, int y, int width, int height);
    }

    private final Stroke stroke;
    private final Side[] sides;
    private final Paint paint;

    public StrokedBorder(Stroke stroke, Paint paint, Side... sides) {
        this.stroke = stroke;
        this.sides = sides;
        this.paint = paint;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke originalStroke = g2.getStroke();
        Paint originalPaint = g2.getPaint();
        g2.setStroke(stroke);
        g2.setPaint(paint);
        try {
            for (Side side : sides) {
                side.draw(g2, x, y, width, height);
            }
        } finally {
            g2.setPaint(originalPaint);
            g2.setStroke(originalStroke);
        }
    }
}
