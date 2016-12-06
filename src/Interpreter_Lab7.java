import Controller.Controller;
import DataTypes.MyDictionary;
import DataTypes.MyHeap;
import DataTypes.MyList;
import DataTypes.MyStack;
import Model.Expression.*;
import Model.Expression.BooleanExpression.LessThanExpression;
import Model.ProgramState;
import Model.Statement.*;
import Repository.IRepository;
import Repository.Repository;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;

/**
 * Created by vladc on 20.11.2016.
 */
@Deprecated
public class Interpreter_Lab7 {
    private static final String logFilePath = "res\\log.txt";

    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(getExample1());
        menu.addCommand(getExample2());
//        menu.addCommand(getExample3());
//        menu.addCommand(getExample4());
        menu.addCommand(new ExitCommand("0", "EXIT"));
        menu.show();

//        getExample1().getController().serialize();
//        System.out.println(getExample1().getController().getCurrentProgram().toString());

//        ProgramState st = new Controller(new Repository(logFilePath)).deserialize();
//        System.out.println(st.toString());
    }

    /*  While Statement Example
    **  -----------------------
    **  v = 6; (while(v - 4) print(v); v = v - 1); print(v)
     */
    private static IStatement example1() {
        IStatement ret;
        IStatement op1 = new AssignmentStatement("v", new ConstantExpression(6));
        IStatement op2 = new WhileStatement(
                new ArithmeticExpression(
                        new VariableExpression("v"),
                        new ConstantExpression(4), '-'
                ),
                new CompoundStatement(
                        new PrintStatement(new VariableExpression("v")),
                        new AssignmentStatement("v", new ArithmeticExpression(
                                new VariableExpression("v"),
                                new ConstantExpression(1), '-'
                        ))
                )
        );
        IStatement op3 = new PrintStatement(new VariableExpression("v"));

        ret = new CompoundStatement(
                op1, new CompoundStatement(
                        op2, op3
        ));

        return ret;
    }

    private static RunExample getExample1() {
        IRepository repo = new Repository(logFilePath);
        Controller ctrl = new Controller(repo);
        ctrl.add(example1());
        return new RunExample("1", "While Statement Example", ctrl);
    }

    /* Example boolean expressions
    ** ---------------------------
    ** x = 10 + (2 < 6);
    ** y = (10 + 2) < 6;
    ** print(x);
    ** print(y);
     */
    private static IStatement example2() {
        IStatement ret;
        Expression expr1 = new ArithmeticExpression(
                new ConstantExpression(10),
                new LessThanExpression(
                        new ConstantExpression(2),
                        new ConstantExpression(6)
                ),
                '+'
        );
        Expression expr2 = new LessThanExpression(
                new ArithmeticExpression(
                        new ConstantExpression(10),
                        new ConstantExpression(2),
                        '+'
                ),
                new ConstantExpression(6)
        );
        IStatement op1 = new AssignmentStatement("x", expr1);
        IStatement op2 = new AssignmentStatement("y", expr2);
        IStatement op3 = new PrintStatement(new VariableExpression("x"));
        IStatement op4 = new PrintStatement(new VariableExpression("y"));

        ret = new CompoundStatement(
                op1, new CompoundStatement(
                        op2, new CompoundStatement(
                                op3, op4
        )));

        return ret;
    }

    private static RunExample getExample2() {
        IRepository repo = new Repository(logFilePath);
        Controller ctrl = new Controller(repo);
        ctrl.add(example2());
        return new RunExample("2", "Boolean Expressions Example", ctrl);
    }
}
