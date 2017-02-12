package Controller;

import DataTypes.*;
import Model.ProgramState;
import Model.Statement.IStatement;
import Repository.IRepository;
import Exception.MyStatementExecutionException;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by vladc on 25.10.2016.
 */
public class Controller {
    private IRepository     repo;
    private static int      id = 1;
    private ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void add(IStatement st) {
        ProgramState state = new ProgramState(
                id,
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
//                new MyDictionary<>(),
                new MyLatchTable(),
                st
        );
        this.repo.add(state);
        id++;
    }

    public IRepository getRepository() {
        return repo;
    }

    public void allStepGUI() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        // remove the completed programs
        List<ProgramState> list = removeCompletedPrograms(repo.getProgramList());
        if (list.size() == 0) {
            // display a windows message saying that the execution terminates
            executor.shutdownNow();
        }
        else {
//            try {
                oneStepForAllPrograms(list);
//            }
//            catch (InterruptedException e) {
//                System.err.println("The execution Thread has been interrupted.");
//                e.printStackTrace();
//            }

            executor.shutdownNow();
        }
    }

    public void allStep() throws MyStatementExecutionException {
        executor = Executors.newFixedThreadPool(2);
        while (true) {
            // remove the completed programs
            List<ProgramState> list = removeCompletedPrograms(repo.getProgramList());
            if (list.size() == 0)
                break ; // complete the execution of all threads
            try {
                oneStepForAllPrograms(list);
            }
            catch (InterruptedException e) {
                System.err.println("The execution Thread has been interrupted.");
                e.printStackTrace();
            }

//            // Garbage Collector functionality -- NO!
//            list.get(0).getHeap().setContent(conservativeGarbageCollector(
//                    list.get(0).getSymTable().values(),
//                    list.get(0).getHeap().getContent()
//            ));
        }

        executor.shutdownNow();
    }

    private void oneStepForAllPrograms(List<ProgramState> list) throws InterruptedException {
        // I. Log the ProgramStates before the execution
        list.forEach(p -> repo.logProgramStateExec(p));

        // II. RUN concurrently one step for each of the existing ProgramStates
        // ------------------------------
        // 1. prepare the list of Callable
        List<Callable<ProgramState>> callList = list.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> p.oneStep()))
                .collect(Collectors.toList());

        /*
        List<Callable<ProgramState>> callList = list.stream()
                .map((ProgramState p) -> new Callable<ProgramState> () {
                    public ProgramState call() throws Exception {
                        return p.oneStep();
                    }
                })
                .collect(Collectors.toList());
         */

        // 2. start the execution of the Callable's (it returns the list of new created threads)
        List<ProgramState> newProgramList =
                executor.invokeAll(callList).stream()
                .map(future -> { try {
                                    return future.get();
                                }
                                catch (Exception e) {
                                    System.err.println("There is an exception in Controller::oneStepForAllPrograms");
                                    e.printStackTrace();
                                }
                                return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        // 3. add the new created threads to the list of existing threads
        list.addAll(newProgramList);

        // III. Log the ProgramStates after the execution
        list.forEach(p -> repo.logProgramStateExec(p));

        // IV. save the current programs in the repository
        repo.setProgramList(list);
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> list) {
        return list.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Deprecated
    private Map<Integer, Integer> conservativeGarbageCollector(
            Collection<Integer> symTableValues,
            Map<Integer, Integer> heap
    ) {
        return heap.entrySet().stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void serialize() {
        this.repo.serialize();
    }

    public void deserialize() {
        this.repo.deserialize();
    }

    public List<ProgramState> getProgramList() {
        return repo.getProgramList();
    }

    public ProgramState getProgramById(int id) {
        return repo.getProgramById(id);
    }
}
