package Model;

import DataTypes.*;
import Model.Statement.*;

import java.io.BufferedReader;

/**
 * Created by vladc on 22.10.2016.
 */
public class ProgramState {
    private MyIStack<IStatement>                                    exeStack;
    private MyIDictionary<String, Integer>                          symTable;
    private MyIList<Integer>                                        out;
    private MyIDictionary<Integer, MyPair<String, BufferedReader>>  fileTable;
    private IStatement                                              originalProgram; // optional field (din cerinta)

    public ProgramState(MyIStack<IStatement>                                    exeStack,
                        MyIDictionary<String, Integer>                          symTable,
                        MyIList<Integer>                                        out,
                        MyIDictionary<Integer, MyPair<String, BufferedReader>>  fileTable,
                        IStatement                                              originalProgram) {
        this.exeStack           = exeStack;
        this.symTable           = symTable;
        this.out                = out;
        this.fileTable          = fileTable;
        this.originalProgram    = originalProgram;
        this.exeStack.push(originalProgram);
    }

    public MyIStack<IStatement> getExeStack() {
        return this.exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return this.symTable;
    }

    public MyIList<Integer> getOut() {
        return this.out;
    }

    public MyIDictionary<Integer, MyPair<String, BufferedReader>> getFileTable() {
        return this.fileTable;
    }

    @Override
    public String toString() {
        return "ExeStack:\n"    + exeStack.toString() +
                "\nSymTable:\n"   + symTable.toString() +
                "\nOut:\n"        + out.toString() +
                "\nFileTable:\n"  + fileTable.toString();
    }
}
