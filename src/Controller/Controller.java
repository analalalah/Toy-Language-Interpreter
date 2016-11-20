package Controller;

import DataTypes.*;
import Model.ProgramState;
import Model.Statement.IStatement;
import Repository.IRepository;
import Exception.MyStatementExecutionException;
import Exception.EmptyMyStackException;

import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by vladc on 25.10.2016.
 */
public class Controller {
    private IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void add(IStatement st) {
        ProgramState state = new ProgramState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                st
        );
        this.repo.add(state);
    }

    public ProgramState getCurrentProgram() {
        return repo.getCurrentProgram();
    }

    public ProgramState oneStep(ProgramState state) throws MyStatementExecutionException {
        MyIStack<IStatement> st = state.getExeStack();

        try {
            IStatement currentStatement = st.pop();
            return currentStatement.execute(state);
        }
        catch (EmptyMyStackException e) {
            throw new MyStatementExecutionException("Exception. Execution Stack is empty.");
        }
    }

    public void allStep() throws MyStatementExecutionException {
        ProgramState program = repo.getCurrentProgram();

        while (!program.getExeStack().isEmpty()) {
            oneStep(program);
            program.getHeap().setContent(conservativeGarbageCollector(
                    program.getSymTable().values(),
                    program.getHeap().getContent()
            ));
            System.out.println(program.toString());
            repo.logProgramStateExec();
        }
        repo.logEndOfExecution();
    }

    private Map<Integer, Integer> conservativeGarbageCollector(
            Collection<Integer> symTableValues,
            Map<Integer, Integer> heap
    ) {
        return heap.entrySet().stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
