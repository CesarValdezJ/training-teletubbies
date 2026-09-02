EJ01 = 01-employees-real-inheritance
EJ02 = 02-stack-misused-inheritance
EJ03 = 03-stack-composition-done-right

.PHONY: run-01 run-02 run-03 run-all clean

run:
	cd $(EJ01) && javac -d out *.java && java -cp out Main

run-02:
	cd $(EJ02) && javac -d out *.java && java -cp out Main

run-03:
	cd $(EJ03) && javac -d out *.java && java -cp out Main

clean:
	rm -rf $(EJ01)/out $(EJ02)/out $(EJ03)/out
