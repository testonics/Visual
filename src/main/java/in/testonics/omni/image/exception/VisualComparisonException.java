package in.testonics.omni.image.exception;

/**
 * A {@link RuntimeException} that is thrown in case of an image comparison failures.
 */
public final class VisualComparisonException extends RuntimeException {

    /**
     * Constructs a new {@link VisualComparisonException} with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to
     * Throwable.initCause(java.lang.Throwable).
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     *        method.
     */
    public VisualComparisonException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link VisualComparisonException} with the specified detail message and cause.
     * Note that the detail message associated with cause is not automatically incorporated in this {@link
     * VisualComparisonException}'s detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param throwable the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *        (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public VisualComparisonException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
