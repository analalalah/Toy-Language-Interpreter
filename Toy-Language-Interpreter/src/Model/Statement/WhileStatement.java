package Model.Statement;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import DataTypes.MyIStack;
import Exception.ExpressionEvaluationException;
import Exception.MyStatementExecutionException;
import Model.Expression.Expression;
import Model.ProgramState;

/**
 * Created by vladc on 21.11.2016.
 */
public class WhileStatement implements IStatement {
    private Expression expression;
    private IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIStack<IStatement> exeStack = state.getExeStack();
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyIHeap heap = state.getHeap();

        try {
            if (expression.evaluate(symTab, heap) != 0) {
                exeStack.push(this);
                exeStack.push(statement);
            }
        }
        catch (ExpressionEvaluationException e) {
            throw new MyStatementExecutionException("While(" + expression.toString() + ") failed to evaluate.");
        }

        return null;
    }

    @Override
    public String toString() {
        return "while (" + expression.toString() + ") do\n\t" + statement.toString() + "\nend_while";
    }
}
