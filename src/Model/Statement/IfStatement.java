package Model.Statement;

import DataTypes.*;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.*;

/**
 * Created by vladc on 12.10.2016.
 */
public class IfStatement implements IStatement {
    private Expression expr;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(Expression expr, IStatement thenStatement, IStatement elseStatement) {
        this.expr = expr;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "If (" + expr.toString() + ") Then\n\t(" +
                thenStatement.toString() + ")\nElse\n\t(" + elseStatement.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIStack<IStatement> st = state.getExeStack();
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyIHeap heap = state.getHeap();

        try {
            if (expr.evaluate(symTab, heap) != 0)
                st.push(thenStatement);
            else
                st.push(elseStatement);
        }
        catch (ExpressionEvaluationException ex) {
            ex.printStackTrace();
        }
        return state;
    }
}
