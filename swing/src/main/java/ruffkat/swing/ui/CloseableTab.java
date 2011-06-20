package ruffkat.swing.ui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseableTab extends JPanel {

    private final JTabbedPane container;

    public CloseableTab(JTabbedPane container) {
        this.container = container;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        setOpaque(false);
        add(Box.createHorizontalGlue());
        add(new TabLabel());
        add(Box.createHorizontalStrut(5));
        add(new TabButton());
    }

    private class TabLabel extends JLabel {
        public String getText() {
            int i = container.indexOfTabComponent(CloseableTab.this);
            return (i != -1) ? container.getTitleAt(i) : null;
        }
    }

    private class TabButton extends UndecoratedButton implements ActionListener {

        public TabButton() {
            setIcon(UIManager.getIcon("close.normal.icon"));
            setPressedIcon(UIManager.getIcon("close.pressed.icon"));
            setRolloverIcon(UIManager.getIcon("close.rollover.icon"));
            setPreferredSize(new Dimension(16, 16));
            setToolTipText(UIManager.getString("CloseableTab.TabButton.toolTipText"));
            setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            int i = container.indexOfTabComponent(CloseableTab.this);
            if (i != -1) {
                container.remove(i);
            }
        }
    }

    public static CloseableTab addTab(JTabbedPane container, String name, Component component) {
        CloseableTab tab = new CloseableTab(container);
        container.addTab(name, component);
        container.setSelectedComponent(component);
        container.setTabComponentAt(container.getSelectedIndex(), tab);
        return tab;
    }
}


