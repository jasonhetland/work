//Jason Hetland
//1324246
//CMPS12B
//8/3/14
//Simulation.java
//implement a Queue ADT in Java based on a linked list data structure. You will 
//use your Queue ADT to simulate a set of jobs to be performed by a set of 
//processors, where there are more jobs than processors, and therefore some jobs may have to wait in a 
//queue. 

import java.io.*;
import java.util.Scanner;

public class Simulation{
   public static Job getJob(Scanner in){
    String[] s = in.nextLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int d = Integer.parseInt(s[1]);
    return new Job(a, d);
   }
   public static void main(String[] args) throws IOException{
      //  check command line arguments
      if(args.length < 1){
         System.out.println("Usage: Simulation input_file ");
         System.exit(1);
      }

      //  open files for reading & writing
      PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
      PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));

      //  read in m jobs from input file
      Scanner in = new Scanner(new File(args[0]));
      String fileName = args[0];
      int m = in.nextInt();
      in.skip("\n");
      int time = 0;
      Queue backup = new Queue();
      Queue storage = new Queue();
      for ( int i = 0; i< m; i++){
         Job J = getJob(in);
         backup.enqueue(J);
         storage.enqueue(J);
      }

      // run simulation with n processors for n=1 to n=m-1
      report.println("Report file: " + args[0] + ".rpt");
      report.println(m + " Jobs:");
      report.println(storage);
      report.println();
      report.println("***********************************************************");

      // trace file:
      trace.println("Trace file: " + args[0] + ".trc");
      trace.println(m + " Jobs:");
      trace.println(storage);
      trace.println();

      for(int n = 1; n < m; n++){
      trace.println("*****************************");
         if(n == 1){
            trace.println("1 processor:");
         }else{
            trace.println(n + " processors:");
           trace.println("*****************************");
      int c = 0;
      int[] wait = new int[m];
      time = 0;
      boolean emptyP = true;
      boolean flagship = false;

      // declare & intialize an array of n processor Queues &
      // any necessary storage Queues
      Queue[] processorQ = new Queue[n];
       for(int y = 0; y<processorQ.length;y++){
         processorQ[y] = new Queue();
      }
      Queue dumpQ = new Queue();

      // while unprocessed jobs remain
      // determine the time of the next arrival or finish event
      // and update time
      trace.println("time="+time);
      trace.println("0: "+storage);
      for(int vag = 0; vag < n; vag++){
         trace.println((vag+1) +": "+ processorQ[vag]);
      }
      trace.println();
      while(!storage.isEmpty() || !emptyP){
         for( int t = 0; t < processorQ.length; t++){
            if(!processorQ[t].isEmpty() && ((Job)(processorQ[t].peek())).getFinish() == -1){
               ((Job)(processorQ[t].peek())).computeFinishTime(time);
            }
         }
         if(flagship){
            trace.println("time="+time);
            trace.println("0: "+storage+dumpQ);
            for(int god = 0; god < n; god++){
               trace.println((god+1)+": "+ processorQ[god]);
            }
            trace.println();
            flagship = false;
         }
         time++;
         //complete all processes finishing now
         for(int a = 0; a < processorQ.length; a++){
            if(!processorQ[a].isEmpty()){
               if(((Job)(processorQ[a].peek())).getFinish() == time){
                  wait[c++] = ((Job)(processorQ[a].peek())).getWaitTime();
                  dumpQ.enqueue(((Job)(processorQ[a].dequeue())));
                  flagship = true;
               }
            }
         }
       // if there are any jobs arriving now, assign them to a processor
         while(!storage.isEmpty() && ((Job)(storage.peek())).getArrival() == time){
            int smallest = 1000;
            int smallestIndex = 0;
            for(int b = 0; b < processorQ.length; b++){
               if(processorQ[b].length() < smallest){
                  smallest = processorQ[b].length();
                  smallestIndex = b;
               }
            }
            processorQ[smallestIndex].enqueue(storage.dequeue());
            flagship = true;
         }
         emptyP = true;
         for(int z = 0; z< processorQ.length; z++){
            if(!processorQ[z].isEmpty()){
               emptyP = false;
               break;
            }
         }
      // end loop
      }
      trace.println("time="+time);
      trace.println("0: "+storage+ dumpQ);
      for(int r = 0; r < n; r++){
         trace.println((r+1)+": "+processorQ[r]);
      }

      // Compute the total wait, max wait, & avg wait for
      // all Jobs, then reset finish times
      double max = 0;
      double total = 0;
      double average = 0;
      for(int index = 0; index < wait.length; index++){
         total += wait[index];
      }
      for(int coc = 0; coc < wait.length; coc++){
         if(wait[coc] > max){
            max = wait[coc];
         }
      }
      average = total/wait.length;
      if(n == 1){
         report.printf("1 processor: totalWait=%.0f, maxWait=%.0f, averageWait=%.2f\n",total, max, average);
      }else report.printf("%d processors: totalWait=%.0f, maxWait=%.0f, averageWait=%.2f\n",n, total, max, average);

      System.out.println(dumpQ);
       for(int o = 0; o < backup.length(); o++){
         backup.enqueue(((Job)(backup.peek())));
         ((Job)(backup.peek())).resetFinishTime();
         storage.enqueue(((Job)(backup.dequeue())));
      }
      dumpQ.dequeueAll();

      // end loop
   }
   // close input & output files
   in.close();
   trace.close();
   report.close();
  }
 }
}

