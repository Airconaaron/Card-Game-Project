STUPIDFLAGS = -Wall -Werror -ansi -pedantic -ggdb

deck: main.o creater.o random.o memory.o
	gcc main.o creater.o random.o memory.o -o deck

main.o: main.c
	gcc -c main.c -o main.o $(STUPIDFLAGS)

creater.o: creater.c 
	gcc -c creater.c -o creater.o $(STUPIDFLAGS)

random.o: random.c 
	gcc -c random.c -o random.o $(STUPIDFLAGS)

memory.o: memory.c
	gcc -c memory.c -o memory.o $(STUPIDFLAGS)

clean: 
	rm -f *.o *.csv deck
