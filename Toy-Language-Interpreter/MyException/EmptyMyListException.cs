using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class EmptyMyListException : ApplicationException
    {
        public EmptyMyListException() { }
        public EmptyMyListException(string msg) : base(msg) { }
        public EmptyMyListException(string msg, Exception e) : base(msg, e) { }
    }
}
