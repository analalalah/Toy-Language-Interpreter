using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.View
{
    abstract class Command
    {
        private string key;
        private string description;

        public Command(string key, string description)
        {
            this.key = key;
            this.description = description;
        }

        public abstract void Execute();

        public string GetKey()
        {
            return key;
        }

        public string GetDescription()
        {
            return description;
        }
    }
}
