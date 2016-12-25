package Model.Expression.BooleanExpression;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Exception.ExpressionEvaluationException;
import Model.Expression.Expression;

/**
 * Created by vladc on 21.11.2016.
 */
public class LessOrEqualExpression extends BooleanExpression {
    private Expression expr1;
    private Expression expr2;

    public LessOrEqualExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    protected boolean compare(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException {
        return (expr1.evaluate(symTab, heap) - expr2.evaluate(symTab, heap)) <= 0;
    }

    @Override
    public String toString() {
        return expr1.toString() + " <= " + expr2.toString();
    }
}
