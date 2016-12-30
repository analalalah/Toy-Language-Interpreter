using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.Model.Statement
{
    interface IStatement
    {
        ProgramState Execute(ProgramState state);
        string ToString();
    }
}
