package ruffkat.swing.task;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/**
 * A simple {@link AbstractAction} that will attempt to
 * cancel a configured {@link Task} when performed
 *
 * @see Task
 */
public class CancelTaskAction extends AbstractAction {
    private Task task;
    private boolean interruptAllowed = true;

    public <V> Task<V> getTask() {
        return task;
    }

    public <V> void setTask(Task<V> task) {
        this.task = task;
    }

    public boolean isInterruptAllowed() {
        return interruptAllowed;
    }

    public void setInterruptAllowed(boolean interruptAllowed) {
        this.interruptAllowed = interruptAllowed;
    }

    public void actionPerformed(ActionEvent e) {
        if (task != null && !task.isDone()) {
            task.cancel(interruptAllowed);
        }
    }
}
