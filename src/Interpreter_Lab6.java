import Controller.Controller;
import DataTypes.*;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ConstantExpression;
import Model.Expression.ReadHeap;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Repository.*;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;

/**
 * Created by vladc on 20.11.2016.
 */
public class Interpreter_Lab6 {
    private static final String logFilePath = "res\\log.txt";

    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(getExample1());
        menu.addCommand(getExample2());
        menu.addCommand(getExample3());
        menu.addCommand(getExample4());
        menu.addCommand(new ExitCommand("0", "EXIT"));
        menu.show();
        System.out.println("Bye!");
    }

    /*  Heap Allocation Example
    **  -----------------------
    **  v = 10; new(v, 20); new(a, 22); print(v)
    **  At the end of execution:
    **  Heap   = {1->20, 2->22}
    **  SymTab = {v->1,  a->2}
    **  Out    = {1}
     */
    private static IStatement example1() {
        IStatement ret;
        IStatement op1 = new AssignmentStatement("v", new ConstantExpression(10));
        IStatement op2 = new NewStatement("v", new ConstantExpression(20));
        IStatement op3 = new NewStatement("a", new ConstantExpression(22));
        IStatement op4 = new PrintStatement(new VariableExpression("v"));

        ret = new CompoundStatement(
                op1, new CompoundStatement(
                        op2, new CompoundStatement(
                                op3, op4
        )));

        return ret;
    }

    private static RunExample getExample1() {
        IRepository repo = new Repository(logFilePath);
        repo.add(new ProgramState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                example1()
        ));
        Controller ctrl = new Controller(repo);
        return new RunExample("1", "Heap Allocation Example", ctrl);
    }

    /*  Heap Reading Example
    **  --------------------
    **  v = 10; new(v, 20); new(a, 22); print(100 + rH(v)); print(100 + rH(a))
    **  At the end of execution:
    **  Heap   = {1->20, 2->22}
    **  SymTab = {v->1,  a->2}
    **  Out    = {120,   122}
     */
    private static IStatement example2() {
        IStatement ret;
        IStatement op1 = new AssignmentStatement("v", new ConstantExpression(10));
        IStatement op2 = new NewStatement("v", new ConstantExpression(20));
        IStatement op3 = new NewStatement("a", new ConstantExpression(22));
        IStatement op4 = new PrintStatement(
                new ArithmeticExpression(new ConstantExpression(100), new ReadHeap("v"), '+')
        );
        IStatement op5 = new PrintStatement(
                new ArithmeticExpression(new ConstantExpression(100), new ReadHeap("a"), '+')
        );

        ret = new CompoundStatement(
                op1, new CompoundStatement(
                        op2, new CompoundStatement(
                                op3, new CompoundStatement(
                                        op4, op5
        ))));

        return ret;
    }

    private static RunExample getExample2() {
        IRepository repo = new Repository(logFilePath);
        repo.add(new ProgramState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                example2()
        ));
        Controller ctrl = new Controller(repo);
        return new RunExample("2", "Heap Reading Example", ctrl);
    }

    /*  Heap Writing Example
    **  --------------------
    **  v = 10; new(v, 20); new(a, 22); wH(a, 30); print(a); print(rH(a))
    **  At the end of execution:
    **  Heap   = {1->20, 2->30}
    **  SymTab = {v->1,  a->2}
    **  Out    = {2,     30}
     */
    private static IStatement example3() {
        IStatement ret;
        IStatement op1 = new AssignmentStatement("v", new ConstantExpression(10));
        IStatement op2 = new NewStatement("v", new ConstantExpression(20));
        IStatement op3 = new NewStatement("a", new ConstantExpression(22));
        IStatement op4 = new WriteHeap("a", new ConstantExpression(30));
        IStatement op5 = new PrintStatement(new VariableExpression("a"));
        IStatement op6 = new PrintStatement(new ReadHeap("a"));

        ret = new CompoundStatement(
                op1, new CompoundStatement(
                        op2, new CompoundStatement(
                                op3, new CompoundStatement(
                                        op4, new CompoundStatement(
                                                op5, op6
        )))));

        return ret;
    }

    private static RunExample getExample3() {
        IRepository repo = new Repository(logFilePath);
        repo.add(new ProgramState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                example3()
        ));
        Controller ctrl = new Controller(repo);
        return new RunExample("3", "Heap Writing Example", ctrl);
    }

    /*  Garbage Collector Example
    **  -------------------------
    **  v = 10; new(v, 20); new(a, 22); wH(a, 30); print(a); print(rH(a)); a = 0
    **  At the end of execution:
    **  Heap   = {1->20}
    **  SymTab = {v->1,  a->0}
    **  Out    = {2,     30}
     */
    private static IStatement example4() {
        IStatement ret;
        IStatement op1 = new AssignmentStatement("v", new ConstantExpression(10));
        IStatement op2 = new NewStatement("v", new ConstantExpression(20));
        IStatement op3 = new NewStatement("a", new ConstantExpression(22));
        IStatement op4 = new WriteHeap("a", new ConstantExpression(30));
        IStatement op5 = new PrintStatement(new VariableExpression("a"));
        IStatement op6 = new PrintStatement(new ReadHeap("a"));
        IStatement op7 = new AssignmentStatement("a", new ConstantExpression(0));

        ret = new CompoundStatement(
                op1, new CompoundStatement(
                        op2, new CompoundStatement(
                                op3, new CompoundStatement(
                                        op4, new CompoundStatement(
                                                op5, new CompoundStatement(
                                                        op6, op7
        ))))));

        return ret;
    }

    private static RunExample getExample4() {
        IRepository repo = new Repository(logFilePath);
        repo.add(new ProgramState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
                example4()
        ));
        Controller ctrl = new Controller(repo);
        return new RunExample("4", "Garbage Collector Example", ctrl);
    }
}
