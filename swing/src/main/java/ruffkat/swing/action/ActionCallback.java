package ruffkat.swing.action;

/**
 * Defines an interface for classes that are interested in
 * the outcome of an {@link javax.swing.Action}
 *
 * @see javax.swing.Action
 */
public interface ActionCallback {

    /**
     * Notifies this {@link ActionCallback} that the action was completed
     */
    void completed();

    /**
     * Notifies this {@link ActionCallback} that the action was not
     * completed along with the reason why it was not completed
     *
     * @param cause The reason the action was not completed
     */
    void failed(Throwable cause);
}
