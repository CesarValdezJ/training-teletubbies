SOURCES = Product.java Taxes.java FrontEndSimulator.java SalesCalculator.java Main.java

.PHONY: training-false training-true clean

training-false:
	@sed -i.bak -E 's/boolean training = (true|false);/boolean training = false;/' Main.java
	javac $(SOURCES)
	java Main
	@mv Main.java.bak Main.java
	@rm -f *.class

training-true:
	@sed -i.bak -E 's/boolean training = (true|false);/boolean training = true;/' Main.java
	javac $(SOURCES)
	java Main
	@mv Main.java.bak Main.java
	@rm -f *.class

clean:
	rm -f *.class Main.java.bak
