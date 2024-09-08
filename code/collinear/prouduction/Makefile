.PHONY : zip
compile:
	javac -cp ../lib/algs4.jar -d ./out/ ./src/LineSegment.java ./src/Point.java ./src/BruteCollinearPoints.java ./src/FastCollinearPoints.java

TESTCASE=input6.txt
TESTDIR=./test

test-B: compile
	echo "BruteCollinearPoints"
	java -cp ../lib/algs4.jar:./out/ BruteCollinearPoints $(TESTDIR)/$(TESTCASE)

test-F: compile
	echo "FastCollinearPoints"
	java -cp ../lib/algs4.jar:./out/ FastCollinearPoints $(TESTDIR)/$(TESTCASE)

zip : 
	zip collinear.zip ./src/LineSegment.java ./src/Point.java ./src/BruteCollinearPoints.java ./src/FastCollinearPoints.java
