package Model.Expression.BooleanExpression;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Exception.ExpressionEvaluationException;
import Model.Expression.Expression;

/**
 * Created by vladc on 21.11.2016.
 */
public abstract class BooleanExpression extends Expression {
    protected abstract boolean compare(MyIDictionary<String, Integer> symTab, MyIHeap heap)
            throws ExpressionEvaluationException;

    public int evaluate(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException {
        return this.compare(symTab, heap) ? 1 : 0;
    }
}
