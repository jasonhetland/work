//Jason Hetland
//1324246
//CMPS 12B lab6
//8/5/14
//The goal of this assignment is to gain experience with an advanced
//feature in Java called Generics

public class List<T> implements ListInterface<T>{

	// private inner Node class
	   public class Node <T> {
	      private T item;
	      private Node<T> next;

	     public Node(T newItem){
	    	 item = newItem;
	    	 next = null;
	     }
	     public Node(T newItem,Node<T> nextNode){
	    	 item = newItem;
	    	 next = nextNode;
	     }
	   public void setItem(T newItem){
		   item = newItem;
	   }
           public T getItem(){
    	           return item;
       }
       public void setNext(Node<T> nextNode){
    	   next = nextNode;
       }
       public Node<T> getNext(){
    	   return next;
       }
       
	   // Fields for List class
	   private Node<T> head;     // reference to first Node in List
	   private int numItems;  // number of items in this List

	   // List()
	   // constructor for the List class
	   public List(){
	      
	      head = null;
	      numItems = 0;
	   }


	   // private helper function

	   // find()
	   // returns a reference to the Node at position index in this List
	   private Node<T> find(int index){
	      Node<T> N = head;
	      for(int i=1; i<index; i++){ N = N.getNext();
	   }
	      return N;
	   }


	   // ADT operations 

	   // isEmpty()
	   // pre: none
	   // post: returns true if this List is empty, false otherwise
	   public boolean isEmpty(){
	      return(numItems == 0);
	   }

	   // size()
	   // pre: none
	   // post: returns the number of elements in this IntegerList
	   public int size() {
	      return numItems;
	   }

	   // get()
	   // pre: 1 <= index <= size()
	   // post: returns item at position index in this List
	   public T get(int index) throws ListIndexOutOfBoundsException {
	      
	      if( index>=1 && index<=numItems ){
	        
	      Node<T> N = find(index);
	      T dataItem = N.getItem();
	      return dataItem;
	   }
	      else{ throw new ListIndexOutofBoundsException("error");
	      
	      }
	   }
	   // add()
	   // inserts newItem into this List at position index
	   // pre: 1 <= index <= size()+1
	   // post: !isEmpty(), items to the right of newItem are renumbered
	   public void add(int index, T newItem) 
	      throws ListIndexOutOfBoundsException{
	      
		   if( index>=1 && index<=numItems+1 ){
		        if(index==1){
		          Node<T> newNode = new Node<T>(item,head);
		          head = newNode;
		        }
		        else{
		          Node<T> prev = find(index-1);
		          Node<T> newNode = new Node<T>(item, prev.getNext());
		          prev.setNext(newNode);
		        }
		        numItems++;
		   }
		      else throw new ListIndexOutOfBoundsException("Error");
	   }
	   }
	   // remove()
	   // deletes item at position index from this List
	   // pre: 1 <= index <= size()
	   // post: items to the right of deleted item are renumbered
	   public void remove(int index)
	      throws ListIndexOutOfBoundsException{
	      if(index >= 1 && index <= numItems){
	    	  if(index==1){
	    		  head = head.getNext();
	    	  }
	    	  else{
	    		  Node<T> prev = find(index-1);
	    		  Node<T> N = prev.getNext();
	    		  prev.setNext(N.getNext());
	    	  }
	         numItems--;
	      }
	      else{throw new ListIndexOutOfBoundsException("error");
	      }
	      }
	   // removeAll()
	   // pre: none
	   // post: isEmpty()
	   public void removeAll(){
	      head = null;
	      numItems = 0;
	   }

	   // toString()
	   // pre: none
	   // post:  prints current state to stdout
	   // Overrides Object's toString() method
	   public String toString(){
	      String s = "";
	      for(Node<T> N=head; N!=null; N=N.next){
	         s += N.item + " ";
	      }
	      return s;
	   }

	   @SuppressWarnings("unchecked")
	public boolean equals(Object rhs){
	      boolean eq = false;
	      List<T> R = null;
	      Node<T> N = null;
	      Node<T> M = null;

	      if(rhs instanceof List){
	         R = (List<T>)rhs;
	         eq = ( this.numItems == R.numItems );

	         N = this.head;
	         M = R.head;
	         while(eq && N!=null){
	            eq = (N.item == M.item);
	            N = N.next;
	            M = M.next;
	         }
	      }
	      return eq;
	   }

