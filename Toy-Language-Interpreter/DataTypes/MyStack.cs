using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.DataTypes
{
    class MyStack<T> : MyIStack<T>
    {
        private Stack<T> stack;

        public MyStack()
        {
            stack = new Stack<T>();
        }

        public T Pop()
        {
            if (stack.Count == 0)
                throw new EmptyMyStackException("Exception. MyStack is empty.");
            return stack.Pop();
        }

        public void Push(T value)
        {
            stack.Push(value);
        }

        public bool IsEmpty()
        {
            return stack.Count == 0;
        }

        public override string ToString()
        {
            T[] arr = stack.ToArray();
            string str = "";

            for (int i = arr.Length - 1; i > -1; i--)
            {
                str += arr[i].ToString();
                str += "\n";
            }
            return str;
        }
    }
}
