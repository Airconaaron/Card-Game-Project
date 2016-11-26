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