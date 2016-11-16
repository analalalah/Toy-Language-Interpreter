package Model.Statement;

import DataTypes.*;
import Model.ProgramState;
import Exception.MyStatementExecutionException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by vladc on 07.11.2016.
 */
public class OpenRFileStatement implements IStatement {
    private String var_file_id;
    private String filename;
    private static int fd = 2;

    public OpenRFileStatement(String var_file_id, String filename) {
        this.var_file_id = var_file_id;
        this.filename = filename;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        MyIDictionary<Integer, MyPair<String, BufferedReader>> fileTable = state.getFileTable();
        MyIDictionary<String, Integer> symTable = state.getSymTable();

        // 1. check whether the filename is not already in the FileTable.
        // If it exists stop the execution with an appropriate error message.
        for (MyPair<String, BufferedReader> pair : fileTable.values()) {
            if (pair.getFirst().equals(filename)) {
                throw new MyStatementExecutionException("File " + filename + " already open.");
            }
        }

        // 2. open the file filename in Java using an instance of the BufferedReader class. If the file does
        // not exist or other IO error occurs stopped the execution with an appropriate error message
        BufferedReader br;
        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException ex) {
            throw new MyStatementExecutionException("File " + filename + " does not exist.");
        }

        // 3. create a new entrance into the FileTable which maps a new unique integer key to the
        // (filename, instance of the BufferedReader class created before).
        fileTable.put(++fd, new MyPair<>(filename, br));

        // 4. set the var_file_id to that new unique integer key (created at the previous step) into the SymTable.
        symTable.put(var_file_id, fd);

        // 5. return
        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + var_file_id + ", \"" + filename + "\")";
    }
}