package ruffkat.swing.ui;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;

public class TabHandler<T extends Component> implements ChangeListener {
    protected final JTabbedPane tabbedPane;

    private T previousComponent;

    public TabHandler(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public final void stateChanged(ChangeEvent e) {
        T currentComponent = selectedComponent();
        try {
            if (previousComponent != currentComponent) {
                if (currentComponent == null) {
                    onTabClosed(previousComponent);
                } else if (previousComponent == null) {
                    onTabOpened(currentComponent);
                } else {
                    onTabFocused(previousComponent, currentComponent);
                }
            }
        } finally {
            previousComponent = currentComponent;
        }
    }

    protected T selectedComponent() {
        return (T) tabbedPane.getSelectedComponent();
    }

    protected void onTabOpened(T component) {
    }

    protected void onTabFocused(T previous, T current) {
    }

    protected void onTabClosed(T component) {
    }
}
