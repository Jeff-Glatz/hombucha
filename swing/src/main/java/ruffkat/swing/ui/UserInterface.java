package ruffkat.swing.ui;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Image;
import java.util.Map;

public class UserInterface {
    private Map<Object, Object> mappings;
    private boolean lookAndFeelDefaults;

    private UIDefaults defaults;

    public Map<Object, Object> getMappings() {
        return mappings;
    }

    public void setMappings(Map<Object, Object> mappings) {
        this.mappings = mappings;
    }

    public boolean isLookAndFeelDefaults() {
        return lookAndFeelDefaults;
    }

    public void setLookAndFeelDefaults(boolean lookAndFeelDefaults) {
        this.lookAndFeelDefaults = lookAndFeelDefaults;
    }

    public void initialize()
            throws Exception {
        defaults = lookAndFeelDefaults ?
                UIManager.getLookAndFeelDefaults() :
                UIManager.getDefaults();
        for (Map.Entry<Object, Object> entry : mappings.entrySet()) {
            defaults.put(entry.getKey(), entry.getValue());
        }
    }

    public UIDefaults getDefaults() {
        return defaults;
    }

    public Image image(Object key) {
        return (Image) defaults.get(key);
    }

    public Icon icon(Object key) {
        return defaults.getIcon(key);
    }

    public Color color(Object key) {
        return defaults.getColor(key);
    }

    public String string(Object key) {
        return defaults.getString(key);
    }

    public int integer(Object key) {
        return defaults.getInt(key);
    }

    public Border border(Object key) {
        return defaults.getBorder(key);
    }

    public JLabel makeCenteredLabel(String key) {
        return new JLabel(string(key), JLabel.CENTER);
    }
}
