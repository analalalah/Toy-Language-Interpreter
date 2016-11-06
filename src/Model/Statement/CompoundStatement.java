package Model.Statement;

import Model.ProgramState;
import DataTypes.MyIStack;

/**
 * Created by vladc on 12.10.2016.
 */
public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + " ; " + second.toString() + ")";
    }

    public ProgramState execute(ProgramState state) {
        MyIStack<IStatement> st = state.getExeStack();
        st.push(this.second);
        st.push(this.first);
        return state;
    }
}
