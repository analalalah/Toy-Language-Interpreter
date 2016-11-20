package Model.Statement;

import DataTypes.*;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.MyStatementExecutionException;
import Exception.ExpressionEvaluationException;
import Exception.InvalidKeyMyDictionaryException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by vladc on 07.11.2016.
 */
public class CloseRFileStatement implements IStatement {
    private Expression var_file_id;

    public CloseRFileStatement(Expression var_file_id) {
        this.var_file_id = var_file_id;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<Integer, MyPair<String, BufferedReader>> fileTable = state.getFileTable();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();

        // 1. evaluate var_file_id to a value. If any error occurs then terminate the execution.
        int value;
        try {
            value = var_file_id.evaluate(symTable, heap);
        }
        catch (ExpressionEvaluationException ex) {
            throw new MyStatementExecutionException("The file is not opened for reading.");
        }

        // 2. Use the value to get the entry into the FileTable and get the associated BufferedReader
        // object. If there is not any entry in FileTable for the value we stop the execution.
        BufferedReader br;
        try {
            br = fileTable.get(value).getSecond();
        }
        catch (InvalidKeyMyDictionaryException ex) {
            // todo: choose an appropriate error message
            throw new MyStatementExecutionException("Error...");
        }

        // 3. call the close method of the BufferedReader object
        try {
            br.close();
        }
        catch (IOException ex) {
            throw new MyStatementExecutionException("IOException: " + ex.toString());
        }

        // 4. delete the entry from the FileTable
        try {
            fileTable.remove(value);
        }
        catch (InvalidKeyMyDictionaryException ex) {
            throw new MyStatementExecutionException("There is no entry (" + value + ") in the file table.");
        }

        // 5. return
        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + var_file_id.toString() + ")";
    }
}