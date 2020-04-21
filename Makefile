JAVAC=javac
CLASSPATH=opennlp-tools-1.9.1.jar:.
#JFLEX=jflex
JFLEX=/Users/Dimitar/jflex-1.7.0/bin/jflex

all: SentenceSplitter.class Token.class Lexer.class Scanner.class POSTagger.class TaggedDataAnalyzer.class

%.class: %.java
	$(JAVAC) -cp $(CLASSPATH) $^

Lexer.java: article.flex
	$(JFLEX) article.flex

clean:
	rm -f Lexer.java *.class *~
