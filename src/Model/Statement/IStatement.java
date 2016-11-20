package Model.Statement;

import Model.ProgramState;
import Exception.MyStatementExecutionException;

/**
 * Created by vladc on 12.10.2016.
 */
public interface IStatement {
    ProgramState execute(ProgramState state) throws MyStatementExecutionException;
    String toString();
}
