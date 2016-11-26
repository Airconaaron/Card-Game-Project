#include "deck.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char const *argv[])
{
	FILE* fp; 
	creature* head = NULL;

	srand(time(NULL));
	
	fp = fopen("deck.csv", "w");

	/* Type will be 1/0. Spell or Creature*/
	/* Resource will be from 1- 10 */
	/* Health and attack is split 2x + 1 stat points randomly */ 
	/* Death specifies whether the creature has special death effects 0, 2 or 3*/
	/* Spell types. 0 = attack creature spell, 1 = attack hero, 2 = skips a turn, 3 healing spells (can any creature and some classes of heroes) */
	fputs("Name,Type, Resource, Health, Attack, Death, Spell_Type", fp);

	createCreatureName();
	head = appendCreature(head, 0, 3);
	head = appendCreature(head, 5, 3);
	printf("%d %d\n", head->next->health, head->next->attack );
	freeCreature(head);

	fclose(fp);
	return 0;
}
