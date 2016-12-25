package Exception;

/**
 * Created by vladc on 28.10.2016.
 */
public class InvalidArithmeticOperatorException extends ExpressionEvaluationException {
    public InvalidArithmeticOperatorException() {
        super();
    }
    public InvalidArithmeticOperatorException(String message) {
        super(message);
    }
}
