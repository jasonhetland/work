//Jason Hetland
//CMPS012b
//QueueTest.java

public class QueueTest{
   
    public static void main(String[] args){
      Queue A = new Queue();
     // A.enqueue();
      System.out.println(A.peek());
      A.dequeue(); A.dequeue(); A.dequeue();
      System.out.println(A.peek());
      System.out.println(A);
      Queue B = new Queue();
     // System.out.println(A.isEmpty());
      System.out.println(B.isEmpty());
      System.out.println(A);
      System.out.println(B);
      A.dequeueAll();
      System.out.println(A);
   }
}
