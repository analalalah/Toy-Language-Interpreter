using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.MyException
{
    class ToyLanguageRuntimeError : ApplicationException
    {
        public ToyLanguageRuntimeError() { }
        public ToyLanguageRuntimeError(string msg) : base(msg) { }
        public ToyLanguageRuntimeError(string msg, Exception e) : base(msg, e) { }
    }
}
