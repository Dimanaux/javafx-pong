build:
	mkdir -p out/production/pong
	javac -d out/production/pong -sourcepath src/main/java -cp out/production/pong src/main/java/com/example/pong/Player1.java
	javac -d out/production/pong -sourcepath src/main/java -cp out/production/pong src/main/java/com/example/pong/Player2.java

rebuild:
	make clean
	make build

clean:
	rm -r out/production/pong/*

run1:
	java -cp out/production/pong com.example.pong.Player1

run2:
	java -cp out/production/pong com.example.pong.Player2

jar:
	make build
	cp -r src/main/java/META-INF out/production/pong/META-INF
	rm *.jar
	cd out/production/pong
	jar cfm P1.jar META-INF/M1.MF *
	jar cfm P2.jar META-INF/M2.MF *
	mv *.jar ../../..
	cd ../../..

