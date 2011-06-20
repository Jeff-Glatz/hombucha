package ruffkat.swing.task;

import ruffkat.swing.action.ActionConfiguration;
import ruffkat.swing.ui.UserInterface;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Component;

/**
 * A {@link ListCellRenderer} that renders an interface exposing a
 * cancel function
 *
 * @see TaskPanel
 * @see TaskEditor
 * @see TaskListModel
 */
public class TaskRenderer extends TaskPanel implements ListCellRenderer {
    private final Color selectionBackground;

    public TaskRenderer(UserInterface ui, ActionConfiguration actionConfiguration) {
        super(ui, actionConfiguration);
        setName("Task.cellRenderer");
        selectionBackground = ui.color("ListCellRenderer.selectionBackground");
    }

    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean selected, boolean focused) {
        setTask((Task<?>) value);

        setComponentOrientation(list.getComponentOrientation());
        if (selected) {
            setBackground(selectionBackground);
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setFont(list.getFont());

        Border border = null;
        if (focused) {
            if (selected) {
                border = ui.border("List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = ui.border("List.focusCellHighlightBorder");
            }
            setBorder(border);
        }

        return this;
    }
}
