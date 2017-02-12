package Model.Statement;

import DataTypes.*;
import Exception.*;
import Model.ProgramState;

/**
 * @author vladc
 */
public class AwaitStatement implements IStatement {
    private String var_name;

    public AwaitStatement(String var_name) {
        this.var_name = var_name;
    }

    public synchronized ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<String, Integer> symTab = state.getSymTable();
        MyILatchTable latchTable = state.getLatchTable();
        MyIStack<IStatement> exeStack = state.getExeStack();

        try {
            int foundIndex = symTab.get(var_name);
            if (!latchTable.indexExists(foundIndex)) {
                throw new MyStatementExecutionException("Index not found in LatchTable.");
            }
            if (latchTable.valueAt(foundIndex) == 0) {
                // do nothing
            }
            else {
                exeStack.push(this);
            }
        }
        catch (InvalidKeyMyDictionaryException e) {
            throw new MyStatementExecutionException(var_name + " not found in the symbol table.");
        }

        return null;
    }

    @Override
    public String toString() {
        return "await(" + var_name + ")";
    }
}
