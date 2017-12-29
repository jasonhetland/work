//Jason Hetland
//1324246
//7/7/14
//cmps 012m
//pratice using command line arguments,file input-output, and manipulation 
//of strings in java

//-----------------------------------------------------------------------------
// FileCopy.java
// Illustrates file IO
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class FileCopy{
   public static void main(String[] args) throws IOException{
      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      int n;

      if(args.length < 2){
         System.out.println("Usage: FileCopy infile outfile");
         System.exit(1);
      }

      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      in.useDelimiter("\n");
      while( in.hasNext() ){
         line = in.next();
         out.println( line );
      }

      in.close();
      out.close();
   }
}
