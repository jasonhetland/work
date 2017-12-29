//Jason Hetland
//1324246
//7/7/14
//CMPS012m

//-----------------------------------------------------------------------------
// CommandLineArguments.java
//-----------------------------------------------------------------------------

public class CommandLineArguments{
   public static void main(String[] args){
      int n = args.length;
      System.out.println("args.length = " + n);
      for(int i=0; i<n; i++) System.out.println(args[i]);
   }
}

