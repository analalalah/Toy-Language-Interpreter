package Model.Statement;

import DataTypes.MyIDictionary;
import DataTypes.MyIList;
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

        try {
            out.enqueue(expr.evaluate(symTab));
        }
        catch (ExpressionEvaluationException ex) {
            System.err.println(ex.toString());
            // TODO: 09.11.2016 maybe something else...
        }
        return state;
    }
}
