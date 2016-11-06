package Model.Statement;

import DataTypes.MyIDictionary;
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

    public ProgramState execute(ProgramState state) {
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        try {
            int value = expr.evaluate(symTab);
            symTab.put(id, value);
        }
        catch (DivisionByZeroException e) {
            System.err.println("DivisionByZeroException caught in AssignmentStatement.execute().");
            System.err.println(e.toString());
        }
        catch (InvalidArithmeticOperatorException e) {
            System.err.println("InvalidArithmeticOperatorException caught in AssignmentStatement.execute().");
            System.err.println(e.toString());
        }
        catch (UndeclaredVariableException e) {
            System.err.println("UndeclaredVariableException caught in AssignmentStatement.execute().");
            System.err.println(e.toString());
        }
        return state;
    }
}
