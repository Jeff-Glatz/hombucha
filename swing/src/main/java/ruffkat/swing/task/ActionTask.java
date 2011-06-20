package ruffkat.swing.task;

import ruffkat.swing.ui.ErrorHandler;

import javax.swing.Action;

/**
 * A {@link BackgroundTask} that enables a supplied {@link Action}
 * before leaving the EDT
 *
 * @param <V> The value of {@link java.util.concurrent.RunnableFuture#get()} and
 *            {@link java.util.concurrent.RunnableFuture#get(long, java.util.concurrent.TimeUnit)}
 * @param <I> the type used for carrying out intermediate results by this
 *            {@code SwingWorker's} {@code publish} and {@code process} methods
 */
public abstract class ActionTask<V, I> extends BackgroundTask<V, I> {
    private final Action action;

    public ActionTask(String description, ErrorHandler errorHandler, Action action) {
        super(description, errorHandler);
        this.action = action;
    }

    protected void beforeLeavingEDT() {
        if (!action.isEnabled()) {
            action.setEnabled(true);
        }
    }
}
