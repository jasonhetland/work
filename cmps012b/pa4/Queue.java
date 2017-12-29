//Jason Hetland
//1324246
//CMPS12B
//8/3/14
//Queue.java

public class Queue implements QueueInterface {

  private class Node {
    Object item;
    Node next;

    Node(Object x){
      item = x;
      next = null;
    }
  }
  private Node front;
  private Node back;
  private int length;

  public Queue() {
    front = null;
    back = null;
    length = 0;
  }
  public boolean isEmpty(){
    return (length == 0);
  }
  public int length(){
    return length;
  }
  public void enqueue(Object newItem){
    if(isEmpty()){
      Node N = new Node(newItem);
      N.next = front;
      front = N;
    }else{
      Node N = front;
      while(N.next != null){
        N = N.next;
      }
      N.next = new Node(newItem);
    }
    length++;
  }
  public Object dequeue() throws QueueEmptyException{
    Node N;
    if(isEmpty()){
      throw new QueueEmptyException("Queue Error: peek() cannot be called when the queue is empty");
    }else{
      N = front;
      front = front.next;
    }
    length--;
    return N.item;
  }
  public Object peek() throws QueueEmptyException {
    if(isEmpty()){
      throw new QueueEmptyException("Queue Error: peek() cannot be called when the queue is empty");
    }else{
      return front.item;
    }
  }
  public void dequeueAll() throws QueueEmptyException{
    front = null;
    back = null;
    length = 0;
  }
  public String toString(){
    String s = "";
    Node N = front;
    while(N != null){
      s += N.item.toString() + " ";
      N = N.next;
    }
    return s;
  }
}
                                                                        
