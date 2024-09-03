.PHONY:compile

compile:
	javac -cp ../lib/algs4.jar -d ./out/ ./src/Deque.java ./src/RandomizedQueue.java ./src/Permutation.java

testcase=distinct.txt

test: compile
	for i in `seq 1 10`; 
		do java -cp ../lib/algs4.jar:./out/ Permutation 3 < ./test/${testcase}; done