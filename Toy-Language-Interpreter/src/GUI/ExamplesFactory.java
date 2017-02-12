package GUI;

import Model.Expression.ArithmeticExpression;
import Model.Expression.BooleanExpression.EqualsExpression;
import Model.Expression.ConstantExpression;
import Model.Expression.ReadHeap;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladc
 */
public class ExamplesFactory {
    public static List<IStatement> getAll() {
        List<IStatement> list = new ArrayList<>();

        list.add(example1());
        list.add(example2());
        list.add(example3());
        list.add(example4());
        list.add(example5());
        list.add(example6());
        list.add(example7());
        list.add(example8());

        return list;
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

    /*
     ** v=10; new(a,22);
     ** fork(wH(a,30); v=32; print(v); print(rH(a)));
     ** print(v); print(rH(a))
     */
    private static IStatement example2() {
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

    /*
    **  print(2 / 0);
     */
    private static IStatement example4() {
        return new PrintStatement(new ArithmeticExpression(
                new ConstantExpression(2),
                new ConstantExpression(0),
                '/'
        ));
    }

    private static IStatement example5() {
        String test_in_path = "res\\test.in";

        IStatement ex1;
        IStatement ex1_1 = new OpenRFileStatement("var_f", test_in_path);
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
        IStatement ex1_4 = new CloseRFileStatement(new VariableExpression("var_f"));
        ex1 = new CompoundStatement(ex1_1, new CompoundStatement(ex1_2, new CompoundStatement(ex1_3, ex1_4)));

        return ex1;
    }

    private static IStatement example6() {
        IStatement ex;
        IStatement op1 = new AssignmentStatement("a", new ConstantExpression(1));
        IStatement op2 = new AssignmentStatement("a", new ArithmeticExpression(
                new VariableExpression("a"),
                new ConstantExpression(3),
                '+'
        ));
        IStatement op3 = new AssignmentStatement("b", new ConstantExpression(4));

        ex = new CompoundStatement(op1, new CompoundStatement(op2, op3));
        return ex;
    }

    private static IStatement example7() {
        IStatement ex;
        IStatement op1 = new AssignmentStatement("v", new ConstantExpression(0));

        IStatement op2_inside_fork = new CompoundStatement(
                new PrintStatement(new VariableExpression("v")),
                new AssignmentStatement("v",
                        new ArithmeticExpression(
                                new VariableExpression("v"),
                                new ConstantExpression(1),
                                '-'
                        ))
        );

        IStatement op2_compound = new CompoundStatement(
                new ForkStatement(op2_inside_fork),
                new AssignmentStatement("v",
                        new ArithmeticExpression(
                                new VariableExpression("v"),
                                new ConstantExpression(1),
                                '+'
                        ))
        );

        IStatement op2 = new RepeatUntilStatement(
                op2_compound,
                new EqualsExpression(
                        new VariableExpression("v"),
                        new ConstantExpression(3)
                )
        );

        IStatement op3 = new CompoundStatement(
                new AssignmentStatement("x", new ConstantExpression(1)),
                new CompoundStatement(
                        new AssignmentStatement("y", new ConstantExpression(2)),
                        new CompoundStatement(
                                new AssignmentStatement("z", new ConstantExpression(3)),
                                new AssignmentStatement("w", new ConstantExpression(4))
                        )
                )
        );

        IStatement op4 = new PrintStatement(
                new ArithmeticExpression(
                        new VariableExpression("v"),
                        new ConstantExpression(10),
                        '*'
                )
        );

        ex = new CompoundStatement(op1,
                new CompoundStatement(op2,
                        new CompoundStatement(op3, op4)));

        return ex;
    }

    private static IStatement example8() {
        IStatement ex;

        IStatement line1;
        IStatement l11 = new NewStatement("v1", new ConstantExpression(2));
        IStatement l12 = new NewStatement("v2", new ConstantExpression(3));
        IStatement l13 = new NewStatement("v3", new ConstantExpression(4));
        IStatement l14 = new NewLatchStatement("cnt", new ReadHeap("v2"));
        line1 = new CompoundStatement(l11, new CompoundStatement(l12, new CompoundStatement( l13, l14)));

        IStatement line2;
        IStatement l21 = new WriteHeap("v1",
                new ArithmeticExpression(
                        new ReadHeap("v1"),
                        new ConstantExpression(10),
                        '*'
                ));
        IStatement l22 = new PrintStatement(new ReadHeap("v1"));
        IStatement l23 = new CountDownStatement("cnt");
        line2 = new ForkStatement(
                new CompoundStatement(
                        new CompoundStatement(l21, l22),
                        l23
                )
        );

        IStatement line3;
        IStatement l31 = new WriteHeap("v2",
                new ArithmeticExpression(
                        new ReadHeap("v2"),
                        new ConstantExpression(10),
                        '*'
                ));
        IStatement l32 = new PrintStatement(new ReadHeap("v2"));
        IStatement l33 = new CountDownStatement("cnt");
        line3 = new ForkStatement(
                new CompoundStatement(
                        new CompoundStatement(l31, l32),
                        l33
                )
        );

        IStatement line4;
        IStatement l41 = new WriteHeap("v3",
                new ArithmeticExpression(
                        new ReadHeap("v3"),
                        new ConstantExpression(10),
                        '*'
                ));
        IStatement l42 = new PrintStatement(new ReadHeap("v3"));
        IStatement l43 = new CountDownStatement("cnt");
        line4 = new ForkStatement(
                new CompoundStatement(
                        new CompoundStatement(l41, l42),
                        l43
                )
        );

        IStatement line5 = new AwaitStatement("cnt");
        IStatement line6 = new PrintStatement(new VariableExpression("cnt"));
        IStatement line7 = new CountDownStatement("cnt");
        IStatement line8 = new PrintStatement(new VariableExpression("cnt"));

        ex = new CompoundStatement(line1,
                new CompoundStatement(line2,
                        new CompoundStatement(line3,
                                new CompoundStatement(line4,
                                        new CompoundStatement(line5,
                                                new CompoundStatement(line6,
                                                        new CompoundStatement(line7,
                                                        line8)))))));

        return ex;
    }
}
