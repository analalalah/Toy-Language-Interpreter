package Model;

import DataTypes.*;
import Model.Statement.*;

/**
 * Created by vladc on 22.10.2016.
 */
public class ProgramState {
    private MyIStack<IStatement>            exeStack;
    private MyIDictionary<String, Integer>  symTable;
    private MyIList<Integer>                out;
    private IStatement                      originalProgram; // optional field (din cerinta)

    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Integer> symTable,
                        MyIList<Integer> out, IStatement originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
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

    @Override
    public String toString() {
        return "ProgramState:\n" +
                "\texeStack = " + exeStack.toString() + "\n" +
                "\tsymTable = " + symTable.toString() + "\n" +
                "\toutList = " + out.toString() + "\n";
    }
}
