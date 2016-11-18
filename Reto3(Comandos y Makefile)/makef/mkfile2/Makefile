inc=-I./Include

comp: Mult.o Rest.o Sum.o
	g++ -o comp Mult.o Rest.o Sum.o

Mult.o: Mult.C
	g++ $(inc) -c $<

Rest.o: Rest.C
	g++ $(inc) -c $<

Sum.o: Sum.C
	g++ $(inc) -c $<

clean:
	rm comp	*.o Mult Rest Sum

exe:
	./comp



