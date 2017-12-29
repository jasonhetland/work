//Jason Hetland
//1324246
//CMPS 12B lab6
//8/5/14
//The goal of this assignment is to gain experience with an advanced 
//feature in Java called Generics


 public class List<T> implements ListInterface<T> {
	   
	   private static final int MAX_LIST = 50;
           private int physicalSize;
           private T[] items;
	   private int numItems;

	   // IntegerList()
	   // constructor for the IntegerList class
	   @SuppressWarnings("unchecked")
	public List(){
	      physicalSize = MAX_LIST;
	      items = (T[]) new Object[physicalSize];
	      numItems = 0;
	   }
	// doubleItemArray()
	   // doubles the physical size of the underlying array item[]
	   private void doubleItemArray(){
	      physicalSize *=2;
	      T[] newArray = new T[physicalSize];
	      for(int i=0; i<numItems; i++) newArray[i] = items[i];
	      items = newArray;
	   }

	   // private helper function

	   // find()
	   // returns a reference to the Node at position index in this IntegerList
	


	   // ADT operations 
	   // isEmpty()
	   // pre: none
	   // post: returns true if this IntgerList is empty, false otherwise
	   public boolean isEmpty(){
	      return numItems == 0;
	   }

	   // size()
	   // pre: none
	   // post: returns the number of elements in this IntegerList
	   public int size() {
	      return numItems;
	   }

	   // get()
	   // pre: 1 <= index <= size()
	   // post: returns item at position index in this IntegerList
	   public T get(int index) throws ListIndexOutOfBoundsException {
	       if(index>= 1 && index <= numItems){
	    	   		return items[translate(index)];
	       }
	       else{ throw new ListIndexOutOfBoundsException("Out of range");
	       }
	   }

	   // add()
	   // inserts newItem into this IntegerList at position index
	   // pre: 1 <= index <= size()+1
	   // post: !isEmpty(), items to the right of newItem are renumbered
	   public void add(int index, T newItem) 
	      throws ListIndexOutOfBoundsException{
	      
	      if( index<1 || index>(numItems+1) ) 
	         if(numItems == MAX_LIST){
	        	 throw new ListException("List is full");
	         }
	    	 if(index >=1 && index <= numItems + 1){
	    		 makeroom(index);
	    		 items[translate(index)]= newItem;
	    		 numItems++;
	    	 }
	      }
	    private void makeroom(int index){
	    	for(int pos= numItems;pos >= index; pos--){
	    		 items[translate(pos+1)] = items[translate(pos)];
	    	}
	    }
	   
	   private int translate(int position){
		      return position -1;
	   }
	   // remove()
	   // deletes item at position index from this IntegerList
	   // pre: 1 <= index <= size()
	   // post: items to the right of deleted item are renumbered
	   public void remove(int index)
			   throws ListIndexOutOfBoundsException{
	         
		      if( index>= 1 && index<= numItems ){
		   
		      for(int i=index+1; i<=numItems; i++){
		         items[translate(i-1)] = items[translate(i)];
		      }
		      numItems--;
		   }
		      else{
		    	  throw new ListIndexOutOfBoundsException(
					"List Error: remove() called on invalid index");
		      }
	   }
	   // removeAll()
	   // pre: none
	   // post: isEmpty()
	   public void removeAll(){
		  items = (T[]) new Object[MAX_LIST];
	      numItems = 0;
	   }

	   // toString()
	   // pre: none
	   // post:  prints current state to stdout
	   // Overrides Object's toString() method
	   public String toString(){
		      int i;
		      String s = "";

		      for(i=0; i<numItems; i++) s += items[i] + " ";
		      return s;
		   }
	   

	  
	   @SuppressWarnings("unchecked")
	public boolean equals(Object rhs){
		    int i = 0;
		    boolean eq = false;
		    List<T> R = null;

		      if(rhs instanceof List){
		         R = (List<T>)rhs;
		         eq = (this.numItems == R.numItems);
		         while(eq && i<numItems){
		            eq = (this.items[i] == R.items[i]);
		            i++;
		         }
		      }
		      return eq;
		   }
	   }


