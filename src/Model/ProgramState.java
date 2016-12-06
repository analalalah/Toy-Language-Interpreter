package Model;

import DataTypes.*;
import Model.Statement.*;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by vladc on 22.10.2016.
 */
public class ProgramState implements Serializable {
    private int                                                     id;
    private MyIStack<IStatement>                                    exeStack;
    private MyIDictionary<String, Integer>                          symTable;
    private MyIList<Integer>                                        out;
    private MyIDictionary<Integer, MyPair<String, BufferedReader>>  fileTable;
    private MyIHeap                                                 heap;
    private IStatement                                              originalProgram; // optional field (din cerinta)

    public ProgramState(int                                                     id,
                        MyIStack<IStatement>                                    exeStack,
                        MyIDictionary<String, Integer>                          symTable,
                        MyIList<Integer>                                        out,
                        MyIDictionary<Integer, MyPair<String, BufferedReader>>  fileTable,
                        MyIHeap                                                 heap,
                        IStatement                                              originalProgram) {
        this.id                 = id;
        this.exeStack           = exeStack;
        this.symTable           = symTable;
        this.out                = out;
        this.fileTable          = fileTable;
        this.heap               = heap;
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

    public MyIHeap getHeap() {
        return this.heap;
    }

    public int  getId() {
        return this.id;
    }

    /*  6. You must add one more method to the class PrgState: Boolean isNotCompleted() that
        returns true when the exeStack is not empty and false otherwise
     */
    public boolean isNotCompleted() {
        return (!this.exeStack.isEmpty());
    }

    public ProgramState oneStep() {
        try {
            IStatement currentStatement = this.exeStack.pop();
            return currentStatement.execute(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return  "PID: "           + id +
                "\nExeStack:\n"   + exeStack.toString() +
                "\nSymTable:\n"   + symTable.toString() +
                "\nOut:\n"        + out.toString() +
                "\nFileTable:\n"  + fileTable.toString() +
                "\nHeap:\n"       + heap.toString() +
                "--------------------------------------------------";
    }
}
