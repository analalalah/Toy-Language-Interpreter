using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.DataTypes;
using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.Model.Expression
{
    class VariableExpression : AExpression
    {
        private string id;

        public VariableExpression(string id)
        {
            this.id = id;
        }

        public override int Evaluate(MyIDictionary<string, int> symTab)
        {
            try
            {
                return symTab.Get(id);
            }
            catch (InvalidKeyMyDictionaryException)
            {
                throw new UndeclaredVariableException("Exception: Undeclared variable: " + id + ".");
            }
        }

        public override string ToString()
        {
            return id;
        }
    }
}
