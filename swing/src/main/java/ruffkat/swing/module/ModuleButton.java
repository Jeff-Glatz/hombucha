package ruffkat.swing.module;

import ruffkat.swing.action.ActionCallback;
import ruffkat.swing.action.TwoPhaseAction;
import ruffkat.swing.ui.UndecoratedButton;
import ruffkat.swing.ui.UserInterface;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import java.awt.Dimension;

public class ModuleButton extends UndecoratedButton implements ActionCallback {
    private final UserInterface ui;

    public ModuleButton(TwoPhaseAction action, UserInterface ui) {
        super(action);
        this.ui = ui;
        action.addCallback(this);
        setPreferredSize(new Dimension(20, 16));
        setMinimumSize(new Dimension(16, 16));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        installIcon(ui.icon("activate.disabled.icon"));
        setToolTipText(ui.string("ModuleButton.disabledText"));
    }

    public void completed() {
        installIcon(ui.icon("activate.disabled.icon"));
        setToolTipText(ui.string("ModuleButton.disabledText"));
        setVisible(false);
    }

    public void failed(Throwable cause) {
        installIcon(ui.icon("activate.failed.icon"));
        setToolTipText(ui.string("ModuleButton.failedText"));
        setVisible(true);
    }

    private void installIcon(Icon icon) {
        setIcon(icon);
        setDisabledIcon(icon);
    }
}
