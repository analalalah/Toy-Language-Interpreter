using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class InvalidArithmeticOperatorException : ExpressionEvaluationException
    {
        public InvalidArithmeticOperatorException() { }
        public InvalidArithmeticOperatorException(string msg) : base(msg) { }
        public InvalidArithmeticOperatorException(string msg, Exception e) : base(msg, e) { }
    }
}
