using System;


namespace clj_in_csharp
{
    class Program
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

            WriteResults(string.Format("one.ba(0, {0}, {1}) => {2}", h, ab, one.ba(0, h, ab)));
            WriteResults(string.Format("one.ba2({0}, {1}) => {2}", h, ab, one.ba2(h, ab)));
            WriteResults(string.Format("one.standing(\"\", \"sfg\") => {0}", one.standings("", "sfg")));                 
        }
    }
}
