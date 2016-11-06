package Exception;

/**
 * Created by vladc on 28.10.2016.
 */
public class EmptyMyStackException extends Exception {
    public EmptyMyStackException() {
        super();
    }
    public EmptyMyStackException(String message) {
        super(message);
    }
}
