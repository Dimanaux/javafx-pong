build:
	mkdir -p out/production/pong
	javac -d out/production/pong -sourcepath src/main/java -cp out/production/pong src/main/java/com/example/pong/Main.java

rebuild:
	make clean
	make build

clean:
	rm -r out/production/pong/*

run:
	java -cp out/production/pong com.example.pong.Main

