#include "deck.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const char* attr[] = {"Cowardly", "Brave", "Sleepy", "Adventurous", "Spirited", "Nefarious", "Determined", "Obnoxious", "Naked", "Clothed", NULL}; 
const char* hero_names[] = {"Baguette", "Cornelius", "Arthur", "Espinoza", "Polonius", "S4cr1f1c3", "Xenon", "Xavier", 
	"Bob", "Seymour", "Deckard Cain", "Corwin", "Zamboni", "Beaver", "Artemis", "Call of Duty Man", "xXx_n0Sc0p3_xXx", "BlazeIt420", "Generic Genericson", "Hunter2", NULL}; /* 20 heroes in total */

int randr(int min, int max)
{
	int temp;
	int range = max - min + 1;
	srand(time(NULL));
	temp = rand()%range;
	return temp + min;
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

void createCreatureName()
{
	int x = randr(0, arrLength(hero_names) - 1); /* hero */
	int y = randr(0, arrLength(attr) - 1); /* their attributes */

	printf("%s", hero_names[x]);
	printf(" the ");
	printf("%s", attr[y]);
	printf(",\n");
	
}
