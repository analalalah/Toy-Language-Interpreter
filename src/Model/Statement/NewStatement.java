package Model.Statement;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.*;
/**
 * Created by vladc on 19.11.2016.
 */
public class NewStatement implements IStatement {
    private String      var_name;
    private Expression  expr;

    public NewStatement(String var_name, Expression expr) {
        this.var_name = var_name;
        this.expr     = expr;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyIHeap heap = state.getHeap();

        int value;
        int address;
        try {
            value = this.expr.evaluate(symTab, heap);
            address = heap.alloca(value);
            symTab.put(var_name, address);
        }
        catch (ExpressionEvaluationException e) {
            e.printStackTrace();
            throw new MyStatementExecutionException(expr.toString() + " evaluation failed.");
        }

        return null;
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + expr.toString() + ")";
    }
}
