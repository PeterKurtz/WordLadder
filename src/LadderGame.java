import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {

    public ArrayList<ArrayList<String>> orderedWords = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    public void play(String start, String end) {
        // TODO: Write some good stuff here

    }

    public boolean diff(String startWord, String dictWord, int lenOfWord) {
        boolean oneAwayBool;
        int diffNum = 0;

        for (int i=0; i < lenOfWord; i++) {
            if (startWord.charAt(i) == dictWord.charAt(i)) {
                diffNum += 1;
            }
        }

        if (diffNum == lenOfWord - 1) {
            oneAwayBool = true;
        }
        else {
            oneAwayBool = false;
        }

        return oneAwayBool;
    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();
        int lenOfWord = word.length();
        int numOfWords = orderedWords.get(lenOfWord).size();

        for (int i = 0; i < numOfWords; i++) {
            String wordInDict = orderedWords.get(lenOfWord).get(i);
            boolean isOneAway = diff(word, orderedWords.get(lenOfWord).get(i), lenOfWord);
            if (isOneAway) {
                words.add(wordInDict);
            }
        }

        // TODO: Write some good stuff here

        return words;
    }

    public void listWords(int length, int howMany) {
        // TODO: Write some good stuff here
        for (int i = 0; i < howMany; i++) {
            System.out.println(orderedWords.get(length).get(i));
        }
    }

    /*
        Reads a list of words from a file, putting all words of the same length into the same array.
     */
    private void readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

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

            for (int i = 0; i <= longestWord; i++){
                orderedWords.add(new ArrayList());
            }

            for (String word: allWords){
                int lenOfWord = word.length();
                orderedWords.get(lenOfWord).add(word);
            }

        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary.txt: " + ex);
        }
    }
}