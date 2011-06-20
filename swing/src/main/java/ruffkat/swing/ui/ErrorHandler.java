package ruffkat.swing.ui;

/**
 * Interface for classes that provide error handling
 */
public interface ErrorHandler {

    /**
     * Instructs this {@link ErrorHandler} that an
     * {@link Throwable error} has occurred
     *
     * @param message An application message for the error
     * @param error   The {@link Throwable error} that occurred
     */
    void onError(String message, Throwable error);
}
