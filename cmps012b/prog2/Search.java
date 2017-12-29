//Jason Hetland
//1324246
//CMPS12b
//he purpose of this program is to determine whether or not string values 
//exist in a string

import java.io.*;
import java.util.Scanner;

public class Search{
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		Scanner wordCount = null;
		if(args.length < 1){
			System.err.println("Usage: Search Filename Word1 Word2 Etc.");
			System.exit(1);
		}
		
		int lineCount = 0;
		in = new Scanner(System.in);
		// delimiter method
        String delim = "[ \\t\\n]+";  
      // same as: String delim = "\\s+" which represents
      // one or more white-space characters
      
      String line =[args];
             
	 String[] token = (line.trim()+" ").split(delim);
      // trim() deletes leading and trailing white space.  Without it a line
      // with one or more tokens that begins with white space will not tokenize
      // properly.  +" " puts a single trailing space at the end of line. 
      // split() will not tokenize an empty line correctly without it.
      
      for( String s : token ) System.out.println("\""+s+"\"");
      System.out.println("number of tokens = "+token.length);
		wordCount = new Scanner(new File(args[0]));

		while(wordCount.hasNextLine()){
			wordCount.nextLine();
			lineCount++;
		}
		in.close();

		String[] word = new String[lineCount];
		int[] lineNumber = new int[lineCount];
		in = new Scanner(new File(args[0]));
		for (int i=0; i < lineCount; i++) {
			word[i] = in.next();
			lineNumber[i] = i+1;
		}
		mergeSort(word, lineNumber, 0, word.length-1);
		for (int j=1; j < args.length; j++) {
			String target = args[j];
			int index = binarySearch(word, lineNumber, 0,word.length-1, target);
			if (index==-1) {
				System.out.println(target + " not found.");
			}
			else {
				System.out.println(target + " found on line " + index);
			}
		}
	}

	
	public static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
		int mid;
		if (p < r) {
                	mid = (p+r)/2;
			mergeSort(word, lineNumber, p, r);
			mergeSort(word, lineNumber, mid+1, r);
			merge(word, lineNumber, p, mid, r);
		}
	}
	public static void merge(String[] word, int[] lineNumber, int p,int q, int r) {
	//pre A[p..q] and A[q+1..r] are sorted
		int n1 = q-p+1;
		int n2 = r-q;
		String[] L = new String[n1];
		String[] R = new String[n2];
		int[] lNum = new int[n1];
		int[] rNum = new int[n2];
		int i=0, j=0, k=0;

		for(i=0; i<n1; i++) {
			L[i] = word[p+i];
			lNum[i] = lineNumber[p+i];
		}
		for(j=0; j<n2; j++) {
			R[j] = word[q+j+1];
			rNum[j] = lineNumber[q+j+1];
		}
		for(k=p; k<=r; k++)  {
			if(i<n1 && j<n2) {
				if( L[i].compareTo(R[j]) <0) {
					word[k] = L[i];
					lineNumber[k] = lNum[i];
					i++;
				}else{
					word[k] = R[j];
					lineNumber[k] = rNum[j];
					j++;
				}
			}else if (i<n1) {
				word[k] = L[i];
				lineNumber[k] = lNum[i];
				i++;
			}else{ //j<n2
				word[k] = R[j];
				lineNumber[k] = rNum[j];
				j++;
			}
		}	
	}
	public static int binarySearch(String[] A, int[] B, int p, int r, String target) {
		//pre: Array[p...r] is sorted
		int q = (p+r)/2;
		if (target.compareTo(A[q])==0) {
			return B[q];
		}
		else if (r==p && target.compareTo(A[q]) < 0) {
			return -1;
		}
		else if (target.compareTo(A[q]) < 0) {
			return binarySearch(A, B, p, q-1, target);
		}
		else {
			return binarySearch(A, B, q+1, r, target);
		}
	}
}

