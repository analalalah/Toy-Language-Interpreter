using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.View;
using Toy_Language_Interpreter.Repository;
using Toy_Language_Interpreter.Controller;
using Toy_Language_Interpreter.Model;
using Toy_Language_Interpreter.Model.Statement;
using Toy_Language_Interpreter.Model.Expression;

namespace Toy_Language_Interpreter
{
    class Program
    {
        private const string logFilePath = "..\\..\\res\\log.txt";
        static void Main(string[] args)
        {
            TextMenu menu = new TextMenu();

            menu.AddCommand(new ExitCommand("0", "Exit"));
            menu.AddCommand(GetExample(Example1(), "1", "v = 2; Print(v)"));
            menu.AddCommand(GetExample(Example2(), "2", "a = 2 + 3 * 5; b = a - 4 / 2 + 7; Print(b)"));
            menu.AddCommand(GetExample(Example3(), "3", "a = 2 - 2; If a Then v = 2 Else v = 3; Print(v)"));
            menu.AddCommand(GetExample(Example4(), "4", "Files example 1"));
            menu.AddCommand(GetExample(Example5(), "5", "Double open example"));
            menu.AddCommand(GetExample(Example6(), "6", "Open inexistent file example"));

            menu.Show();
        }

        private static RunExample GetExample(IStatement example, string key, string description)
        {
            IRepository repo = new MemoryRepository(logFilePath);
            MyController ctrl = new MyController(repo);
            ctrl.Add(example);
            return new RunExample(key, description, ctrl);
        }

        /**
         * v = 2; Print(v)
         * 
         */
        private static IStatement Example1()
        {
            IStatement ret;
            IStatement st1 = new AssignmentStatement("v", new ConstantExpression(2));
            IStatement st2 = new PrintStatement(new VariableExpression("v"));
            ret = new CompoundStatement(st1, st2);

            return ret;
        }

        /**
         * a = 2 + 3 * 5; b = a - 4 / 2 + 7; Print(b)
         * 
         */
        private static IStatement Example2()
        {
            IStatement ret;
            IStatement st1 = new AssignmentStatement("a",
                new ArithmeticExpression(
                    new ConstantExpression(2),
                    '+',
                    new ArithmeticExpression(
                        new ConstantExpression(3),
                        '*',
                        new ConstantExpression(5))
                        ));
            IStatement st2 = new AssignmentStatement("b",
                new ArithmeticExpression(
                    new ArithmeticExpression(
                        new VariableExpression("a"),
                        '-',
                        new ArithmeticExpression(
                            new ConstantExpression(4),
                            '/',
                            new ConstantExpression(2)
                            )),
                    '+',
                    new ConstantExpression(7)));
            IStatement st3 = new PrintStatement(new VariableExpression("b"));
            ret = new CompoundStatement(st1, new CompoundStatement(st2, st3));

            return ret;
        }

        /**
         * a = 2 - 2;
         * If a Then v = 2 Else v = 3;
         * Print(v)
         * 
         */
        private static IStatement Example3()
        {
            IStatement ret;
            IStatement st1 = new AssignmentStatement("a",
                new ArithmeticExpression(new ConstantExpression(2), '-', new ConstantExpression(2)));
            IStatement st2 = new IfStatement(
                new VariableExpression("a"),
                new AssignmentStatement("v", new ConstantExpression(2)),
                new AssignmentStatement("b", new ConstantExpression(3)));
            IStatement st3 = new PrintStatement(new VariableExpression("v"));
            ret = new CompoundStatement(st1, new CompoundStatement(st2, st3));

            return ret;
        }

        /**
         * openRFile(var_f, "test.in");
         * readFile(var_f, var_c); print(var_c);
         * (if var_c then readFile(var_f, var_c); print(var_c); else print(0));
         * closeRFile(var_f);
         * 
         */
        private static IStatement Example4()
        {
            IStatement ret;
            IStatement st1 = new OpenRFileStatement("var_f", "..\\..\\res\\test.in");
            IStatement st2 = new CompoundStatement(
                new ReadFileStatement(new VariableExpression("var_f"), "var_c"),
                new PrintStatement(new VariableExpression("var_c")));
            IStatement st3 = new IfStatement(
                new VariableExpression("var_c"),
                new CompoundStatement(
                    new ReadFileStatement(new VariableExpression("var_f"), "var_c"),
                    new PrintStatement(new VariableExpression("var_c"))),
                new PrintStatement(new ConstantExpression(0)));
            IStatement st4 = new CloseRFileStatement(new VariableExpression("var_f"));
            ret = new CompoundStatement(st1, new CompoundStatement(st2, new CompoundStatement(st3, st4)));

            return ret;
        }

        /**
         * openRFile(f, "test.in");
         * openRFile(g, "test.in");
         * closeRFile(g);
         * closeRFile(f);
         * 
         */
        private static IStatement Example5()
        {
            IStatement ret;
            IStatement st1 = new OpenRFileStatement("f", "..\\..\\res\\test.in");
            IStatement st2 = new OpenRFileStatement("g", "..\\..\\res\\test.in");
            IStatement st3 = new CloseRFileStatement(new VariableExpression("g"));
            IStatement st4 = new CloseRFileStatement(new VariableExpression("f"));
            ret = new CompoundStatement(st1, new CompoundStatement(st2, new CompoundStatement(st3, st4)));

            return ret;
        }

        /*
         * openRFile(f, "inexistent.txt");
         * 
         */
        private static IStatement Example6()
        {
            return new OpenRFileStatement("f", "..\\..\\res\\inexistent.txt");
        }
    }
}
