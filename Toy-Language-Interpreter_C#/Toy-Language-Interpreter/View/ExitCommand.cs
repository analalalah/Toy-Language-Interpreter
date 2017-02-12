using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.View
{
    class ExitCommand : Command
    {
        public ExitCommand(string key, string description) : base(key, description)
        {
            ;
        }

        public override void Execute()
        {
            System.Environment.Exit(0);
        }
    }
}
