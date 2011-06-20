package ruffkat.swing.ui;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public enum Media {
    ICONS, IMAGES;

    public URL resource(String id) {
        String resource = "/media/" + name().toLowerCase() + "/" + id;
        return getClass().getResource(resource);
    }

    public static ImageIcon icon(String id) {
        return new ImageIcon(ICONS.resource(id));
    }

    public static Image iconImage(String id) {
        return icon(id).getImage();
    }

    public static Image image(String id) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.createImage(IMAGES.resource(id));
    }
}
