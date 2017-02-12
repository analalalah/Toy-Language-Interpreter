package Model.Statement;

import DataTypes.MyILatchTable;
import Exception.*;
import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState;

/**
 * @author vladc
 */
public class NewLatchStatement implements IStatement {
    private String var_name;
    private Expression expr;

    public NewLatchStatement(String var_name, Expression expr) {
        this.var_name = var_name;
        this.expr = expr;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<String, Integer> symTab;
        MyIHeap heap;
        MyILatchTable latchTable;

        int value;
        int address;
        try {
            synchronized (state) {
                symTab = state.getSymTable();
                heap = state.getHeap();
                latchTable = state.getLatchTable();

                value = this.expr.evaluate(symTab, heap);
                address = latchTable.alloca(value);
                symTab.put(var_name, address);
            }
        }
        catch (ExpressionEvaluationException e) {
            e.printStackTrace();
            throw new MyStatementExecutionException(expr.toString() + " evaluation failed.");
        }

        return null;
    }

    @Override
    public String toString() {
        return "newLatch(" + var_name + ", " + expr.toString() + ")";
    }
}
