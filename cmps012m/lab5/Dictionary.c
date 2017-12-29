//Jason Hetland
//1324246
//CMPS12B
//Lab5
// to learn how to implement ADTs in C. To get familiar with typedef and
//struct commannds, header files, and information hiding, constructors.

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types 

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;
// Node
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

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// Dictionary
typedef struct DictionaryObj{
   Node head;
   int numItems;
} DictionaryObj;
 
//public functions
// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->head = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      if( !isEmpty(*pD) ) makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if(D->numItems == 0){
      return(1);
   }
   return 0;
}

int size(Dictionary D){
   return (D->numItems);

}
//finds the a certain key in a list within a hash table
Node findKey(Dictionary D, char* x){
   for(Node N = D->head; N != NULL ; N = N->next){
      int y = strcmp(N->key, x);
      if (y == 0){
         return(N);
      }       
   }
return (NULL);
}

// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* x, char* y){
   Node N;
         if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if(isEmpty(D)){
   N = newNode(x, y);
   N->next = D->head;
   D->head = N;
   } else {
      Node N = D->head;
      while(N->next != NULL) {
         N = N->next;
      }
      N->next = newNode(x,y);
   }
  D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
	if( D->numItems==0 ){
        fprintf(stderr, "Dictionary Error: calling delete() on empty Dictionary list\n");
        exit(EXIT_FAILURE);
   }
   if(lookup(D,k) != NULL){
      Node temp;
      Node N = D->head;
      if(N == findKey(D, k)) {
         D->head = findKey(D, k)->next;
         freeNode(&N);
         D->numItems--;
      } else if(N != findKey(D,k)) {
         while(N != findKey(D,k)) {
            temp = N;
            N = N->next;
         }
         temp->next = N->next;
         freeNode(&N);
         D->numItems--;
      }
       
          } else
            fprintf(stderr, "Dictionary Error KeyNotFoundException: delete() could not find the key you specified");
}
// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling lookup() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
      if( D->numItems==0 ){
      fprintf(stderr, "Dictionary Error: calling lookup() on empty Dictionary list\n");
      exit(EXIT_FAILURE);
   }
   Node N = findKey(D,k);
   if(N == NULL){
      return (NULL);
   } else {
      return(N->value);
   }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   Node temp;
   Node N = D->head;
   if( D==NULL ){
      fprintf(stderr,"Dictionary Error: calling MakeEmpty() on an empty Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems == 0 ){
      fprintf(stderr, "Dictionary Error: calling makeEmpty() on empty Dictionary list\n");
      exit(EXIT_FAILURE);
   }
   while(!isEmpty(D)){
      temp = N;
      delete(D, temp->key);
      N = N->next;
   }
}
// printDictionary()
// pre: none
// prints a text
 void printDictionary(FILE* out, Dictionary D) {
  if (D == NULL) {
    fprintf(stderr, "Stack Error: calling printStack() on NULL stack reference\n");
    exit(EXIT_FAILURE);
  }
  Node N = D->head;
  while (N != NULL) {
    fprintf(out, "%s %s\n", N->key, N->value);
    N = N->next;
  }
}
