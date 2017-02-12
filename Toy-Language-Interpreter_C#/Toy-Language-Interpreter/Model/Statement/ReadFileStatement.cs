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
            MyIDictionary<int, MyPair<string, StreamReader>> fileTable = state.GetFileTab();
            MyIDictionary<string, int> symTable = state.GetSymTab();

            // 1. evaluate var_file_id to a value
            int value;
            try
            {
                value = var_file_id.Evaluate(symTable);
            }
            catch (ExpressionEvaluationException e)
            {
                throw new MyStatementExecutionException("Cannot read from var " + var_file_id.ToString() + ".");
            }

            // 2. get the StreamReader object associated in the file table. If there is no entry associated
            // with that value in the file table, we stop the execution
            StreamReader sr;
            try
            {
                sr = fileTable.Get(value).GetSecond();
            }
            catch (InvalidKeyMyDictionaryException)
            {
                throw new MyStatementExecutionException("Error. There is no entry (" + value + ") in the file table.");
            }

            // 3. Read a line from the file using the ReadLine() method of the StreamReader object.
            // If line is null create a zero int value, otherwise translate the string into an int value
            int read;
            try
            {
                string line = sr.ReadLine();
                read = line.Equals(null) ? 0 : Int32.Parse(line);
            }
            catch (IOException)
            {
                throw new MyStatementExecutionException("Error on reading from file.");
            }

            // 4. Add a new mapping (var_name, int value computed at the previous step) into the sym-table.
            // If var_name exists in sym-table, update its associated value instead of adding a new mapping
            symTable.Put(var_name, read);

            // 5. return
            return null;
        }

        public override string ToString()
        {
            return "readFile(" + var_file_id.ToString() + ", " + var_name + ")";
        }
    }
}
