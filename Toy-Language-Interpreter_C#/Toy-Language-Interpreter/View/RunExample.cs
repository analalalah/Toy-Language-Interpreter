using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.Controller;

namespace Toy_Language_Interpreter.View
{
    class RunExample : Command
    {
        private MyController ctrl;

        public RunExample(string key, string description, MyController ctrl) : base(key, description)
        {
            this.ctrl = ctrl;
        }

        public override void Execute()
        {
            try
            {
                ctrl.AllStep();
            }
            catch (Exception e) // MyStatementExecutionException
            {
                Console.WriteLine(e.ToString());
                // todo: treat this exception
            }
        }
    }
}
