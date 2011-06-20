package ruffkat.swing.task;

/**
 * A {@link ModalTask} is a specialized {@link BackgroundTask} that
 * uses a supplied {@link ModalHandler} to control user-application
 * interaction while the task is executing in the background
 *
 * @param <V> The value of {@link java.util.concurrent.RunnableFuture#get()} and
 *            {@link java.util.concurrent.RunnableFuture#get(long, java.util.concurrent.TimeUnit)}
 * @param <I> the type used for carrying out intermediate results by this
 *            {@code SwingWorker's} {@code publish} and {@code process} methods
 * @see ModalHandler
 */
public abstract class ModalTask<V, I> extends BackgroundTask<V, I> {
    protected final ModalHandler handler;

    public ModalTask(String description, ModalHandler handler) {
        super(description, handler);
        this.handler = handler;
    }

    protected final V doInBackground()
            throws Exception {
        handler.blockInteraction();
        try {
            return doWithBlockedInteraction();
        } finally {
            handler.allowInteraction();
        }
    }

    protected abstract V doWithBlockedInteraction()
            throws Exception;
}
