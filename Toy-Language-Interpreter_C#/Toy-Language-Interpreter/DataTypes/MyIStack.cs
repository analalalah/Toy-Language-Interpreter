using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.DataTypes
{
    interface MyIStack<T>
    {
        T Pop();
        void Push(T value);
        bool IsEmpty();
        string ToString();
    }
}
