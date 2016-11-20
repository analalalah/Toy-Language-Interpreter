package Model.Expression;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Exception.ExpressionEvaluationException;
/**
 * Created by vladc on 12.10.2016.
 */
public abstract class Expression {
    public abstract int evaluate(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException;
    public abstract String toString();
}
