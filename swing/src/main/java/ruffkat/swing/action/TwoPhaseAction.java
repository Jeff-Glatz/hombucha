package ruffkat.swing.action;

import javax.swing.Action;

/**
 * Extends {@link Action} to expose a <b>simple</b> transactional api
 *
 * @see Action
 * @see ActionCallback
 */
public interface TwoPhaseAction extends Action, ActionCallback {

    /**
     * Adds the specified {@link ActionCallback} to the list of
     * callbacks to be notified of the outcome of this {@link Action}
     *
     * @param callback The {@link ActionCallback} to be notified of
     *                 the outcome of this {@link Action}
     */
    void addCallback(ActionCallback callback);

    /**
     * Removes the specified {@link ActionCallback} from the list of
     * callbacks to be notified of the outcome of this {@link Action}
     *
     * @param callback The {@link ActionCallback} no longer notified of
     *                 the outcome of this {@link Action}
     */
    void removeCallback(ActionCallback callback);
}
