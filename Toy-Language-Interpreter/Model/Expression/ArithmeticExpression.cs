using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.Model.Expression
{
    class ArithmeticExpression : AExpression
    {
        private AExpression expr1;
        private AExpression expr2;
        private char op;

        public ArithmeticExpression(AExpression expr1, char op, AExpression expr2)
        {
            this.expr1 = expr1;
            this.expr2 = expr2;
            this.op = op;
        }

        public override int Evaluate(MyIDictionary<string, int> symTab)
        {
            if (op == '+')
                return (expr1.Evaluate(symTab) + expr2.Evaluate(symTab));
            if (op == '-')
                return (expr1.Evaluate(symTab) - expr2.Evaluate(symTab));
            if (op == '*')
                return (expr1.Evaluate(symTab) * expr2.Evaluate(symTab));
            if (op == '/')
            {
                if (expr2.Evaluate(symTab) == 0)
                    throw new DivisionByZeroException();
                return (expr1.Evaluate(symTab) / expr2.Evaluate(symTab));
            }
            throw new InvalidArithmeticOperatorException();
        }

        public override string ToString()
        {
            return expr1.ToString() + " " + op + " " + expr2.ToString();
        }
    }
}
