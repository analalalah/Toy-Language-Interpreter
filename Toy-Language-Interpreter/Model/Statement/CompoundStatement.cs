using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;

namespace Toy_Language_Interpreter.Model.Statement
{
    class CompoundStatement : IStatement
    {
        private IStatement first;
        private IStatement second;
        public CompoundStatement(IStatement first, IStatement second)
        {
            this.first = first;
            this.second = second;
        }
        public ProgramState Execute(ProgramState state)
        {
            MyIStack<IStatement> st = state.GetExeStack();
            st.Push(this.second);
            st.Push(this.first);
            return state;
        }

        public override string ToString()
        {
            return "(" + first.ToString() + " ; " + second.ToString() + ")";
        }
    }
}
