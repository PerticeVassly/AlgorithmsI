.PHONY: zip compile test

LIB_PATH = ../lib/algs4.jar
OUT_PATH = ./out/
SRC_PATH = ./src/
TEST_PATH = ./test/
JAVAC_FLAG = -cp $(LIB_PATH) -d $(OUT_PATH)
SRC_FILES = $(wildcard $(SRC_PATH)*.java)
TEST_FILE = puzzle00.txt

compile:
	javac $(JAVAC_FLAG) $(SRC_FILES)

zip:
	zip 8puzzle.zip $(SRC_PATH)Board.java $(SRC_PATH)Solver.java

test: compile
	java -cp $(LIB_PATH):$(OUT_PATH) Solver $(TEST_PATH)$(TEST_FILE)


