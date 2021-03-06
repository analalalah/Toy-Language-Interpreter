package Model.Expression;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Exception.ExpressionEvaluationException;

/**
 * Created by vladc on 12.10.2016.
 */
public class ConstantExpression extends Expression {
    private int number;

    public ConstantExpression(int number) {
        this.number = number;
    }

    public int evaluate(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException {
        return number;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
