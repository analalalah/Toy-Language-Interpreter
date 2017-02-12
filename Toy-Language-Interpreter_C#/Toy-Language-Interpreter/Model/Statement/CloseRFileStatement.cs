using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.Model.Expression;
using Toy_Language_Interpreter.MyException;

using System.IO;

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
            MyIDictionary<int, MyPair<string, StreamReader>> fileTable = state.GetFileTab();
            MyIDictionary<string, int> symTable = state.GetSymTab();

            // 1. evaluate var_file_id to a value. If any error occurs, stop the execution
            int value;
            try
            {
                value = var_file_id.Evaluate(symTable);
            }
            catch (ExpressionEvaluationException e)
            {
                throw new MyStatementExecutionException("The file is not opened for reading.");
            }

            // 2. Use the value to get the entry into the file table and get the associated StreamReader
            // object. If there is no entry in the file table for the value, we stop the execution
            StreamReader sr;
            try
            {
                sr = fileTable.Get(value).GetSecond();
            }
            catch (InvalidKeyMyDictionaryException)
            {
                throw new MyStatementExecutionException("Error. There is no entry (" + value + ") in the file table.");
            }

            // 3. call the Close() method
            try
            {
                sr.Close();
            }
            catch (IOException e)
            {
                throw new MyStatementExecutionException("IO Error: " + e.ToString());
            }

            // 4. delete the entry from the file table
            try
            {
                fileTable.Remove(value);
            }
            catch (InvalidKeyMyDictionaryException)
            {
                throw new MyStatementExecutionException("Error. There is no entry (" + value + ") in the file table.");
            }

            // 5. return
            return null;
        }

        public override string ToString()
        {
            return "closeRFile(" + var_file_id + ")";
        }
    }
}
