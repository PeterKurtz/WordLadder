import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    public void play(String start, String end) {
        // TODO: Write some good stuff here
        //System.out.println("Test");
    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();

        // TODO: Write some good stuff here

        return words;
    }

    public void listWords(int length, int howMany) {
        // TODO: Write some good stuff here
    }

    /*
        Reads a list of words from a file, putting all words of the same length into the same array.
     */
    private void readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

        //
        // Track the longest word, because that tells us how big to make the array.
        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
            //
            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }

            ArrayList<ArrayList<String>> orderedWords = new ArrayList<>(longestWord);

            for (int i = 0; i < longestWord; i++){
                orderedWords.add(new ArrayList());
            }

            for (String word: allWords){
                int lenOfWord = word.length();
                orderedWords.get(lenOfWord - 1).add(word);
            }

            //Outputs aa
            System.out.println(orderedWords.get(1).get(0));

            //length of first word
            //System.out.println(allWords.get(0).length());
            //size of sizeOfAllWords, gets last word
            //int sizeOfAllWords = allWords.size();
            //System.out.println(sizeOfAllWords);
            //System.out.println(allWords.get(sizeOfAllWords - 1));

            // TODO: You need to do something here to organize the words into groups/arrays of words with the same size

        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary.txt: " + ex);
        }
    }
}