using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.Model.Statement;

using System.IO;

namespace Toy_Language_Interpreter.Model
{
    class ProgramState
    {
        private MyIStack<IStatement> exeStack;
        private MyIDictionary<string, int> symTab;
        private MyIList<int> outList;
        private MyIDictionary<int, MyPair<string, StreamReader>> fileTab;
        private IStatement originalProgram;

        public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<string, int> symTab,
            MyIList<int> outList, MyIDictionary<int, MyPair<string, StreamReader>> fileTab,
            IStatement originalProgram)
        {
            this.exeStack = exeStack;
            this.symTab = symTab;
            this.outList = outList;
            this.fileTab = fileTab;
            this.originalProgram = originalProgram;

            this.exeStack.Push(originalProgram);
        }

        public MyIStack<IStatement> GetExeStack()
        {
            return exeStack;
        }

        public MyIDictionary<string, int> GetSymTab()
        {
            return symTab;
        }

        public MyIList<int> GetOutList()
        {
            return outList;
        }

        public MyIDictionary<int, MyPair<string, StreamReader>> GetFileTab()
        {
            return fileTab;
        }

        public override string ToString()
        {
            return "ExeStack:\n" + exeStack.ToString() +
                "\nSymTable:\n" + symTab.ToString() +
                "\nOutList:\n" + outList.ToString() +
                "\nFileTable:\n" + fileTab.ToString() +
                "--------------------\n";
        }
    }
}
