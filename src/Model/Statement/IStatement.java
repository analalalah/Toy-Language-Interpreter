package Model.Statement;

import Model.ProgramState;

/**
 * Created by vladc on 12.10.2016.
 */
public interface IStatement {
    @Override
    String toString();
    ProgramState execute(ProgramState state);
}
