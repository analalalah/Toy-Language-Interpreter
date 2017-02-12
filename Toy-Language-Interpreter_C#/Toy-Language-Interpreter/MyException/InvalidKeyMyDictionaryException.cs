using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class InvalidKeyMyDictionaryException : ApplicationException
    {
        public InvalidKeyMyDictionaryException() { }
        public InvalidKeyMyDictionaryException(string msg) : base(msg) { }
        public InvalidKeyMyDictionaryException(string msg, Exception e) : base(msg, e) { }
    }
}
