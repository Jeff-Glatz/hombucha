package ruffkat.swing.task;

import ruffkat.swing.action.ActionConfiguration;
import ruffkat.swing.ui.UndecoratedButton;
import ruffkat.swing.ui.UserInterface;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import java.awt.Dimension;

/**
 * A simple visual representation of a {@link Task} which provides
 * the ability to cancel an active {@link Task}
 *
 * @see Task
 * @see CancelTaskAction
 */
public class TaskPanel extends JPanel {
    protected final UserInterface ui;
    protected final CancelTaskAction action;
    protected final JLabel label;
    protected final JButton cancel;

    public TaskPanel(UserInterface ui, ActionConfiguration actionConfiguration) {
        this.ui = ui;
        action = new CancelTaskAction();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        label = new JLabel();
        label.setText(label());

        cancel = new UndecoratedButton();
        cancel.setHideActionText(true);
        cancel.setPreferredSize(new Dimension(16, 16));
        cancel.setAction(actionConfiguration.configure("task.cancel", action));
        cancel.setIcon(ui.icon("close.normal.icon"));
        cancel.setPressedIcon(ui.icon("close.pressed.icon"));
        cancel.setRolloverIcon(ui.icon("close.rollover.icon"));

        add(label);
        add(Box.createHorizontalGlue());
        add(cancel);
    }

    protected <V> void setTask(Task<V> task) {
        action.setTask(task);
        if (task != null) {
            label.setText(label(task));
            cancel.setVisible(true);
        } else {
            cancel.setVisible(false);
            label.setText(label());
        }
    }

    protected <V> Task<V> getTask() {
        return action.getTask();
    }

    protected <V> String label(Task<V> task) {
        return label(task.getDescription(), task.getState());
    }

    protected String label() {
        return label(null, null);
    }

    private String label(String description, SwingWorker.StateValue state) {
        StringBuilder builder = new StringBuilder();
        if (description != null) {
            builder.append(description).append(" ");
        }
        builder.append(ui.string("TaskPanel." + (state == null ? "IDLE" : state) + ".text"));
        return builder.toString();
    }
}
