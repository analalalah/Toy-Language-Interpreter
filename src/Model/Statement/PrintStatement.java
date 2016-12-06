package Model.Statement;

import DataTypes.*;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.*;
/**
 * Created by vladc on 12.10.2016.
 */
public class PrintStatement implements IStatement {
    private Expression expr;

    public PrintStatement(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "Print(" + expr.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIList<Integer> out = state.getOut();
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyIHeap heap = state.getHeap();

        try {
            out.enqueue(expr.evaluate(symTab, heap));
        }
        catch (ExpressionEvaluationException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
