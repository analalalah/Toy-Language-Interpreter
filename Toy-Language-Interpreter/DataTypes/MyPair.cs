using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.DataTypes
{
    class MyPair<F, S>
    {
        private F first;
        private S second;

        public MyPair(F first, S second)
        {
            this.first = first;
            this.second = second;
        }

        public F GetFirst()
        {
            return first;
        }

        public S GetSecond()
        {
            return second;
        }

        public void SetFirst(F first)
        {
            this.first = first;
        }

        public void SetSecond(S second)
        {
            this.second = second;
        }

        public override string ToString()
        {
            return first.ToString();
        }
    }
}
