package Model.Statement;

import DataTypes.*;
import Exception.MyStatementExecutionException;
import Model.ProgramState;

import java.io.BufferedReader;

/**
 * Created by vladc on 03.12.2016.
 */
public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws MyStatementExecutionException {
        int id = state.getId() * 10;

        MyIStack<IStatement> exeStack = new MyStack<>();
//        exeStack.push(this.statement); // the statement is pushed on the stack by the constructor of ProgramState

        MyIDictionary<String, Integer> symTable = new MyDictionary<>();
        symTable.clone(state.getSymTable());

        MyIList<Integer> out = state.getOut();

        MyIDictionary<Integer, MyPair<String, BufferedReader>> fileTable = state.getFileTable();

        MyIHeap heap = state.getHeap();

        return new ProgramState(id, exeStack, symTable, out, fileTable, heap, this.statement);
    }

    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
}
