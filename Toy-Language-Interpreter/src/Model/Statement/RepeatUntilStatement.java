package Model.Statement;

import DataTypes.MyIDictionary;
import DataTypes.MyIHeap;
import DataTypes.MyIStack;
import Exception.*;
import Model.Expression.Expression;
import Model.Expression.NotExpression;
import Model.ProgramState;

/**
 * @author vladc
 */
public class RepeatUntilStatement implements IStatement {
    private IStatement statement1;
    private Expression expression2;

    public RepeatUntilStatement(IStatement statement1, Expression expression2) {
        this.statement1 = statement1;
        this.expression2 = expression2;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIStack<IStatement> exeStack = state.getExeStack();


        Expression notE2 = new NotExpression(expression2);

        IStatement statement = new CompoundStatement(statement1,
                new WhileStatement(notE2, statement1
                ));

        exeStack.push(statement);

        return null;
    }

    @Override
    public String toString() {
        return "Repeat(" + statement1.toString() + ")..until(" + expression2.toString() + ")";
    }
}
