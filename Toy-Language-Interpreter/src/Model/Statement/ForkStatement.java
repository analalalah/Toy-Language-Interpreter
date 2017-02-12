package Model.Statement;

import DataTypes.*;
import Exception.MyStatementExecutionException;
import Model.IdProvider;
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
//        int id = state.getId() * 10;
        int id = IdProvider.getNextId();

        MyIStack<IStatement> exeStack = new MyStack<>();
//        exeStack.push(this.statement); // the statement is pushed on the stack by the constructor of ProgramState

        MyIDictionary<String, Integer> symTable = new MyDictionary<>();
        symTable.clone(state.getSymTable());

        MyIList<Integer> out = state.getOut();

        MyIDictionary<Integer, MyPair<String, BufferedReader>> fileTable = state.getFileTable();

        MyIHeap heap = state.getHeap();

//        MyIDictionary<Integer, Integer> latchTable = state.getLatchTable();

        MyILatchTable latchTable = state.getLatchTable();

        return new ProgramState(id, exeStack, symTable, out, fileTable, heap, latchTable, this.statement);
    }

    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
}
