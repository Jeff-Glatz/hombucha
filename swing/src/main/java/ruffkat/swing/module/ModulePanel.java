package ruffkat.swing.module;

import ruffkat.swing.ui.UserInterface;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ModulePanel extends JPanel {
    private final UserInterface ui;
    private final String titleKey;

    public ModulePanel(UserInterface ui, String titleKey) {
        this.ui = ui;
        this.titleKey = titleKey;
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public String getTitleKey() {
        return titleKey;
    }

    public String getTitle() {
        return ui.string(titleKey);
    }
}
