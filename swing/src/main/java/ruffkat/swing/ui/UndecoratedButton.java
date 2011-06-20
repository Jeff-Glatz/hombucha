package ruffkat.swing.ui;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Graphics;

public class UndecoratedButton extends JButton {
    {
        setUI(new BasicButtonUI());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setBorderPainted(false);
        setFocusable(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setHideActionText(true);
    }

    public UndecoratedButton() {
    }

    public UndecoratedButton(Icon icon) {
        super(icon);
    }

    public UndecoratedButton(String text) {
        super(text);
    }

    public UndecoratedButton(Action a) {
        super(a);
    }

    public UndecoratedButton(String text, Icon icon) {
        super(text, icon);
    }

    public void updateUI() {

    }

    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.translate(1, 1);
        }
        super.paintComponent(g);
    }
}
