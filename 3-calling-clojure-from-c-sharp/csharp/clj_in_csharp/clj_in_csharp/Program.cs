using System;


namespace clj_in_csharp
{
    class Program
    {
        static void WriteResults(string doc, string res)
        {
            Console.WriteLine(doc);
            Console.WriteLine(res);
            Console.WriteLine();    
        }

        static void CallOneClj()
        {
            const int h = 10;
            const int ab = 20;

            WriteResults(string.Format("Calling (ba {0} {1}).  ba will echo the params then do the division", h, ab),
                         string.Format("(ba {0} {1}) = {2}", h, ab, one.ba(0, h, ab)));

            WriteResults("Calling (standing \"\" \"sfg\").  standing will echo the params then do the 'work'",
                         string.Format("(standing \"\" \"sfg\") = {0}", one.standings("", "sfg")));                
        }

        

        static void Main(string[] args)
        {
            CallOneClj();
        }
    }
}
