package Model.Expression;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Model.ProgramState;
import Exception.ExpressionEvaluationException;
import Exception.InvalidKeyMyDictionaryException;

/**
 * Created by vladc on 19.11.2016.
 */
public class ReadHeap extends Expression {
    private String var_name;

    public ReadHeap(String var_name) {
        this.var_name = var_name;
    }

    public int evaluate(MyIDictionary<String, Integer> symTab, MyIHeap heap) throws ExpressionEvaluationException {
        int address;
        int value;

        try {
            address = symTab.get(var_name);
            value = heap.valueAt(address);
        }
        catch (InvalidKeyMyDictionaryException e) {
            throw new ExpressionEvaluationException("Invalid key: " + var_name + ".");
        }
        return value;
    }

    @Override
    public String toString() {
        return "rH(" + var_name + ")";
    }
}
