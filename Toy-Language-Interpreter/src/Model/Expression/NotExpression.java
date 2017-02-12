package Model.Expression;

import Exception.ExpressionEvaluationException;
import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;

/**
 * @author vladc
 */
public class NotExpression extends Expression {
    private Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "!(" + expression.toString() + ")";
    }

    public int evaluate(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException {
        if (expression.evaluate(symTab, heap) != 0) {
            return 0;
        }
        return 1;
    }
}
