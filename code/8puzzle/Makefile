.PHONY: zip compile

LIB_PATH = ../lib/
OUT_PATH = ./out/
SRC_PATH = ./src/
TEST_PATH = ./test/
JAVAC_FLAG = -cp $(LIB_PATH) -d $(OUT_PATH)
SRC_FILES = $(wildcard $(SRC_PATH)*.java)

compile:
	javac $(JAVAC_FLAG) $(SRC_FILES)

zip:
	zip 8puzzle.zip $(SRC_FILES)
