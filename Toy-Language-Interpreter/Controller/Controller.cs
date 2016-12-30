using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.Repository;
using Toy_Language_Interpreter.Model;
using Toy_Language_Interpreter.Model.Statement;
using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.MyException;

using System.IO;

namespace Toy_Language_Interpreter.Controller
{
    class MyController
    {
        private IRepository repo;

        public MyController(IRepository repo)
        {
            this.repo = repo;
        }

        public void Add(IStatement st)
        {
            ProgramState state = new ProgramState(new MyStack<IStatement>(), new MyDictionary<string, int>(),
                new MyList<int>(), new MyDictionary<int, MyPair<string, StreamReader>>(), st);
            this.repo.Add(state);
        }

        public ProgramState GetCurrentProgram()
        {
            return this.repo.GetCurrentProgram();
        }

        public ProgramState OneStep(ProgramState state)
        {
            MyIStack<IStatement> st = state.GetExeStack();

            try
            {
                IStatement currentStatement = st.Pop();
                return currentStatement.Execute(state);
            }
            catch (EmptyMyStackException)
            {
                throw new MyStatementExecutionException("Exception. Execution stack is empty.");
            }
        }

        public void AllStep()
        {
            ProgramState program = repo.GetCurrentProgram();

            while (!program.GetExeStack().IsEmpty())
            {
                OneStep(program);
                repo.LogProgramStateExec();
            }
        }
    }
}
