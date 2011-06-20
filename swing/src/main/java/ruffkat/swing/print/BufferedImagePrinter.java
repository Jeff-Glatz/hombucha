package ruffkat.swing.print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class BufferedImagePrinter implements Printable {
    private final BufferedImage image;

    public BufferedImagePrinter(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g = (Graphics2D) graphics;

        g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int iw = (int) pageFormat.getImageableWidth();
        int ih = (int) pageFormat.getImageableHeight();

        // ensure image will fit
        int dw = w;
        int dh = h;
        if (dw > iw) {
            dh = (int) (dh * ((float) iw / (float) dw));
            dw = iw;
        }
        if (dh > ih) {
            dw = (int) (dw * ((float) ih / (float) dh));
            dh = ih;
        }
        // centre on page
        int dx = (iw - dw) / 2;
        int dy = (ih - dh) / 2;

        g.drawImage(image, dx, dy, dx + dw, dy + dh, 0, 0, w, h, null);
        return Printable.PAGE_EXISTS;
    }
}
