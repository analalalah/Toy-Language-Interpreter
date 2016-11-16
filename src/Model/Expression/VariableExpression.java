package Model.Expression;

import DataTypes.MyIDictionary;
import Exception.ExpressionEvaluationException;
import Exception.UndeclaredVariableException;
import Exception.InvalidKeyMyDictionaryException;

/**
 * Created by vladc on 12.10.2016.
 */
public class VariableExpression extends Expression {
    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    public int evaluate(MyIDictionary<String, Integer> symTab) throws ExpressionEvaluationException {
        try {
            return symTab.get(id);
        }
        catch(InvalidKeyMyDictionaryException e) {
            throw new UndeclaredVariableException("Exception. Undeclared variable: " + id + ".");
        }
    }
}
