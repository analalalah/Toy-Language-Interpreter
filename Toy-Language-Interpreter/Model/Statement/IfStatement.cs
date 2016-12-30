using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.Model.Expression;
using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.Model.Statement
{
    class IfStatement : IStatement
    {
        private AExpression expr;
        private IStatement thenStatement;
        private IStatement elseStatement;

        public IfStatement(AExpression expr, IStatement thenStatement, IStatement elseStatement)
        {
            this.expr = expr;
            this.thenStatement = thenStatement;
            this.elseStatement = elseStatement;
        }

        public ProgramState Execute(ProgramState state)
        {
            MyIStack<IStatement> st = state.GetExeStack();
            MyIDictionary<string, int> symTab = state.GetSymTab();

            try
            {
                if (expr.Evaluate(symTab) != 0)
                    st.Push(thenStatement);
                else
                    st.Push(elseStatement);
            }
            catch (ExpressionEvaluationException e)
            {
                Console.Write(e.ToString());
            }
            return state;
        }

        public override string ToString()
        {
            return "If (" + expr.ToString() + ") Then (" +
                thenStatement.ToString() + ") Else (" + elseStatement.ToString() + ")";
        }
    }
}
