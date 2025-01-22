.PHONY: zip compile test

LIB_PATH = ../lib/algs4.jar
OUT_PATH = ./out/
SRC_PATH = ./src/
TEST_PATH = ./test/
JAVAC_FLAG = -cp $(LIB_PATH) -d $(OUT_PATH)
SRC_FILES = $(wildcard $(SRC_PATH)*.java)
TEST_FILE = input10K.txt

compile:
	javac $(JAVAC_FLAG) $(SRC_FILES)

zip:
	zip kdtree.zip $(SRC_PATH)PointSET.java $(SRC_PATH)KdTree.java

test: compile
	java -ea -cp $(LIB_PATH):$(OUT_PATH) NearestNeighborVisualizer $(TEST_PATH)$(TEST_FILE)