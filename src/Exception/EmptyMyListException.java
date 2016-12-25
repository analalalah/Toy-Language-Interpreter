package Exception;

/**
 * Created by vladc on 28.10.2016.
 */
public class EmptyMyListException extends Exception {
    public EmptyMyListException() {
        super();
    }
    public EmptyMyListException(String message) {
        super(message);
    }
}
