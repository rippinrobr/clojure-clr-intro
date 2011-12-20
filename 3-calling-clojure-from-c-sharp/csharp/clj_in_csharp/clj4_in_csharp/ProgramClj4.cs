using System;

namespace clj4_in_csharp
{
    class ProgramClj4
    {
        static void WriteResults(string res)
        {
            Console.WriteLine(res);
            Console.WriteLine();
        }

        static void Main(string[] args)
        {
            const int h = 10;
            const int ab = 20;

            var defaultColor = Console.ForegroundColor;
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine("The results of each call will be preceded by a line that ");
            Console.WriteLine("prints out the values of the parameters as the clojure fn ");
            Console.WriteLine("sees them.");
            Console.WriteLine();
            Console.ForegroundColor = defaultColor;

            WriteResults(string.Format("one.ba(0, {0}, {1}) => {2}", h, ab, one14.ba(h, ab)));
            WriteResults(string.Format("one.standing(\"\", \"sfg\") => {0}", one14.standings("sfg")));
        }
    }
}
