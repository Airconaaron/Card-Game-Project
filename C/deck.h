#ifndef DECK_H
#define DECK_H

#include <stdio.h>

#define SPELL 0
#define CREATURE 1

/* Hero name combinations */ 
extern const char* attr[];
extern const char* hero_names[];
extern const char* spell_names[];

typedef struct creature {
	int resource; 
	int health;
	int attack;
	int death;
	struct creature * next;
} creature;

int randr(int min, int max);
int arrLength (const char* arr[]);
int* arrShuffle(int size);

void createSpellName();

creature* createCreature(int resource, int death);
creature* appendCreature(creature* head, int resource, int death);
creature* createCreatureList();

void printCreature(creature* curr, FILE* fp, int count);
void printSpell(FILE* fp, int resource, int type, int count);

void printCreatureDeck(creature* head, FILE* fp);
void printSpellDeck(FILE* fp);
void freeCreature(creature* head);

#endif
