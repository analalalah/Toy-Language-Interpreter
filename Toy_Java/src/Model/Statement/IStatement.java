package Model.Statement;

import Model.ProgramState;
import Exception.MyStatementExecutionException;

import java.io.Serializable;

/**
 * Created by vladc on 12.10.2016.
 */
public interface IStatement extends Serializable {
    ProgramState execute(ProgramState state) throws MyStatementExecutionException;
    String toString();
}
