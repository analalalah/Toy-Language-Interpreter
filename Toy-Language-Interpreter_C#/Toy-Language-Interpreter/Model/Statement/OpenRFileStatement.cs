using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.MyException;

using System.IO;

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
            MyIDictionary<int, MyPair<string, StreamReader>> fileTable = state.GetFileTab();
            MyIDictionary<string, int> symTable = state.GetSymTab();

            // 1. check whether the filename is not already in the file table
            // If it exists stop the execution with an appropiate error message
            foreach (MyPair<string, StreamReader> pair in fileTable.Values())
            {
                if (pair.GetFirst().Equals(filename))
                {
                    throw new MyStatementExecutionException("File " + filename + " already open.");
                }
            }

            // 2. open the file filename. If the file does not exists or other IO error occurs,
            // stop the execution with an appropiate error message
            StreamReader sr;
            try
            {
                sr = new StreamReader(filename);
            }
            catch (FileNotFoundException)
            {
                throw new MyStatementExecutionException("File " + filename + " does not exist");
            }
            catch (IOException e)
            {
                throw new MyStatementExecutionException(e.ToString());
            }

            // 3. create a new entrance into the file table which maps a new unique integer id
            // to the (filename, StreamReader class created before) pair
            fileTable.Put(++fd, new MyPair<string, StreamReader>(filename, sr));

            // 4. set the var_file_id to that new unique integer key into the sym-table.
            symTable.Put(var_file_id, fd);

            // 5. return
            return null;
        }

        public override string ToString()
        {
            return "openRFile(" + var_file_id + ", \"" + filename + "\")";
        }
    }
}
