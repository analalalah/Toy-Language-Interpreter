package Repository;

import Model.ProgramState;

import java.util.List;

/**
 * Created by vladc on 22.10.2016.
 */
public interface IRepository {
//    ProgramState getCurrentProgram();
    void add(ProgramState state);
    void logProgramStateExec(ProgramState state);
    void logEndOfExecution();
    void serialize();
    void deserialize();
    ProgramState getProgramById(int id);
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> list);
}
