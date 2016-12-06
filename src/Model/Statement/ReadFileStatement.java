package Model.Statement;

import DataTypes.*;
import Model.Expression.Expression;
import Model.ProgramState;
import Exception.*;

import java.io.BufferedReader;
import java.io.IOException;
/**
 * Created by vladc on 07.11.2016.
 */
public class ReadFileStatement implements IStatement {
    private Expression  var_file_id;
    private String      var_name;

    public ReadFileStatement(Expression var_file_id, String var_name) {
        this.var_file_id = var_file_id;
        this.var_name = var_name;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<Integer, MyPair<String, BufferedReader>> fileTable = state.getFileTable();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();

        // 1. evaluate var_file_id to a value
        int value;
        try {
            value = var_file_id.evaluate(symTable, heap);
        }
        catch (ExpressionEvaluationException ex) {
            throw new MyStatementExecutionException("Cannot read from var " + var_file_id.toString() + ".");
        }

        // 2. using the previous step value we get the BufferedReader object associated in the FileTable.
        // If there is not any entry associated to this value in the FileTable we stop the execution with
        // an appropriate error message.
        BufferedReader br;
        try {
            br = fileTable.get(value).getSecond();
        }
        catch (InvalidKeyMyDictionaryException ex) {
            // todo: write an appropriate error message
            throw new MyStatementExecutionException("Error...");
        }

        // 3. Reads a line from the file using readLine method of the BufferedReader object. If line is null create a
        // zero int value. Otherwise translate the returned String into an int value (using Integer.parseInt(String)).
        int read;
        try {
            String line = br.readLine();
            read = line.equals(null) ? 0 : Integer.parseInt(line);
        }
        catch (IOException ex) {
            throw new MyStatementExecutionException("Error on reading from file.");
        }

        // 4. Add a new mapping (var_name, int value computed at the previous step) into the SymTable.
        // If var_name exists in SymTable update its associated value instead of adding a new mapping.
        try {
            symTable.replace(var_name, read);
        }
        catch (InvalidKeyMyDictionaryException ex) {
            symTable.put(var_name, read);
        }

        // 5. return
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + var_file_id.toString() + ", " + var_name + ")";
    }
}