//Jason Hetland
//1324246
//CMPS 12B PA5
//8/11/14
//Implentation file based on a hash table data structure. This has the 
//add, delete functions for a dictionary using chaining to deal with 
//collsions of the same index

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


const int tableSize=101;

typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;
// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// ListObj
typedef struct ListObj{
  Node head;
} ListObj;

// List
typedef ListObj* List;

// Constructor of the List type
List newList() {
  List L = malloc(sizeof(ListObj));
  L->head = NULL;
  return L;
}

typedef struct DictionaryObj{
   List table;
   int numItems;
} DictionaryObj;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void) {
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->table = calloc(tableSize, sizeof(ListObj));
   D->numItems = 0;
   return D;
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD) {
   if( pD!=NULL && *pD!=NULL ){
      if( !isEmpty(*pD) ) makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if (shift == 0)
	return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
   unsigned int result = 0xBAE86554;
   while (*input) { 
      result ^= *input++;
      result = rotate_left(result, 5);
  }
   return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

//finds the a certain key in a list within a hash table by using the
//hash function
Node findKey(Dictionary D, char* searchKey){
  //This Node doesn't contain the key
  for (int i = 0; i < tableSize; i++) {
    for (Node N = D->table[i].head; N != NULL; N = N->next) {
      if (strcmp(searchKey, N->key) == 0) return N;
    }
  }
  // if key was not found
  return NULL;
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
    if(D->numItems == 0){
      return(1);
   }
   return 0;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
   return (D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems == 0 ){
      fprintf(stderr, "Dictionary Error: calling lookup() on empty Dictionary list\n");
      exit(EXIT_FAILURE);
   }
   if (findKey(D, k) != NULL) {
      return findKey(D, k)->value;
  }
   else return NULL;
}

// insert()
// inserts new (key, value) pair into Dictionary
// pre: lookup(D, k) == NULL
void insert(Dictionary D, char* k, char* v) {
  if (D != NULL) {
    if (D->table[hash(k)].head == NULL) {
      Node N = newNode(k, v);
      N->next = D->table[hash(k)].head;
      D->table[hash(k)].head = N;
      D->numItems++;
    } else {
      if (lookup(D, k) == NULL) {
        Node N = D->table[hash(k)].head;
          while (N->next != NULL) { 
            N = N->next;
          }
          N->next = newNode(k, v); 
	  D->numItems++;
      }
      else {
        fprintf(stderr, "Dictionary Error KeyCollisionException: calling insert() on duplicate key\n");
        exit(EXIT_FAILURE);
      }
    }
  } else {
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k) {
  if (D != NULL) {
    if (lookup(D, k) != NULL) {
       for (int i = 0; i < tableSize; i++) {
         Node temp;
         Node N = D->table[i].head;
       if (N == findKey(D, k)) {
         if (D->table[i].head->next == NULL) {
           freeNode(&D->table[i].head);
           D->numItems--;
           break;
         }
          else {
            N = D->table[i].head;
            D->table[i].head = D->table[i].head->next;
            freeNode(&N);
            D->numItems--;
            break;
          }
        }
        else if (N != findKey(D, k)) {
          while (N != findKey(D, k) && N != NULL) {
            temp = N;
            N = N->next;
          }
          if (N != NULL) {
            temp->next = N->next;
            freeNode(&N);
            D->numItems--;
            break;
         }
       }
     }
   } 
    else {
      fprintf(stderr, "Dictionary Error KeyNotFoundException: deleting on non existant key\n");
      exit(EXIT_FAILURE);
    }
  }
  else {
    fprintf(stderr, "Dictionary Error: deleting on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
}

// makeEmpty()
// Clears Dictionary
// pre: none
void makeEmpty(Dictionary D) {
   for (int i = 0; i < tableSize; i++) {
    Node N = D->table[i].head;
    Node temp;
    while (N != NULL) {
      temp = N;
      delete(D, temp->key);
      N = N->next;
    }
  }
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D) {
  if (D == NULL) {
    fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
    exit(EXIT_FAILURE);
  }
  for(int i = 0 ; i < tableSize; i++){
  
  Node N = D->table[i].head;
   while (N != NULL) {
    fprintf(out, "%s %s\n", N->key, N->value);
     N = N->next;
  }
 }
}

