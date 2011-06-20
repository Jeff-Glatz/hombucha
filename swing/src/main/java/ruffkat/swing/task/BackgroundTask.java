package ruffkat.swing.task;

import ruffkat.swing.action.ActionCallback;
import ruffkat.swing.ui.ErrorHandler;

import javax.swing.SwingWorker;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * The {@link BackgroundTask} is a specialized {@link SwingWorker} that uses a supplied
 * {@link ErrorHandler} and provides transaction support via an {@link ActionCallback}
 *
 * @param <V> The value of {@link java.util.concurrent.RunnableFuture#get()} and
 *            {@link java.util.concurrent.RunnableFuture#get(long, java.util.concurrent.TimeUnit)}
 * @param <I> the type used for carrying out intermediate results by this
 *            {@code SwingWorker's} {@code publish} and {@code process} methods
 * @see ErrorHandler
 * @see ActionCallback
 */
public abstract class BackgroundTask<V, I>
        extends SwingWorker<V, I>
        implements Task<V> {
    private final String description;

    private ErrorHandler errorHandler;
    private ActionCallback actionCallback;

    public BackgroundTask(String description) {
        this(description, null, null);
    }

    public BackgroundTask(String description, ActionCallback actionCallback) {
        this(description, null, actionCallback);
    }

    public BackgroundTask(String description, ErrorHandler errorHandler) {
        this(description, errorHandler, null);
    }

    public BackgroundTask(String description, ErrorHandler errorHandler, ActionCallback actionCallback) {
        this.description = description;
        this.errorHandler = errorHandler;
        this.actionCallback = actionCallback;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the {@link ErrorHandler} handling exceptional situations
     *
     * @return The {@link ErrorHandler} handling exceptional situations
     */
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    /**
     * Sets the {@link ErrorHandler} handling exceptional situations
     *
     * @param errorHandler The {@link ErrorHandler} handling exceptional situations;
     *                     {@code null} allowed
     */
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * Returns the {@link ActionCallback} being notified of results
     *
     * @return The {@link ActionCallback} being notified of results
     */
    public ActionCallback getActionCallback() {
        return actionCallback;
    }

    /**
     * Sets the {@link ActionCallback} being notified of results
     *
     * @param actionCallback The {@link ActionCallback} being notified of results;
     *                       {@code null} allowed
     */
    public void setActionCallback(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    /**
     * Provides simple transactional behavior around result processing/handling
     * gracefully handling {@link CancellationException cancellations},
     * {@link InterruptedException interruptions}, and any other
     * {@link ExecutionException execution exception}
     */
    protected final void done() {
        try {
            try {
                completed(get());
                if (actionCallback != null) {
                    actionCallback.completed();
                }
            } catch (CancellationException e) {
                failed(e);
                if (actionCallback != null) {
                    actionCallback.failed(e);
                }
            } catch (InterruptedException e) {
                failed(e);
                if (actionCallback != null) {
                    actionCallback.failed(e);
                }
                if (errorHandler != null) {
                    errorHandler.onError(description + " timed out", e);
                }
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                failed(cause);
                if (actionCallback != null) {
                    actionCallback.failed(cause);
                }
                if (errorHandler != null) {
                    errorHandler.onError(description + " aborted", cause);
                }
            } finally {
                beforeLeavingEDT();
            }
        } catch (Throwable failure) {
            if (errorHandler != null) {
                errorHandler.onError(description + " raised an unhandled exception", failure);
            }
        }
    }

    /**
     * Template method invoked from {@link #done()} to allow for result
     * processing on the EDT
     *
     * @param result The result of this {@link BackgroundTask}
     */
    protected void completed(V result) {
    }

    /**
     * Template method invoked from {@link #done()} to allow for error
     * processing on the EDT
     *
     * @param error The cause of the failure of this {@link BackgroundTask}
     */
    protected void failed(Throwable error) {
    }

    /**
     * Template method invoked from {@link #done()} to allow for additional
     * processing on the EDT after all processing has been done; always
     * invoked before exiting the EDT
     */
    protected void beforeLeavingEDT() {
    }
}
