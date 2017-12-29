import java.io.*; 
import java.util.Scanner;


public class Test {
	public static String stringReverse(String s, int n){
		
	int i=0;
	String Reverse="";
        if(n>0)
        Reverse= s.charAt(n-1)+stringReverse(s,n-1);
        return Reverse;
 }
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
        
        String[] word=line.split(" ");
        for(int i=0;i< word.length; i++){
        	String firstWord=word[i];
        	int l=firstWord.length();
        stringReverse(firstWord,l);
       	
	 }
        
  
 }
  
 in.close();
 out.close();
 }
}
