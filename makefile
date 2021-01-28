all:
	javac -d . *.java
	
run:
	java -cp . classes.gis.property.Property
	
clean:
	rm -f *.class