package Model.Expression;

import DataTypes.MyIDictionary;
import Exception.DivisionByZeroException;
import Exception.InvalidArithmeticOperatorException;
import Exception.UndeclaredVariableException;

/**
 * Created by vladc on 12.10.2016.
 */
public class ArithmeticExpression extends Expression {
    private Expression expr1;
    private Expression expr2;
    private char       operator;

    public ArithmeticExpression(Expression expr1, Expression expr2, char operation) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operation;
    }

    public int evaluate(MyIDictionary<String, Integer> symTab)
            throws DivisionByZeroException, InvalidArithmeticOperatorException, UndeclaredVariableException {
        if (operator == '+')
            return (expr1.evaluate(symTab) + expr2.evaluate(symTab));
        else if (operator == '-')
            return (expr1.evaluate(symTab) - expr2.evaluate(symTab));
        else if (operator == '*')
            return (expr1.evaluate(symTab) * expr2.evaluate(symTab));
        else if (operator == '/') {
            if (expr2.evaluate(symTab) == 0)
                throw new DivisionByZeroException();
            return (expr1.evaluate(symTab) / expr2.evaluate(symTab));
        }
        throw new InvalidArithmeticOperatorException();
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + operator + " " + expr2.toString();
    }
}
