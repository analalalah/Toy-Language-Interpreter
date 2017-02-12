package Model.Statement;

import Exception.*;
import DataTypes.*;
import Model.ProgramState;

/**
 * @author vladc
 */
public class CountDownStatement implements IStatement {
    private String var_name;

    public CountDownStatement(String var_name) {
        this.var_name = var_name;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<String, Integer> symTab;
        MyILatchTable latchTable;
        MyIList<Integer> out;

        try {
            synchronized (state) {
                symTab = state.getSymTable();
                latchTable = state.getLatchTable();
                out = state.getOut();

                int foundIndex = symTab.get(var_name);
                if (!latchTable.indexExists(foundIndex)) {
                    // do nothing
                }
                if (latchTable.valueAt(foundIndex) > 0) {
                    int value = latchTable.valueAt(foundIndex);

                    latchTable.valueAt(foundIndex, value - 1);

                    out.enqueue(state.getId());
                } else {
                    // do nothing
                }
            }
        }
        catch (InvalidKeyMyDictionaryException e) {
            throw new MyStatementExecutionException(var_name + " not found in the symbol table.");
        }

        return null;
    }

    @Override
    public String toString() {
        return "countDown(" + var_name + ")";
    }
}
