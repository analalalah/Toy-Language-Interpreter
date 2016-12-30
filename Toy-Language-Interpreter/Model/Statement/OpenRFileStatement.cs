using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;

namespace Toy_Language_Interpreter.Model.Statement
{
    class OpenRFileStatement : IStatement
    {
        private string var_file_id;
        private string filename;
        private static int fd = 2;

        public OpenRFileStatement(string var_file_id, string filename)
        {
            this.var_file_id = var_file_id;
            this.filename = filename;
        }

        public ProgramState Execute(ProgramState state)
        {
            throw new NotImplementedException();
        }

        public override string ToString()
        {
            return "openRFile(" + var_file_id + ", \"" + filename + "\")";
        }
    }
}
