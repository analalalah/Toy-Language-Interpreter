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
    class AssignmentStatement : IStatement
    {
        private string id;
        private AExpression expr;

        public AssignmentStatement(string id, AExpression expr)
        {
            this.id = id;
            this.expr = expr;
        }

        public ProgramState Execute(ProgramState state)
        {
            MyIDictionary<string, int> symTab = state.GetSymTab();
            try
            {
                int value = expr.Evaluate(symTab);
                symTab.Put(id, value);
            }
            catch (ExpressionEvaluationException e)
            {
                Console.WriteLine(e.ToString());
            }
            return null;
        }

        public override string ToString()
        {
            return id + " = " + expr.ToString();
        }
    }
}
