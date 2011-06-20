package ruffkat.swing.ui;

import org.jdesktop.swingx.JXImageView;
import org.jdesktop.swingx.color.ColorUtil;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class ImageView extends JXImageView {
    private Paint backgroundPaint;

    public ImageView() {
        this(ColorUtil.getCheckerPaint(Color.white, new Color(240, 240, 240), 50));
    }

    public ImageView(Paint backgroundPaint) {
        this.backgroundPaint = backgroundPaint;
    }

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void setBackgroundPaint(Paint background) {
        this.backgroundPaint = background;
    }

    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setPaint(backgroundPaint);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (getImage() != null) {
            Point2D center = new Point2D.Double(getWidth() / 2, getHeight() / 2);
            if (getImageLocation() != null) {
                center = getImageLocation();
            }
            Point2D loc = new Point2D.Double();
            double width = getImage().getWidth(null) * getScale();
            double height = getImage().getHeight(null) * getScale();
            loc.setLocation(center.getX() - width / 2, center.getY() - height / 2);
            g.drawImage(getImage(), (int) loc.getX(), (int) loc.getY(), (int) width, (int) height, null);
        }
    }
}
