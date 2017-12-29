//Jason Hetland
//1324246
//7/20/14
//CMPS12B
//To work with Linked Lists and ADT. To work with ADT operations like 
//delete and insert.To implement a Dictionary ADT based on the linked list 
//data structure


public class Dictionary implements DictionaryInterface {

	//private inner Node class
	private class Node { 
		String key;
		String value;
		Node next;

                Node(String x, String y) {
			key = x;
			value = y;
			next = null;
		}
     }
	// Fields for dictionary class

        private Node head;     // reference to first Node in List
	private int numItems;  // number of items in this Dictionary

        // Dictionary() constructor for the Dictionary class

        public Dictionary(){
		head = null; 
		numItems = 0;

}
//Returns reference to the Node containing its matching argument
//if no such Node exists null is returned

        private Node findKey(String key) {
		for(Node N = head; N != null; N = N.next) {
			if (N.key.equals(key)) {
				return N;
		}
	}
		return null;

        }

//Returns true if the Dictionary contains no pairs and returns
//false otherwise.

        public boolean isEmpty() {
		return(numItems == 0);
	}
	//Returns the number of (key, value) pairs found in Dictionary

        public int size() {
		return numItems;
	}

//Sees if  Dictionary contains a pair whose key
//field matches the argument key, then returns the
//value field. If no such pair exsists null is returned.

        public String lookup(String key) {
		Node N = findKey(key);
		if (N == null) {
			return null;
		}else {
			return N.value;
		} 
	}

//If the pair exists in Dictionary, KeyCollisionException is thrown.
//The pair will be added.

        public void insert(String key, String value) throws KeyCollisionException {
		//If Dictionary is empty, the Stiring key and value get added to the top
                if (isEmpty()) {
		Node N = new Node(key, value);
		N.next = head;
		head = N;
		}else {

                        if (lookup(key) != null) {throw new KeyCollisionException("Dictionary Error:Cannot insert duplicate keys.");
			}
			Node N = head;

                        //move pointer to ennd
				while (N.next != null) {
				N = N.next;

                        }
			//insert new Node to end of Dictionary
			N.next = new Node(key, value);

                }
			numItems++;
	}

//If pair is currently in Dictionary, then it will be removed.
//If no such pair currently exists in Dictionary, throw erorr message 

        public void delete(String key) throws KeyNotFoundException {
		if (lookup(key) != null) { //key does exist
			Node N = head;
			if (N == findKey(key)) { //delete first Node
				head = findKey(key).next;

                        }else if (N != findKey(key)) {

                                while (N.next != findKey(key)) {
					N = N.next;
				}
				N.next = findKey(key).next;

                        }
				numItems--;

           }		else {

                        throw new KeyNotFoundException("Dictionary Error: Cannot delete non-existent key.");

	}

}

//Resets the Dictionary

        public void makeEmpty() {
		head = null;
		numItems = 0;
	}

//Returns a String to its current state of the Dictionary

        public String toString() {
		String s = "";
		for(Node N = head; N != null; N = N.next) {
			s += N.key + " " + N.value + "\n";

                }
			return s;

        }
}
