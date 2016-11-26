#include "deck.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char const *argv[])
{
	FILE* fp; 
	fp = fopen("deck.csv", "w");

	/* Type will be 1/0. Spell or Creature*/
	/* Resource will be from 1- 10 */
	/* Health and attack is split 2x + 1 stat points randomly */ 
	/* Spell types. 0 = attack creature spell, 1 = attack hero, 2 = skips a turn, 3 will allow you to attack the hero directly */
	fputs("Name,Type, Resource, Health, Attack, Spell_Type", fp);

	createCreatureName();

	fclose(fp);
	return 0;
}
