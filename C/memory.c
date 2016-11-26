#include "deck.h"
#include <stdlib.h>
#include <stdio.h>

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

/* Death number tells us both its cost and what kind of effect it'll have*/
creature* createCreature(int resource, int death)
{
	creature* temp;
	int stat = 2* resource + 1 - death;
	int attack = randr(1, stat - 1);

	temp = (creature *)malloc(sizeof(creature));

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

creature* createCreatureList()
{
	creature* head = NULL;
	int i;
	int length = arrLength(hero_names);

	int low = randr(10, 15);
	int mid = randr(3, 4);
	int hi = length - low - mid;

	/* Create the low cards*/
	for (i = 0; i < low; ++i)
	{
		head = appendCreature(head, randr(1,2), 0);
	}

	/* Mid cards*/

	for (i = 0; i < mid; ++i)
	{
		head = appendCreature(head, randr(3, 6), 0);
	}

	for (i = 0; i < hi; i++)
	{
		head = appendCreature(head, randr(7,10), randr(2,3));
	}

	/* Hi cards*/
	return head;
}
