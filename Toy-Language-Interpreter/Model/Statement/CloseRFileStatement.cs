using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.Model.Expression;

namespace Toy_Language_Interpreter.Model.Statement
{
    class CloseRFileStatement : IStatement
    {
        private AExpression var_file_id;

        public CloseRFileStatement(AExpression var_file_id)
        {
            this.var_file_id = var_file_id;
        }

        public ProgramState Execute(ProgramState state)
        {
            throw new NotImplementedException();
        }

        public override string ToString()
        {
            return "closeRFile(" + var_file_id + ")";
        }
    }
}
