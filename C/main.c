#include "deck.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char const *argv[])
{
	FILE* fp; 
	creature* head = NULL;
	creature* head1 = NULL;

	srand(time(NULL));
	

	fp = fopen("deck.csv", "w");
	if (fp == NULL)
	{
		fclose(fp);
		printf("Couldn't create the file\n");
		return -1;
	}

	/* Type will be 1/0. Spell or Creature*/
	/* Resource will be from 1- 10 */
	/* Health and attack is split 2x + 1 stat points randomly */ 
	/* Death specifies whether the creature has special death effects 0, 2 or 3*/
	/* Spell types. 0 = attack creature spell, 1 = attack hero, 2 = healing spells (can any creature and some classes of heroes) */
	fputs("Name,Type, Resource, Health, Attack, Death, Spell_Type, \n", fp);

	/* hence the first 30 cards will bleong to player 1*/
	head = createCreatureList();
	printCreatureDeck(head, fp);
	printSpellDeck(fp);

	head1 = createCreatureList();
	printCreatureDeck(head1, fp);
	printSpellDeck(fp);

	fclose(fp);
	return 0;
}
