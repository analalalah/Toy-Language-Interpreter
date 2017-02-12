using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.DataTypes
{
    class MyList<T> : MyIList<T>
    {
        private List<T> list;

        public MyList()
        {
            this.list = new List<T>();
        }

        public void Enqueue(T e)
        {
            this.list.Add(e);
        }

        public T Dequeue()
        {
            if (list.Count == 0)
                throw new EmptyMyListException("Exception. MyList is empty.");
            T ret = list.ElementAt(0);
            list.RemoveAt(0);
            return ret;
        }

        public bool IsEmpty()
        {
            return list.Count == 0;
        }

        public override string ToString()
        {
            string str = "";

            foreach (T t in list)
            {
                str += t.ToString();
                str += "\n";
            }
            return str;
        }
    }
}
