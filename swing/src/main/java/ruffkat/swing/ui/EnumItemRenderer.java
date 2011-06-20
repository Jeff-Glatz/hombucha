package ruffkat.swing.ui;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.util.ResourceBundle;

public class EnumItemRenderer extends DefaultListCellRenderer {
    private final ResourceBundle resources;

    public EnumItemRenderer(ResourceBundle resources) {
        this.resources = resources;
    }

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean selected,
                                                  boolean focused) {
        Component renderer = super.getListCellRendererComponent(
                list, value, index, selected, focused);
        if (value == null) {
            setEnabled(false);
            setText("<unselected>");
        } else {
            setEnabled(true);
            setText(localize((Enum) value));
        }
        return renderer;
    }

    private String localize(Enum item) {
        Class type = item.getDeclaringClass();
        return resources.getString(type.getSimpleName() + "." + item.name());
    }
}
