using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.View
{
    class TextMenu
    {
        private IDictionary<string, Command> commands;

        public TextMenu()
        {
            commands = new Dictionary<string, Command>();
        }

        public void AddCommand(Command c)
        {
            commands.Add(c.GetKey(), c);
        }

        public void PrintMenu()
        {
            foreach (Command cmd in commands.Values)
            {
                string line = string.Format("%4s. %s", cmd.GetKey(), cmd.GetDescription());
                Console.WriteLine(line);
            }
        }

        public void Show()
        {
            while (true)
            {
                PrintMenu();
                Console.Write("Your option: ");
                string key = Console.ReadLine();
                if (commands.ContainsKey(key) == false)
                {
                    Console.WriteLine("Wrong command!");
                    continue;
                }
                Command cmd = commands[key];
                cmd.Execute();
            }
        }
    }
}
