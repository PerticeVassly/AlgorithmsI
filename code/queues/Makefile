.PHONY:compile
.PHONY:test
.PHONY:zip

compile:
	javac -cp ../lib/algs4.jar -d ./out/ ./src/Deque.java ./src/RandomizedQueue.java ./src/Permutation.java

testcase=distinct.txt

test: compile
	java -cp ../lib/algs4.jar:./out/ Permutation 9 < ./test/${testcase}

zip:
	zip queues.zip ./src/Deque.java ./src/RandomizedQueue.java ./src/Permutation.java