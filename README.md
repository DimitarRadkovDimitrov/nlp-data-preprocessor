# Data Preprocessor

Uses data preprocessing techniques such as sentence splitting, string tokenizing, and part-of-speech tagging to make article data easier to analyze.

<br>

## Build 

* Build in the root directory using makefile.
    ```
    make
    ```

<br>

## Run

* Run each program individually or together through piping. All programs read from STDIN and write to STDOUT.

* Run sentence splitter.
    ```
    java -cp opennlp-tools-1.9.1.jar:. SentenceSplitter < {input file}
    ```

* Run scanner.
    ```
    java Scanner < {input file}
    ```

* Run POS tagger.
    ```
    java -cp opennlp-tools-1.9.1.jar:. POSTagger < {input file}
    ```

* Run data analyzer.
    ```
    java TaggedDataAnalyzer < {input file}
    ```

* All at once.
    ```
    java -cp opennlp-tools-1.9.1.jar:. SentenceSplitter < {input file} | java Scanner | java -cp opennlp-tools-1.9.1.jar:. POSTagger | java TaggedDataAnalyzer
    ```

<br>

## Known Limitations

* Programs depend on the $DOC-$TITLE-$TEXT formatting for each document provided as input.
* No user input validation.
