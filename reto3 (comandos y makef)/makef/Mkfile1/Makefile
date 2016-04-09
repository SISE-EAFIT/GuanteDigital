inc=-I./Include

ejem1: ejem1.o
	g++ $< -o $@

ejem1.o: ejem1.C
	g++ $(inc) -c $<

clean:
	rm *.o ejem1

exe:
	./ejem1
