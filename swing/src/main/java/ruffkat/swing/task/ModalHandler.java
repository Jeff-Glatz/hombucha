package ruffkat.swing.task;

import ruffkat.swing.ui.ErrorHandler;

/**
 * A specialized {@link ErrorHandler} interface for controlling user
 * interface interaction
 */
public interface ModalHandler extends ErrorHandler {

    /**
     * Notifies this {@link ModalHandler} that user-application interaction
     * should be allowed
     */
    void allowInteraction();

    /**
     * Notifies this {@link ModalHandler} that user-application interaction
     * should be blocked
     */
    void blockInteraction();
}
