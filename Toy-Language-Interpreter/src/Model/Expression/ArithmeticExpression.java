package Model.Expression;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Exception.ExpressionEvaluationException;
import Exception.DivisionByZeroException;
import Exception.InvalidArithmeticOperatorException;

/**
 * Created by vladc on 12.10.2016.
 */
public class ArithmeticExpression extends Expression {
    private Expression expr1;
    private Expression expr2;
    private char       operator;

    public ArithmeticExpression(Expression expr1, Expression expr2, char operator) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    public int evaluate(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException {
        if (operator == '+')
            return (expr1.evaluate(symTab, heap) + expr2.evaluate(symTab, heap));
        else if (operator == '-')
            return (expr1.evaluate(symTab, heap) - expr2.evaluate(symTab, heap));
        else if (operator == '*')
            return (expr1.evaluate(symTab, heap) * expr2.evaluate(symTab, heap));
        else if (operator == '/') {
            if (expr2.evaluate(symTab, heap) == 0)
                throw new DivisionByZeroException();
            return (expr1.evaluate(symTab, heap) / expr2.evaluate(symTab, heap));
        }
        throw new InvalidArithmeticOperatorException();
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + operator + " " + expr2.toString();
    }
}
