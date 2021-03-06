package Model.Statement;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.*;

/**
 * Created by vladc on 12.10.2016.
 */
public class AssignmentStatement implements IStatement {
    private String      id;
    private Expression  expr;

    public AssignmentStatement(String id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return id + " = " + expr.toString();
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyIHeap heap = state.getHeap();

        try {
            int value = expr.evaluate(symTab, heap);
            symTab.put(id, value);
        }
        catch (ExpressionEvaluationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
