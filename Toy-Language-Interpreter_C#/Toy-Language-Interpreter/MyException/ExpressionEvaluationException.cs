using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class ExpressionEvaluationException : ApplicationException
    {
        public ExpressionEvaluationException() { }
        public ExpressionEvaluationException(string str) : base(str) { }
        public ExpressionEvaluationException(string str, Exception e) : base(str, e) { }
    }
}
