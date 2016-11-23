package Repository;

import Model.ProgramState;

/**
 * Created by vladc on 22.10.2016.
 */
public interface IRepository {
    ProgramState getCurrentProgram();
    void add(ProgramState state);
    void logProgramStateExec();
    void logEndOfExecution();
    void serialize();
    ProgramState deserialize();
}
