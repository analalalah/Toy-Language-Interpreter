using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.Model;

using System.IO;

namespace Toy_Language_Interpreter.Repository
{
    class MemoryRepository : IRepository
    {
        private List<ProgramState> programStates;
        private string logFilePath;

        public MemoryRepository(string logFilePath)
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

        public void LogProgramStateExec(ProgramState state)
        {
            StreamWriter fw;

            try
            {
                fw = new StreamWriter(logFilePath, true);
                fw.Write(state.ToString());
                //fw.Write("\n");
                fw.Close();
            }
            catch (IOException e)
            {
                Console.WriteLine(e.ToString());
            }
        }
    }
}
