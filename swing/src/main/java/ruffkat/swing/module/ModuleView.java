package ruffkat.swing.module;

import org.jdesktop.swingx.JXTitledPanel;
import ruffkat.swing.action.ActionCallback;
import ruffkat.swing.action.TwoPhaseAction;
import ruffkat.swing.ui.UserInterface;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

public class ModuleView extends JPanel implements ActionCallback {
    private final UserInterface ui;
    private final ModulePanel activated;
    private final TwoPhaseAction action;

    public ModuleView(UserInterface ui, ModulePanel activated) {
        this(ui, activated, null);
    }

    public ModuleView(UserInterface ui, ModulePanel activated, TwoPhaseAction action) {
        super(new CardLayout());
        this.ui = ui;
        this.activated = activated;
        this.action = action;

        ModulePanel deactivated = new ModulePanel(ui, activated.getTitleKey());
        deactivated.add(ui.makeCenteredLabel("ModuleView.deactivatedText"), BorderLayout.CENTER);
        add(deactivated, "deactivated");
        add(activated, "activated");

        if (action != null) {
            action.addCallback(this);
        }
    }

    public void toggle() {
        CardLayout cards = (CardLayout) getLayout();
        cards.next(this);
    }

    public void setActive(boolean active) {
        CardLayout cards = (CardLayout) getLayout();
        cards.show(this, active ? "activated" : "deactivated");
    }

    public void completed() {
        setActive(true);
    }

    public void failed(Throwable cause) {
        setActive(false);
    }

    public JXTitledPanel createTitledPanel() {
        JXTitledPanel titledPanel = new JXTitledPanel(activated.getTitle(), this);
        titledPanel.setBorder(BorderFactory.createEtchedBorder());
        titledPanel.setVisible(isVisible());
        titledPanel.setEnabled(isEnabled());
        if (action != null) {
            titledPanel.setRightDecoration(new ModuleButton(action, ui));
        }
        return titledPanel;
    }
}
