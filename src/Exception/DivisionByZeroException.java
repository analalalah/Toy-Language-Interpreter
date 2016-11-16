package Exception;

/**
 * Created by vladc on 28.10.2016.
 */
public class DivisionByZeroException extends ExpressionEvaluationException {
    public DivisionByZeroException() {
        super();
    }
    public DivisionByZeroException(String message) {
        super(message);
    }
}
