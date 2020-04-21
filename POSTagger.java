import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

/**
 * Program used to append part-of-speech tags on space-delimited
 * tokens
 */
public class POSTagger
{
    public static final String ENGLISH_POS_TAG_MODEL_LOCATION = "./OpenNLP_models/en-pos-maxent.bin";
    public static final int MINIMUM_SIZE_TAG = 5;

    /**
     * Takes a $DOC-$TITLE-$TEXT formatted document from STDIN and prints out space-delimeted
     * and POS-tagged tokens
     * @param POSTagger
     */
    public static void printPOSTaggedTokens(POSTaggerME POSTagger)
    {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext())
        {
            String nextTokenizedSentence = scanner.nextLine();
            if (nextTokenizedSentence.startsWith("$DOC") || nextTokenizedSentence.startsWith("$TITLE") || nextTokenizedSentence.startsWith("$TEXT"))
            {
                System.out.println(nextTokenizedSentence);
            }
            else
            {
                String[] sentenceTokens = nextTokenizedSentence.split(" ");
                String[] POSTags = getPOSTagsForSentence(POSTagger, sentenceTokens);

                for (int i = 0; i < POSTags.length; i++)
                {
                    System.out.print(String.format("%s/%s ", sentenceTokens[i], POSTags[i]));
                }
                System.out.println();
            }    
        }
        scanner.close();
    }

    /**
     * Given an array of tokens returns the same tokens with POS tags appended to each
     * @param POSTagger
     * @param tokens
     * @return
     */
    public static String[] getPOSTagsForSentence(POSTaggerME POSTagger, String[] tokens)
    {
        return POSTagger.tag(tokens);
    }

    public static void main(String[] args)
    {
        try (InputStream englishPOSModel = new FileInputStream(POSTagger.ENGLISH_POS_TAG_MODEL_LOCATION)) 
        {
            POSModel POSModel = new POSModel(englishPOSModel);
            POSTaggerME POSTagger = new POSTaggerME(POSModel);
            printPOSTaggedTokens(POSTagger);
        }
        catch(Exception e)
        {
            System.out.println("Unexpected exception:");
            e.printStackTrace();
        }
    }
}