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

    public ProgramState execute(ProgramState state) {
        MyIList<Integer> out = state.getOut();
        MyIDictionary<String, Integer> symTab = state.getSymTable();

        try {
            out.enqueue(expr.evaluate(symTab));
        }
        catch (DivisionByZeroException e) {
            System.err.println("DivisionByZeroException caught in PrintStatement.execute().");
            System.err.println(e.toString());
        }
        catch (InvalidArithmeticOperatorException e) {
            System.err.println("InvalidArithmeticOperatorException caught in PrintStatement.execute().");
            System.err.println(e.toString());
        }
        catch (UndeclaredVariableException e) {
            System.err.println("UndeclaredVariableException caught in PrintStatement.execute().");
            System.err.println(e.toString());
        }
        return state;
    }
}
