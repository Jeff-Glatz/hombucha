package ruffkat.swing.task;

import javax.swing.SwingWorker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A custom {@link PropertyChangeListener} that invokes
 * template methods as a {@link Task} transitions between
 * states during it's lifecycle.
 *
 * @see SwingWorker.StateValue
 */
public class StateChangeHandler implements PropertyChangeListener {
    private static final String STATE = "state";
    private static final String PROGRESS = "progress";

    public final void propertyChange(PropertyChangeEvent event) {
        if (STATE.equals(event.getPropertyName())) {
            Task<?> task = (Task<?>) event.getSource();
            if (SwingWorker.StateValue.PENDING == event.getNewValue()) {
                onTaskPending(task);
            } else if (SwingWorker.StateValue.STARTED == event.getNewValue()) {
                onTaskStarted(task);
            } else if (SwingWorker.StateValue.DONE == event.getNewValue()) {
                task.removePropertyChangeListener(this);
                onTaskDone(task);
            }
        } else if (PROGRESS.equals(event.getPropertyName())) {
            Task<?> task = (Task<?>) event.getSource();
            onTaskProgress(task);
        }
    }

    /**
     * Invoked when the specified {@link Task} transitions to the
     * {@link SwingWorker.StateValue#PENDING} state
     *
     * @param task The {@link Task} whose state has changed
     */
    protected void onTaskPending(Task<?> task) {
    }

    /**
     * Invoked when the specified {@link Task} transitions to the
     * {@link SwingWorker.StateValue#STARTED} state
     *
     * @param task The {@link Task} whose state has changed
     */
    protected void onTaskStarted(Task<?> task) {
    }

    /**
     * Invoked when the specified {@link Task} has made progress
     * while in the {@link SwingWorker.StateValue#STARTED} state
     *
     * @param task The {@link Task} whose state has changed
     */
    protected void onTaskProgress(Task<?> task) {
    }

    /**
     * Invoked when the specified {@link Task} transitions to the
     * {@link SwingWorker.StateValue#DONE} state
     *
     * @param task The {@link Task} whose state has changed
     */
    protected void onTaskDone(Task<?> task) {
    }
}
