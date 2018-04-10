#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 9
#define SIZE 7
#define WORD_SIZE 5


const char searchspace [SIZE][SIZE] = {"TCLASQU", "RLETTUS", "EATREES", "ESKATUT", "CSQUAEA", "ARRAYMC", "CRSQYVK"};
char word_list [90] [5];
int locations [90] [3];
const char searchterms [WORD_SIZE][WORD_SIZE] = {"ARRAY", "CLASS", "QUEUE", "STACK", "TREES"};
int searchtermsVal [WORD_SIZE] = {0, 0, 0, 0, 0};

struct matches {
	int y;
	int x;
	int arrayPos;
};

struct matches matchesArray[NUM_THREADS];
void *searcher (void *param);
int compareStrings(char a [], char b []);

int main (int argc, char *argv[]) 
{
	pthread_attr_t attr;
	pthread_t workers[NUM_THREADS];
	pthread_attr_init(&attr);
	pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);

	int ys = 0;
	int xs = 0;
	int position = 0;

	for(int x = 0; x < NUM_THREADS; ++x) 
	{
		matchesArray[x].y = ys;
		matchesArray[x].x = xs;
		matchesArray[x].arrayPos = position;
		pthread_create(&workers[x], &attr, searcher, (void *) &matchesArray[x]);
		++xs;
		if (xs%3 == 0) {
			xs =0;
			++ys;
		};
		position = position +10;
	};

	for(int a = 0 ; a < NUM_THREADS; ++a) 
	{
		pthread_join(workers[a], NULL);
	};


	char temp [5];
	char temp2 [5];
	int check;

	for(int i = 0; i < 90; ++i) 
	{
		for(int j = 0; j < 5; ++j) 
		{
			temp[j] = word_list[i][j];
		};
		for(int k = 0; k < 5; ++k) 
		{
			for(int u = 0; u < 5; ++u) 
			{
				temp2[u]  = searchterms[k][u];
			};
			check = compareStrings(temp, temp2);
			if(check == 0 && searchtermsVal[k] == 0) {
				searchtermsVal[k] = 1;
				printf("word: %s \n", temp);
				printf("y: %d,",   locations[i][0]+1);
				printf("x: %d,", locations[i][1]+1);
				if(locations[i][2] == 0){
					printf("horizantal");
				} else {
					printf("verticle");
				};
			};
		};
	};	
	return 0;
};

int compareStrings(char a [], char b []) 
{
	for(int i = 0; i < 5; ++i) 
	{
		if(a[i] != b[i]) {
			return -1;
		};
	};
	return 0;
};

void *searcher (void *matchesPassed) 
{
	struct matches *matchesGiven;
	matchesGiven = (struct matches *) matchesPassed;
	int yPassed = matchesGiven -> y;
	int xPassed = matchesGiven -> x;
	int arrayPosition = matchesGiven -> arrayPos;
	char temp[5];
	for(int i = yPassed; i < yPassed + 5; ++i) 
	{
		int starter = 0;
		for(int j = xPassed; j < xPassed + 5; ++j) 
		{
			temp[starter] = searchspace[i][j];
			++starter;
		};
		for(int k = 0; k < 5; ++k) 
		{
			word_list[arrayPosition][k] = temp[k];
		};
		locations[arrayPosition][0] = i;
		locations[arrayPosition][1] = xPassed;
		locations[arrayPosition][2] = 0;
	};

	for(int i = xPassed; i < xPassed + 5; ++i) 
	{
		int starter = 0;
		for(int j = yPassed; j < yPassed + 5; ++j) 
		{
			temp[starter] = searchspace[j][i];
			++starter;
		};
		for(int k = 0; k < 5; ++k) 
		{
			word_list[arrayPosition][k] = temp[k];
		};
		locations[arrayPosition][0] = yPassed;
		locations[arrayPosition][1] = i;
		locations[arrayPosition][2] = 1;
	};
};