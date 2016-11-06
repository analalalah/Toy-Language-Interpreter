package Model.Expression;

import DataTypes.MyIDictionary;
import Exception.DivisionByZeroException;
import Exception.InvalidArithmeticOperatorException;
import Exception.UndeclaredVariableException;

/**
 * Created by vladc on 12.10.2016.
 */
public abstract class Expression {
    public abstract int evaluate(MyIDictionary<String, Integer> symTab)
            throws DivisionByZeroException, InvalidArithmeticOperatorException, UndeclaredVariableException;
    public abstract String toString();
}
