package ruffkat.swing.action;

import javax.swing.Action;

/**
 * Controls the behavior of a {@link BackgroundAction} during it's
 * lifecycle.
 *
 * @see Action#setEnabled(boolean)
 */
public enum ActionMode {

    /**
     * When an {@link Action} is prepared, it is disabled to prevent subsequent
     * invocation. If the {@link Action} is completed, it is disabled. If the
     * {@link Action} failed, it is re-enabled for another attempt.
     */
    LATCH() {
        public void prepared(Action action) {
            action.setEnabled(false);
        }

        public void completed(Action action) {
            action.setEnabled(false);
        }

        public void failed(Action action) {
            action.setEnabled(true);
        }
    },

    /**
     * When an {@link Action} is prepared, it is disabled to prevent subsequent
     * invocation. If the {@link Action} is completed, it is enabled. If the
     * {@link Action} failed, it is enabled.
     */
    MUTEX() {
        public void prepared(Action action) {
            action.setEnabled(false);
        }

        public void completed(Action action) {
            action.setEnabled(true);
        }

        public void failed(Action action) {
            action.setEnabled(true);
        }
    };

    /**
     * Controls the {@link Action#setEnabled(boolean) enabled} state of
     * the {@link Action} when it has been prepared
     *
     * @param action The {@link Action} being modulated
     */
    public abstract void prepared(Action action);

    /**
     * Controls the {@link Action#setEnabled(boolean) enabled} state of
     * the {@link Action} when it has completed
     *
     * @param action The {@link Action} being modulated
     */
    public abstract void completed(Action action);

    /**
     * Controls the {@link Action#setEnabled(boolean) enabled} state of
     * the {@link Action} when it has failed
     *
     * @param action The {@link Action} being modulated
     */
    public abstract void failed(Action action);
}
