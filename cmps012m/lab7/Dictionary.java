//Jason Hetland
//1324246
//Lab7
//8/13/14
//Implement a Dictionary using Binary Search Tree. The private and
//public functions

public class Dictionary implements DictionaryInterface{
   
   // private inner Node class
   private class Node {
      String key;
      String value;
      Node right;
      Node left;

      //constructor for private Node type
      Node(String x, String y){
         key = x;
         value = y;
         right = null;
         left = null;
      }
   }
    
   // Fields for the Dictionary class
   private Node root;
   private int numItems;

   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary(){
      root = null;
      numItems = 0;
   }
   
// findParent()
// returns the parent of N in the subtree rooted at R, or returns null 
// if N is equal to R. (pre: R != null)
Node findParent(Node N, Node R){
   Node P = null;
   if(N != R){
      P = R;
      while( P.left != N && P.right != N ){
         if(P.key.compareTo(N.key) > 0){
            P = P.left;
         } else {
            P = P.right;
         }
      }
   }
   return P;
}

// findLeftmost()
// returns the leftmost Node in the subtree rooted at R, or null if R is null.
Node findLeftmost(Node R){
   Node L = R;
   if( L != null ) for( ; L.left != null; L = L.left) ;
   return L;
}
//findKey()
// returns the Node containing key k in the subtree rooted at R, or returns
// null if no such node exists
Node findKey(Node N, String key){
   if(N == null || N.key.equals(key)){
      return N;
   }
   if(N.key.compareTo(key) > 0){
      return findKey(N.left, key);
   }else{ // N.key.compareTo(key)<0
      return findKey(N.right, key);
   }
}
// printInOrder()
// prints the (key, value) pairs belonging to the subtree rooted at R in order
// of increasing keys to file pointed to by out.
String printInOrder(Node R){
   String s = "";
   if( R != null ){
      s += printInOrder(R.left);
      s += (R.key + " " + R.value + "\n");
      s += printInOrder(R.right);
   }
   return s;
}

// deleteAll()
// deletes all Nodes in the subtree rooted at N.
void deleteAll(Node N){
   if( N != null ){
      deleteAll(N.left);
      deleteAll(N.right);
   }
}

// isEmpty()
// pre: none
// post: returns true if this Dictionary is empty, false otherwise
public boolean isEmpty(){
   return(numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
public int size(){
   return(numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns null if no 
// such value v exists.
// pre: none
public String lookup(String key){
   Node N;
   N = findKey(root, key);
   return ( N==null ? null : N.value );
}

// insert()
// inserts new (key,value) pair into Dictionary
// pre: lookup(D, k)==null
public void insert(String key, String value) throws KeyCollisionException{
   Node N, A, B;
   if(findKey(root, key) != null){
      throw new KeyCollisionException("Error: Cannot insert duplicate keys");
   }
   N = new Node(key, value);
   B = null;
   A = root;
   while(A != null){
      B = A;
      if(A.key.compareTo(key) > 0) A = A.left;
      else A = A.right;
   }
   if( B == null ) root = N;
   else if(B.key.compareTo(key) > 0) B.left = N;
   else B.right = N;
   numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=null
public void delete(String key) throws KeyNotFoundException{
   Node N, Q, S;
   N = findKey(root, key);
   if( N == null ){
      throw new KeyNotFoundException("Cannot delete non-existent key");
   }
   if( N.left == null && N.right == null ){
      if( N == root ){
         root = null;
      }else{
         Q = findParent(N, root);
         if( Q.right == N ) Q.right = null;
         else Q.left = null;
      }
   }else if( N.right == null ){
      if( N == root ){
         root = N.left;
      }else{
         Q = findParent(N, root);
         if( Q.right==N ) Q.right = N.left;
         else Q.left = N.left;
      }
   }else if(N.left == null){
      if(N == root){
         root = N.right;
      }else{
         Q = findParent(N, root);
         if( Q.right == N ) Q.right = N.right;
         else Q.left = N.right;
      }
   }else{
      S = findLeftmost(N.right);
      N.key = S.key;
      N.value = S.value;
      Q = findParent(S, N);
      if( Q.right==S ) Q.right = S.right;
      else Q.left = S.right;
   }
   numItems--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
public void makeEmpty(){
   deleteAll(root);
   root = null;
   numItems = 0;
}

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      return printInOrder(root);
   }
}
