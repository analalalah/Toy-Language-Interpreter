package Model.Statement;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.*;

/**
 * Created by vladc on 19.11.2016.
 */
public class WriteHeap implements IStatement {
    private String      var_name;
    private Expression  expr;

    public WriteHeap(String var_name, Expression expr) {
        this.var_name = var_name;
        this.expr = expr;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyIHeap heap = state.getHeap();
        int address;
        int value;

        try {
            address = symTab.get(var_name);
            value = expr.evaluate(symTab, heap);
            heap.valueAt(address, value);
        }
        catch (InvalidKeyMyDictionaryException e) {
            throw new MyStatementExecutionException(var_name + " not found in the symbol table.");
        }
        catch (ExpressionEvaluationException e) {
            throw new MyStatementExecutionException(expr + " evaluation failed.");
        }

        return null;
    }

    @Override
    public String toString() {
        return "wH(" + var_name + ", " + expr.toString() + ")";
    }
}
