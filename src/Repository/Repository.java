package Repository;

import Model.ProgramState;
import java.util.*;

/**
 * Created by vladc on 22.10.2016.
 */
public class Repository implements IRepository {
    private Vector<ProgramState> programStates;

    public Repository() {
        this.programStates = new Vector<ProgramState>();
    }

    // temporary
    public ProgramState getCurrentProgram() {
        return programStates.get(programStates.size() - 1);
    }

    public void add(ProgramState state) {
        programStates.addElement(state);
    }

    //...
}
