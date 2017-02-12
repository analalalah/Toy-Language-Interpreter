using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.Model;

namespace Toy_Language_Interpreter.Repository
{
    interface IRepository
    {
        ProgramState GetCurrentProgram();
        void Add(ProgramState state);
        void LogProgramStateExec(ProgramState state);
    }
}
