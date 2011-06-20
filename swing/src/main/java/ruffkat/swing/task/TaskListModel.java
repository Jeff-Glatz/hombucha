package ruffkat.swing.task;

import javax.swing.DefaultComboBoxModel;

/**
 * The {@link TaskListModel} is a specialized {@link DefaultComboBoxModel}
 * that contains a collection of {@link Task} instances.
 *
 * When a {@link Task} is registered, a {@link StateChangeHandler} is
 * registered with the task so that when the task transitions to the
 * {@code started} state, it will be added to the data model. Likewise,
 * when the task transitions to the {@code done} state, it will be removed
 * from the model.
 *
 * @see StateChangeHandler
 * @see TaskRenderer
 * @see TaskEditor
 */
public class TaskListModel extends DefaultComboBoxModel
        implements TaskRepository {
    private final StateChangeHandler listener;

    public TaskListModel() {
        listener = new StateChangeHandler() {
            protected void onTaskStarted(Task task) {
                addElement(task);
            }

            protected void onTaskDone(Task task) {
                removeElement(task);
            }
        };
    }

    public <V> Task<V> register(Task<V> task) {
        task.addPropertyChangeListener(listener);
        return task;
    }
}
