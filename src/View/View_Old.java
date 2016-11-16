package View;

import Controller.Controller;
import Model.Statement.*;
import Model.Expression.*;
import Exception.MyStatementExecutionException;

import java.util.Scanner;

/**
 * Created by vladc on 25.10.2016.
 */
public class View_Old {
    private Controller  ctrl;
    private Scanner     cin;

    public View_Old(Controller ctrl) {
        this.ctrl = ctrl;
        this.cin = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMainMenu();
            int userInput = readInteger();
            switch (userInput) {
                case 1:
                    ctrl.add(getExample1());
                    break;
                case 2:
                    ctrl.add(getExample2());
                    break;
                case 3:
                    ctrl.add(getExample3());
                    break;
                case 4:
                    ctrl.add(getExample4());
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Wrong input! Please try again!");
                    break;
            }
            if (1 <= userInput && userInput <= 4)
                runSecond();
        }
    }

    private void runSecond() {
        while (true) {
            printSecondMenu();
            int userInput = readInteger();
            try {
                switch (userInput) {
                    case 1:
                        ctrl.oneStep(ctrl.getCurrentProgram());
                        break;
                    case 2:
                        ctrl.allStep();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Wrong input! Please try again!");
                        break;
                }
            }
            catch (MyStatementExecutionException e) {
                System.err.println(e.toString());
                System.out.println("Getting back to main menu...");
                return;
            }
        }
    }

    private void printMainMenu() {
        String str = "1. v = 2; Print(v)\n" +
                "2. a = 2 + 3 * 5; b = a + 1; Print(b)\n" +
                "3. a = 2 - 2; (If a Then v = 2 Else v = 3); Print(v)\n" +
                "4. b = 1 / 0; Print(b)\n" +
                "0. EXIT\n" +
                "Your option: ";
        System.out.print(str);
    }

    private void printSecondMenu() {
        String str = "1. one-step execution\n" +
                "2. all-step execution\n" +
                "0. go back to main menu\n" +
                "Your option: ";
        System.out.print(str);
    }

    private int readInteger() {
        int ret;

        while (!cin.hasNextInt()) {
            cin.next();
            System.out.println("Wrong input! Please input an integer.");
        }
        ret = cin.nextInt();
        cin.nextLine();
        return ret;
    }

    // Example 1.
    // v=2;Print(v)
    private IStatement getExample1() {
        IStatement st1 = new AssignmentStatement("v", new ConstantExpression(2));
        IStatement st2 = new PrintStatement(new VariableExpression("v"));
        return new CompoundStatement(st1, st2);
    }

    // Example 2.
    // a=2+3*5;b=a+1;Print(b)
    private IStatement getExample2() {
        Expression expr1 = new ArithmeticExpression(new ConstantExpression(2),
                new ArithmeticExpression(new ConstantExpression(3), new ConstantExpression(5), '*'),
                '+');
        IStatement st1 = new AssignmentStatement("a", expr1);
        Expression expr2 = new ArithmeticExpression(new VariableExpression("a"), new ConstantExpression(1), '+');
        IStatement st2 = new AssignmentStatement("b", expr2);
        IStatement st3 = new PrintStatement(new VariableExpression("b"));

        return new CompoundStatement(st1, new CompoundStatement(st2, st3));
    }

    // Example 3.
    // a=2-2;
    // (If a Then v=2 Else v=3);
    //  Print(v)
    private IStatement getExample3() {
        IStatement st1 = new AssignmentStatement("a", new ArithmeticExpression(
                new ConstantExpression(2), new ConstantExpression(2), '-'
        ));
        IStatement st2 = new IfStatement(new VariableExpression("a"),
                new AssignmentStatement("v", new ConstantExpression(2)),
                new AssignmentStatement("v", new ConstantExpression(3)));
        IStatement st3 = new PrintStatement(new VariableExpression("v"));
        return new CompoundStatement(st1, new CompoundStatement(st2, st3));
    }

    // b = 1 / 0; Print(b)
    private IStatement getExample4() {
        Expression expr = new ArithmeticExpression(new ConstantExpression(1), new ConstantExpression(0), '/');
        IStatement st1 = new AssignmentStatement("b", expr);
        IStatement st2 = new PrintStatement(new VariableExpression("b"));
        return new CompoundStatement(st1, st2);
    }
}
