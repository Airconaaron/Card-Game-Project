#ifndef DECK_H
#define DECK_H

#define SPELL 0
#define CREATURE 1

/* Hero name combinations */ 
extern const char* attr[];
extern const char* hero_names[];

typedef struct spell {
	int type;
	int resource; 
	int spell_type;
} spell;


typedef struct creature {
	int type;
	int resource; 
	int health;
	int attack;
} creature;

int randr(int min, int max);
int arrLength (const char* arr[]);

void createSpellName();

void createCreatureName();

spell* createCreature();
#endif
