using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.DataTypes
{
    interface MyIList<T>
    {
        void Enqueue(T e);
        T Dequeue();
        bool IsEmpty();
        string ToString();
    }
}
