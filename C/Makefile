STUPIDFLAGS = -Wall -Werror -ansi -pedantic -ggdb

deck: main.o creater.o
	gcc main.o creater.o -o deck
	

main.o: main.c
	gcc -c main.c -o main.o $(STUPIDFLAGS)

creater.o: creater.c 
	gcc -c creater.c -o creater.o $(STUPIDFLAGS)

clean: 
	rm -f *.o *.csv deck
