using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class EmptyMyStackException : ApplicationException
    {
        public EmptyMyStackException() { }
        public EmptyMyStackException(string str) : base(str) { }
        public EmptyMyStackException(string str, Exception e) : base(str, e) { }
    }
}
