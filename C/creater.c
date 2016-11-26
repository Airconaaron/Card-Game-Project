#include "deck.h"
#include <stdio.h>
#include <stdlib.h>

/* 10 Attributes */
const char* attr[] = {"Cowardly", "Punished", "Sleepy", "Adventurous", "Spirited", "Nefarious", "Determined", "Obnoxious", "Naked", "Clothed", NULL}; 
/* 20 heroes in total */
const char* hero_names[] = {"Baguette", "SpongeBob", "Tobby Ran", "McAdoodle", "Harambe", "S4cr1f1c3", "Gab3n", "Pimone Serault", "Wim Tertz", "Seymour", 
"Pylons?", "Lericles Pewis", "Zamboni", "Beaver", "Chaurice Mah Jong", "Call of Duty Man", "xXx_n0Sc0p3_xXx", "BlazeIt420", "John Cena", "Hackquinas Hodor", NULL}; 


void createCreatureName()
{
	int length = arrLength(hero_names);
	int* x = arrShuffle(length); 
	int i;

	for (i = 0; i < length; i++)
	{
		printf("%s", hero_names[x[i]]);
		printf(" the ");
		printf("%s,\n", attr[x[i]%10]);
	}

	free(x);
}

/* Death number tells us both its cost and what kind of effect it'll have*/
creature* createCreature(int resource, int death)
{
	creature* temp;
	int stat = 2* resource + 1 - death;
	int attack = randr(1, stat - 1);

	temp = (creature *)malloc(sizeof(creature));

	temp->type = 0;
	temp->resource = resource; 
	temp -> death = death;
	temp -> next = NULL;
	temp -> attack = attack;
	temp -> health = stat - attack;



	/* stat points are 2x + 1 resource */
	return temp;
}

creature* appendCreature(creature* head, int resource, int death)
{
	creature* LAST = createCreature(resource, death);
	creature* temp;

	if (head == NULL)
	{
		head = LAST;
	}
	else
	{
		temp = head;

		while(temp -> next)
		{
			temp = temp->next;
		}
		temp->next = LAST;
	}
	return (head);
}

void printCreatures(creature* head, FILE* fp)
{

}

void freeCreature(creature* head)
{
	creature* temp;

	while ((temp = head) != NULL)
	{
		head = head -> next;
		free(temp);
	}
	head = NULL;
}
