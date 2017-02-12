using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class MyStatementExecutionException : ApplicationException
    {
        public MyStatementExecutionException() { }
        public MyStatementExecutionException(string msg) : base(msg) { }
        public MyStatementExecutionException(string msg, Exception e) : base(msg, e) { }
    }
}
