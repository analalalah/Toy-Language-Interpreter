using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class DivisionByZeroException : ExpressionEvaluationException
    {
        public DivisionByZeroException() { }
        public DivisionByZeroException(string msg) : base(msg) { }
        public DivisionByZeroException(string msg, Exception e) : base(msg, e) { }
    }
}
