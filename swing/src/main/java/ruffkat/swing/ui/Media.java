package ruffkat.swing.ui;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

public enum Media {
    ICONS, IMAGES;

    public URL resource(String name) {
        String resource = "/media/" + name().toLowerCase() + "/" + name;
        return getClass().getResource(resource);
    }

    public static ImageIcon icon(String name) {
        return new ImageIcon(ICONS.resource(name));
    }

    public static Image iconImage(String name) {
        return icon(name).getImage();
    }
}
