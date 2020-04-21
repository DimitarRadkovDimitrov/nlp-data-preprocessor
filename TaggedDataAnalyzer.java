import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaggedDataAnalyzer
{
    /**
     * Takes a $DOC-$TITLE-$TEXT formatted document from STDIN, performs token and
     * sentence data analysis, and prints results to STDOUT
     */
    public static void parsePOSTaggedData()
    {
        int numberOfDocuments = 0;
        int numberOfSentencesInDoc = 0;
        int numberOfTokensInDoc = 0;

        ArrayList<Integer> numberOfSentencesPerDoc = new ArrayList<>();
        ArrayList<Integer> numberOfTokensPerDoc = new ArrayList<>();
        ArrayList<Integer> numberOfTokensPerSentencePerDoc = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext())
        {
            String sentence = scanner.nextLine();
            if (sentence.startsWith("$DOC"))
            {
                if (numberOfDocuments != 0)
                {
                    numberOfSentencesPerDoc.add(numberOfSentencesInDoc);
                    numberOfTokensPerDoc.add(numberOfTokensInDoc);
                    numberOfTokensPerSentencePerDoc.add(numberOfTokensInDoc / numberOfSentencesInDoc);
                }
                numberOfDocuments++;
                numberOfSentencesInDoc = 0;
                numberOfTokensInDoc = 0;
            }
            else if (sentence.startsWith("$TITLE") || sentence.startsWith("$TEXT"))
            {
                continue;
            }
            else
            {
                numberOfSentencesInDoc++;
                String[] sentenceTokens = sentence.split(" ");
                numberOfTokensInDoc += sentenceTokens.length;
            }
        }
        scanner.close();

        numberOfSentencesPerDoc.add(numberOfSentencesInDoc);
        numberOfTokensPerDoc.add(numberOfTokensInDoc);
        numberOfTokensPerSentencePerDoc.add(numberOfTokensInDoc / numberOfSentencesInDoc);

        int minNumSentencesPerDoc = intListMinimum(numberOfSentencesPerDoc);
        int avgNumSentencesPerDoc = intListAverage(numberOfSentencesPerDoc);
        int maxNumSentencesPerDoc = intListMaximum(numberOfSentencesPerDoc);

        int minNumTokensPerDoc = intListMinimum(numberOfTokensPerDoc);
        int avgNumTokensPerDoc = intListAverage(numberOfTokensPerDoc);
        int maxNumTokensPerDoc = intListMaximum(numberOfTokensPerDoc);

        int avgNumTokensPerSentencePerDoc = intListAverage(numberOfTokensPerSentencePerDoc);

        printPerDocumentData(numberOfSentencesPerDoc, numberOfTokensPerDoc, numberOfTokensPerSentencePerDoc);
        printPerCollectionData(numberOfDocuments, minNumSentencesPerDoc, avgNumSentencesPerDoc, maxNumSentencesPerDoc, minNumTokensPerDoc, avgNumTokensPerDoc, maxNumTokensPerDoc, avgNumTokensPerSentencePerDoc);
    }

    /**
     * Prints data acquired which pertains to individual documents
     * @param numberOfSentencesPerDoc
     * @param numberOfTokensPerDoc
     * @param numberOfTokensPerSentencePerDoc
     */
    public static void printPerDocumentData(List<Integer> numberOfSentencesPerDoc, List<Integer> numberOfTokensPerDoc, List<Integer> numberOfTokensPerSentencePerDoc)
    {
        int numberOfDocuments = numberOfSentencesPerDoc.size();

        for (int i = 0; i < numberOfDocuments; i++)
        {
            System.out.println(String.format("Document #%d:", i + 1));
            System.out.println(String.format("\tNumber of Sentences: %d", numberOfSentencesPerDoc.get(i)));
            System.out.println(String.format("\tNumber of Tokens: %d", numberOfTokensPerDoc.get(i)));
            System.out.println(String.format("\tAverage Number of Tokens per Sentence: %d\n", numberOfTokensPerSentencePerDoc.get(i)));
        }
    }

    /**
     * Prints data acquired which pertains to the entire collection of documents
     * @param numberOfDocuments
     * @param minNumSentencesPerDoc
     * @param avgNumSentencesPerDoc
     * @param maxNumSentencesPerDoc
     * @param minNumTokensPerDoc
     * @param avgNumTokensPerDoc
     * @param maxNumTokensPerDoc
     * @param avgNumTokensPerSentencePerDoc
     */
    public static void printPerCollectionData(int numberOfDocuments, int minNumSentencesPerDoc, int avgNumSentencesPerDoc, int maxNumSentencesPerDoc, int minNumTokensPerDoc, int avgNumTokensPerDoc, int maxNumTokensPerDoc, int avgNumTokensPerSentencePerDoc)
    {
        System.out.println(String.format("\nTotal Number of Documents in Collection: %d", numberOfDocuments));
        System.out.println("-------------------------------------------");
        
        System.out.println(String.format("\nSentences:"));
        System.out.println(String.format("\tMinimum Number of Sentences: %d", minNumSentencesPerDoc));
        System.out.println(String.format("\tAverage Number of Sentences: %d", avgNumSentencesPerDoc));
        System.out.println(String.format("\tMaximum Number of Sentences: %d", maxNumSentencesPerDoc));

        System.out.println(String.format("\nTokens:"));
        System.out.println(String.format("\tMinimum Number of Tokens: %d", minNumTokensPerDoc));
        System.out.println(String.format("\tAverage Number of Tokens: %d", avgNumTokensPerDoc));
        System.out.println(String.format("\tMaximum Number of Tokens: %d", maxNumTokensPerDoc));

        System.out.println(String.format("\nAverage Number of Tokens per Sentence (Collection): %d", avgNumTokensPerSentencePerDoc));
    }

    /**
     * Given a list of integers returns the minimum value
     */
    public static int intListMinimum(List<Integer> array)
    {
        int min = Integer.MAX_VALUE;
        for (int number: array)
        {
            if (number < min)
            {
                min = number;
            }
        }
        return min;
    }

    /**
     * Given a list of integers returns the maximum value
     * @param array
     * @return
     */
    public static int intListMaximum(List<Integer> array)
    {
        int max = Integer.MIN_VALUE;
        for (int number: array)
        {
            if (number > max)
            {
                max = number;
            }
        }
        return max;
    }

    /**
     * Given a list of integers returns the average of all the values
     * @param array
     * @return
     */
    public static int intListAverage(List<Integer> array)
    {
        return intListSum(array) / array.size(); 
    }

    /**
     * Given a list of integers returns the sum of all the values
     */
    public static int intListSum(List<Integer> array)
    {
        int sum = 0;
        for (int number: array)
        {
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args)
    {
        parsePOSTaggedData();
    }
}