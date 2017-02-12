using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;

namespace Toy_Language_Interpreter.Model.Expression
{
    abstract class AExpression
    {
        public abstract int Evaluate(MyIDictionary<string, int> symTab);
        public override abstract string ToString();
    }
}
