import DataTypes.*;
import Model.Expression.*;
import Model.Statement.*;
import Repository.*;
import Controller.Controller;
import View.*;
import Model.ProgramState;

/**
 * Created by vladc on 08.11.2016.
 */
@Deprecated
public class Interpreter_Lab5 {
    public static void main(String[] args) {
        // exemplul 1
        IStatement ex1;
        IStatement ex1_1 = new OpenRFileStatement("var_f", "test.in");
        IStatement ex1_2 = new CompoundStatement(
                new ReadFileStatement(new VariableExpression("var_f"), "var_c"),
                new PrintStatement(new VariableExpression("var_c"))
        );
        IStatement ex1_3 = new IfStatement(
                new VariableExpression("var_c"),
                new CompoundStatement(
                        new ReadFileStatement(new VariableExpression("var_f"), "var_c"),
                        new PrintStatement(new VariableExpression("var_c"))
                ),
                new PrintStatement(new ConstantExpression(0))
        );
        IStatement ex1_4 = new CloseRFileStatement(new ArithmeticExpression(
                new VariableExpression("var_f"), new ConstantExpression(3), '+')
        );
        ex1 = new CompoundStatement(ex1_1, new CompoundStatement(ex1_2, new CompoundStatement(ex1_3, ex1_4)));
        IRepository r1 = new Repository("res\\log.txt");
        Controller c1 = new Controller(r1);
        c1.add(ex1);

        // exemplul 2
        IStatement ex2;
        IStatement ex2_1 = new OpenRFileStatement("var_f", "test.in");
        IStatement ex2_2 = new CompoundStatement(
                new ReadFileStatement(
                        new ArithmeticExpression(new VariableExpression("var_f"), new ConstantExpression(2), '+'),
                        "var_c"
                ),
                new PrintStatement(new VariableExpression("var_c"))
        );
        IStatement ex2_3 = new IfStatement(
                new VariableExpression("var_c"),
                new CompoundStatement(
                        new ReadFileStatement(new VariableExpression("var_f"), "var_c"),
                        new PrintStatement(new VariableExpression("var_c"))
                ),
                new PrintStatement(new ConstantExpression(0))
        );
        IStatement ex2_4 = new CloseRFileStatement(new VariableExpression("var_f"));
        ex2 = new CompoundStatement(ex2_1, new CompoundStatement(ex2_2, new CompoundStatement(ex2_3, ex2_4)));
        IRepository r2 = new Repository("res\\log.txt");
        Controller c2 = new Controller(r2);
        c2.add(ex2);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), c1));
        menu.addCommand(new RunExample("2", ex2.toString(), c2));
//        menu.addCommand(new RunExample("3", ex3.toString(), c3));

        menu.show();
    }
}
