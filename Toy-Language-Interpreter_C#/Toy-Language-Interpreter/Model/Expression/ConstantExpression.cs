using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;

namespace Toy_Language_Interpreter.Model.Expression
{
    class ConstantExpression : AExpression
    {
        private int number;

        public ConstantExpression(int number)
        {
            this.number = number;
        }

        public override int Evaluate(MyIDictionary<string, int> symTab)
        {
            return number;
        }

        public override string ToString()
        {
            return "" + number;
        }

    }
}
