#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int randr(int min, int max)
{
	double temp;
	int range = max - min + 1;
	temp = (double) rand()/(RAND_MAX + 1.0);
	return range * temp + min;
}

int arrLength(const char* arr[])
{
	int count = 0;
	while (arr[count] != '\0')
	{
		count++;
	}
	return count;
}

int* arrShuffle(int size)
{
	int *elements = malloc(sizeof(int)*size);
	int i, w, t;

	/* Creates the array we'll use*/
	for (i = 0; i < size; ++i)
	  elements[i] = i;

	/* Fisher-Yates Shuffle */
	for (i = size - 1; i > 0; --i) {
	  w = rand()%i;
	  t = elements[i];
	  elements[i] = elements[w];
	  elements[w] = t;
	}
	return elements;
}
