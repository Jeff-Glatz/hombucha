package ruffkat.swing.task;

import ruffkat.swing.action.ActionConfiguration;
import ruffkat.swing.ui.UserInterface;

import javax.swing.ComboBoxEditor;
import java.awt.Component;
import java.awt.event.ActionListener;

/**
 * A {@link ComboBoxEditor} that renders an interface exposing a
 * cancel function
 *
 * @see TaskPanel
 * @see TaskRenderer
 * @see TaskListModel
 */
public class TaskEditor extends TaskPanel implements ComboBoxEditor {

    public TaskEditor(UserInterface ui, ActionConfiguration actionConfiguration) {
        super(ui, actionConfiguration);
    }

    public Component getEditorComponent() {
        return this;
    }

    public void setItem(Object worker) {
        setTask((Task<?>) worker);
    }

    public Object getItem() {
        return getTask();
    }

    public void selectAll() {
        cancel.requestFocus();
    }

    public synchronized void addActionListener(ActionListener listener) {
        cancel.addActionListener(listener);
    }

    public synchronized void removeActionListener(ActionListener listener) {
        cancel.removeActionListener(listener);
    }
}
