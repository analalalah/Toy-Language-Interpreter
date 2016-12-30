using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.Model.Expression;

namespace Toy_Language_Interpreter.Model.Statement
{
    class ReadFileStatement : IStatement
    {
        private AExpression var_file_id;
        private string var_name;

        public ReadFileStatement(AExpression var_file_id, string var_name)
        {
            this.var_file_id = var_file_id;
            this.var_name = var_name;
        }

        public ProgramState Execute(ProgramState state)
        {
            throw new NotImplementedException();
        }

        public override string ToString()
        {
            return "readFile(" + var_file_id.ToString() + ", " + var_name + ")";
        }
    }
}
