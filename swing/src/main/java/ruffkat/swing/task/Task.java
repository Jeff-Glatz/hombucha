package ruffkat.swing.task;

import javax.swing.SwingWorker;
import java.beans.PropertyChangeListener;
import java.util.concurrent.RunnableFuture;

/**
 * A specialized {@link RunnableFuture} that provides an interface for
 * activities performed in coordination with the swing event dispatch
 * thread
 *
 * @param <V> The value of {@link RunnableFuture#get()} and
 *            {@link RunnableFuture#get(long, java.util.concurrent.TimeUnit)}
 * @see SwingWorker
 */
public interface Task<V> extends RunnableFuture<V> {

    /**
     * Adds a {@code PropertyChangeListener} to the listener list. The listener
     * is registered for all properties. The same listener object may be added
     * more than once, and will be called as many times as it is added. If
     * {@code listener} is {@code null}, no exception is thrown and no action is taken.
     *
     * @param listener the {@code PropertyChangeListener} to be added
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Removes a {@code PropertyChangeListener} from the listener list. This
     * removes a {@code PropertyChangeListener} that was registered for all
     * properties. If {@code listener} was added more than once to the same
     * event source, it will be notified one less time after being removed. If
     * {@code listener} is {@code null}, or was never added, no exception is
     * thrown and no action is taken.
     *
     * @param listener the {@code PropertyChangeListener} to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Returns a description of this {@link Task}
     *
     * @return A a description of this {@link Task}
     */
    String getDescription();

    /**
     * Returns the current {@link SwingWorker.StateValue state}
     * of this {@link Task}
     *
     * @return he current {@link SwingWorker.StateValue state}
     *         of this {@link Task}
     * @see SwingWorker
     */
    SwingWorker.StateValue getState();

    /**
     * Returns the {@code progress} bound property.
     *
     * @return the progress bound property.
     */
    int getProgress();

    /**
     * Schedules this {@link Task} for execution on a <i>worker</i> thread.
     * There are a number of <i>worker</i> threads available. In the event
     * all <i>worker</i> threads are busy handling other {@link Task} this
     * {@link Task} is placed in a waiting queue.
     * <p/>
     * <p/>
     * Note:
     * {@link Task} is only designed to be executed once.  Executing a
     * {@link Task} more than once will result in only one invocation
     * of background activities.
     */
    void execute();
}
