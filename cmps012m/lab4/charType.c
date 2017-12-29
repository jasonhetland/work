/*
*Jason Hetland
*1324246
*7/24/14
*Lab4 CMPS12M
*The purpose of this lab assignment is to get more practice programming in
*C, including the character functions in the library ctype.h, and dynamic
*memory allocation using malloc, calloc, and free.
*/

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

//function prototype for function extract_chars
void extract_chars(char* s, char* a, char* d, char* p, char* w);

//function main takes in command line args
int main(int argc, char* argv[]) {
	FILE* in; //input file handle
	FILE* out; //output file handle
	char* line; //line to hold string input
	char* alpha; //string to hold alpha chars
	char* digit; // string to hold digits
	char* punct; //string to hold punctuation
	char* space; //string to hold white space

//print error message if there are not 3 arguments
if (argc != 3) {
	printf("Usage: %s input-file output-file\n", argv[0]);
	exit (EXIT_FAILURE);
}
//open input file for reading
if ((in = fopen(argv[1], "r")) == NULL) {
	printf("Unable to read from file %s\n", argv[1]);
	exit(EXIT_FAILURE);
}
// open output file for writing
if((out=fopen(argv[2], "w"))== NULL){
	printf("Unable to write to file %s\n", argv[2]);
	exit(EXIT_FAILURE);
}
//allocate line, alpha, digit, punct, and space strings on heap
line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
punct = calloc(MAX_STRING_LENGTH+1, sizeof(char));
space = calloc(MAX_STRING_LENGTH+1, sizeof(char));
//Character strings must not null
assert(line != NULL && alpha != NULL && digit != NULL && punct != NULL && space != NULL); 

//read in each line from input file and extract all characters
int i = 1; 
int stringlength;
while (fgets(line, MAX_STRING_LENGTH, in) != NULL) {

if (i < 5) {
	extract_chars(line, alpha, digit, punct, space);
	fprintf(out, "line %d contains:\n", i);

	stringlength = strlen(alpha);
	fprintf(out, "%d alphabetic characters: %s\n", stringlength, alpha);
	
	stringlength = strlen(digit);
	fprintf(out, "%d numeric characters: %s\n", stringlength, digit);

	stringlength = strlen(punct);
	fprintf(out, "%d punctuation characters: %s\n", stringlength, punct);

	stringlength = strlen(space);
	fprintf(out, "%d whitespace characters: %s\n", stringlength, space);
	i++;
	}
}
//free heap memory
free(line);
free(alpha);
free(digit);
free(punct);
free(space);

//close input file and output file
fclose(in);
fclose(out);

return EXIT_SUCCESS;
}
//function definition of extract_chars
void extract_chars(char* s, char* a, char* d, char* p, char* w) {
	int i = 0, j = 0, k = 0, l = 0, m = 0;
	while (s[i] != '\0' && i < MAX_STRING_LENGTH) {
		if (isalpha((int)s[i]))
			a[j++] = s[i];
    		      else if (isdigit((int)s[i])) 
    	    		d[k++] = s[i];
    	              else if (ispunct((int)s[i])) 
    			p[l++] = s[i];
    		      else if (isspace((int)s[i])) 
    			w[m++] = s[i];
		        i++;
}
	a[j] = '\0';
	d[k] = '\0';
	p[l] = '\0';
	w[m] = '\0';
}
