package augustus.exception;

/**
 * Represents errors that occur while Augustus processes commands or tasks.
 */
public class AugustusException extends Exception {
    /**
     * Creates an AugustusException with the specified error message.
     *
     * @param message description of the error
     */
    public AugustusException(String message) {
        super(message);
    }
}
