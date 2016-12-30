using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.Model;

namespace Toy_Language_Interpreter.Repository
{
    class Repository
    {
        private List<ProgramState> programStates;
        private string logFilePath;

        public Repository(string logFilePath)
        {
            this.programStates = new List<ProgramState>();
            this.logFilePath = logFilePath;
        }

        public ProgramState GetCurrentProgram()
        {
            return programStates.ElementAt(programStates.Count - 1);
        }

        public void Add(ProgramState state)
        {
            programStates.Add(state);
        }

        public void LogProgramStateExec()
        {
            throw new NotImplementedException();
        }
    }
}
