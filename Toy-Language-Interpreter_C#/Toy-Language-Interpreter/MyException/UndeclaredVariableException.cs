using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class UndeclaredVariableException : ExpressionEvaluationException
    {
        public UndeclaredVariableException() { }
        public UndeclaredVariableException(string str) : base(str) { }
        public UndeclaredVariableException(string str, Exception e) : base(str, e) { }
    }
}
