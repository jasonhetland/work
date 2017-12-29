//Jason Hetland
//1324246
//CMPS12B
//Lab5
// to learn how to implement ADTs in C. To get familiar with typedef and  
//struct commannds, header files, and information hiding, constructors.
//This was my test program to test out my operations functionality from 
//Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
   int i;

   for(i=0; i<7; i++){
      insert(A, word1[i], word2[i]);
   }

   printDictionary(stdout, A);

 //  for(i=0; i<7; i++){
   //   k = word1[i];
    //  v = lookup(A, k);
   // printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
  // }

   // insert(A, "five", "glow"); // error: key collision
     
   // delete(A, "two");
   // delete(A, "four");
   // delete(A, "five");

    // printDictionary(stdout, A);

  // for(i=0; i<7; i++){
    //  k = word1[i];
    // v = lookup(A, k);
   //  printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
//   }

   // delete(A, "one");  // error: key not found

  // printf("%s\n", (isEmpty(A)?"true":"false"));
  // printf("%d\n", size(A));
   //  makeEmpty(A);
  // printf("%s\n", (isEmpty(A)?"true":"false"));

//   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
