using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.Model.Expression;
using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.Model.Statement
{
    class PrintStatement : IStatement
    {
        private AExpression expr;

        public PrintStatement(AExpression expr)
        {
            this.expr = expr;
        }

        public ProgramState Execute(ProgramState state)
        {
            MyIList<int> outList = state.GetOutList();
            MyIDictionary<string, int> symTab = state.GetSymTab();

            try
            {
                outList.Enqueue(expr.Evaluate(symTab));
            }
            catch (ExpressionEvaluationException e)
            {
                Console.Write(e.ToString());
            }
            return null;
        }

        public override string ToString()
        {
            return "Print(" + expr.ToString() + ")";
        }
    }
}
