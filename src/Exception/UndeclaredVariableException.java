package Exception;

/**
 * Created by vladc on 28.10.2016.
 */
public class UndeclaredVariableException extends ExpressionEvaluationException {
    public UndeclaredVariableException() {
        super();
    }
    public UndeclaredVariableException(String message) {
        super(message);
    }
}
