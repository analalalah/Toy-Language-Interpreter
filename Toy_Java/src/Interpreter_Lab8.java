import Controller.Controller;
import Model.Expression.ConstantExpression;
import Model.Expression.ReadHeap;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Repository.IRepository;
import Repository.Repository;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;

/**
 * Created by vladc on 06.12.2016.
 */
public class Interpreter_Lab8 {
    private static final String logFilePath = "res\\log.txt";

    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(getExample1());
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.show();
    }

    /*
     ** v=10; new(a,22);
     ** fork(wH(a,30); v=32; print(v); print(rH(a)));
     ** print(v); print(rH(a))
     */
    private static IStatement example1() {
        IStatement ret;
        IStatement st1 = new CompoundStatement(
                new AssignmentStatement("v", new ConstantExpression(10)),
                new NewStatement("a", new ConstantExpression(22))
        );
        IStatement st2 = new ForkStatement(
                new CompoundStatement(
                        new WriteHeap("a", new ConstantExpression(30)),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ConstantExpression(32)),
                                new CompoundStatement(
                                        new PrintStatement(new VariableExpression("v")),
                                        new PrintStatement(new ReadHeap("a"))
                                )
                        )
                )
        );
        IStatement st3 = new CompoundStatement(
                new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new ReadHeap("a"))
        );
        ret = new CompoundStatement(
                st1, new CompoundStatement(
                        st2, st3
        ));

        return ret;
    }

    private static RunExample getExample1() {
        IRepository repo = new Repository(logFilePath);
        Controller ctrl = new Controller(repo);
        ctrl.add(example1());
        return new RunExample("1", "Concurrency example", ctrl);
    }
}
