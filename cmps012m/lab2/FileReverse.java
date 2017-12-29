//Jason Hetland
//1324246
//CMPS 012m
//7/7/14
//TO work with command lines to transfer files

import java.io.*; 
import java.util.Scanner; //importing scanner


public class FileReverse {
        public static String stringReverse(String s, int n){
                if(n>0) {
                  return s.substring(n-1, n)+ stringReverse(s, n-1);
                }
                return ""; //allows the returned value to print to the display
        }

        public static void main(String[] args) throws IOException{
                //initialize 
                Scanner in = null;
                PrintWriter out = null;
                String line = null;
                String[] token = null;
                int n;

                //if the user enters invalid information
                if(args.length < 2){
                        //Print a usage message to user
                        System.out.println("Usage: FileIO infile outfile");
                        System.exit(1);
                //exit loop once the user enters correct  arguments
                }

                in = new Scanner(new File(args[0]));
                out = new PrintWriter(new FileWriter(args[1]));

                in.useDelimiter("\n"); //used for newline as whitespace
                while(in.hasNext()){
                        line = in.next() + ""; //add space to split wordson blank lines
                        token = line.split("\\s+"); 
			//split line 

                        for(int i=0; i<token.length; i++){
                                String firstWord = token[i];
                                int length = firstWord.length();
                                out.print(stringReverse(firstWord,length));
                                out.println();
                        }
                }
                in.close();
                out.close();
        }

}
