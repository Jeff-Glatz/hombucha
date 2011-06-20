package ruffkat.swing.action;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A base action to be used for potentially long running operations. Designed to
 * work via a callback from a SwingWorker. The {@link BackgroundAction} can be
 * constructed in a specified {@link ActionMode} which controls the
 * {@link javax.swing.Action#setEnabled(boolean) enabled} state
 *
 * @see ActionMode
 */
public abstract class BackgroundAction extends AbstractAction implements TwoPhaseAction {
    private final List<ActionCallback> callbacks = new ArrayList<ActionCallback>();
    private final ActionMode mode;

    /**
     * Constructs a new {@link BackgroundAction}
     * in {@link ActionMode#MUTEX} mode
     */
    public BackgroundAction() {
        this(ActionMode.MUTEX);
    }

    /**
     * Constructs a new {@link BackgroundAction} in the
     * specified {@link ActionMode mode}
     *
     * @param mode The {@link ActionMode mode} of this {@link BackgroundAction}
     */
    public BackgroundAction(ActionMode mode) {
        this.mode = mode;
    }

    public void addCallback(ActionCallback callback) {
        callbacks.add(callback);
    }

    public void removeCallback(ActionCallback callback) {
        callbacks.remove(callback);
    }

    public final void actionPerformed(ActionEvent event) {
        actionPrepared();
        actionInvoked(event);
    }

    public final void completed() {
        for (ActionCallback callback : callbacks) {
            callback.completed();
        }
        actionCompleted();
    }

    public final void failed(Throwable cause) {
        for (ActionCallback callback : callbacks) {
            callback.failed(cause);
        }
        actionFailed();
    }

    protected void actionPrepared() {
        mode.prepared(this);
    }

    protected abstract void actionInvoked(ActionEvent event);

    protected void actionCompleted() {
        mode.completed(this);
    }

    protected void actionFailed() {
        mode.failed(this);
    }
}
