#include "deck.h"
#include <stdio.h>
#include <stdlib.h>

/* 10 Attributes */
const char* attr[] = {"Cowardly", "Punished", "Sleepy", "Adventurous", "Spirited", "Nefarious", "Determined", "Obnoxious", "Naked", "Clothed", NULL}; 
/* 20 heroes in total */
const char* hero_names[] = {"Baguette", "SpongeBob", "Tobby Ran", "McAdoodle", "Harambe", "S4cr1f1c3", "Gab3n", "Pimone Serault", "Wim Tertz", "Seymour", 
"Pylons?", "Lericles Pewis", "Zamboni", "Beaver", "Chaurice Mah Jong", "Call of Duty Man", "xXx_n0Sc0p3_xXx", "BlazeIt420", "John Cena", "Hackquinas Hodor", NULL}; 

/* 10 Spells in total*/
const char* spell_names[] = {"Computer Virus", "Elite Hacks", "Lizard Squad", "N0SC0P3", "Email Server", "Iluminati","D*cks Out","Dank Kush",  "Doritos", "Mountain Dew"};

/*
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
*/

void printCreature(creature* curr, FILE* fp, int count)
{
	/* Name,Type, Resource, Health, Attack, Death, Spell_Type */

	/* Name */
	fputs(hero_names[count], fp);
	fputs(" the ", fp);
	fputs(attr[count%10], fp);
	fputs(",", fp);

	/* Type*/
	fputs("0,", fp);

	/* Resource */
	fprintf(fp,"%d," , curr->resource);

	/* Health*/
	fprintf(fp,"%d," , curr->health);

	/* Attack*/
	fprintf(fp,"%d," , curr->attack);

	/* Death*/
	fprintf(fp,"%d," , curr->death);

	/* Spell_Type*/
	fputs("-1, \n", fp);
}

void printSpell(FILE* fp, int resource, int type, int count)
{	
	/* Name,Type, Resource, Health, Attack, Death, Spell_Type */

	/* Name */
	fputs(spell_names[count], fp);

	/* Type*/
	fputs("1,", fp);

	/* Resource */
	fprintf(fp,"%d," , resource);

	/* Health*/
	fputs("-1,", fp);

	/* Attack*/
	fputs("-1,", fp);

	/* Death*/
	fputs("-1,", fp);

	/* Spell_Type*/
	fprintf(fp,"%d, \n" ,type);
}

void printCreatureDeck(creature* head, FILE* fp)
{
	int length = arrLength(hero_names);
	creature* temp;
	int* x = arrShuffle(length); 
	int i;
	
	temp = head;
	/* Each count will be some value of x[i]*/

	for (i = 0; i < length - 1; i++)
	{
		printCreature(temp, fp, x[i]);
		temp = temp->next;
	}

	freeCreature(head);
	free(x);
}

void printSpellDeck(FILE* fp)
{
	int i;
	int count = 0;
	/* Damage to enemy minions*/
	for (i = 0; i < 3; ++i)
	{
		printSpell(fp, randr(1,10), 0, count);
		count++;
	}
	/* Damage to enemey hero*/
	for (i = 0; i < 4; ++i)
	{
		printSpell(fp, randr(1,10), 1, count);
		count++;
	}
	/* healing spells (only for creatures*/
	for (i = 0; i < 3; ++i)
	{
		printSpell(fp, randr(1,10), 2, count);
		count++;
	}
}
