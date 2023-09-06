import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {

    public ArrayList<ArrayList<String>> orderedWords = new ArrayList<>();
    public ArrayList<ArrayList<String>> remOrderedWords = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    public void play(String start, String end) {

        System.out.println(start + " " + end);

        int lenOfWord = start.length();

        if (start.length() != end.length()){
            System.out.println("The words are not the same length");
            return;
        }

        if (!remOrderedWords.get(lenOfWord).contains(start) || !remOrderedWords.get(lenOfWord).contains(end)) {
            System.out.println("The start word and or end word is not in the dictionary");
            return;
        }

        System.out.println("These words are valid inputs");//For testing

    }

    private boolean diff(String startWord, String dictWord, int lenOfWord) {
        boolean oneAwayBool;
        int diffNum = 0;

        for (int i=0; i < lenOfWord; i++) {
            if (startWord.charAt(i) == dictWord.charAt(i)) {
                diffNum += 1;
            }
        }

        oneAwayBool = diffNum == lenOfWord - 1;

        return oneAwayBool;
    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();
        int lenOfWord = word.length();
        int numOfWords = remOrderedWords.get(lenOfWord).size();

        for (int i = 0; i < numOfWords; i++) {
            String wordInDict = remOrderedWords.get(lenOfWord).get(i);
            boolean isOneAway = diff(word, remOrderedWords.get(lenOfWord).get(i), lenOfWord);
            if (isOneAway) {
                words.add(wordInDict);
            }
        }

        if (withRemoval) {
            remOrderedWords.get(lenOfWord).remove(word);
            for (String removeWord : words) {
                remOrderedWords.get(lenOfWord).remove(removeWord);
            }
        }

        return words;
    }

    public void listWords(int length, int howMany) {
        // TODO: Write some good stuff here
        for (int i = 0; i < howMany; i++) {
            System.out.println(orderedWords.get(length).get(i));
        }
    }

    private void resetRemOrderedWords () {

        for (int i=0; i < orderedWords.size(); i++) {
            for (String word : orderedWords.get(i)) {
                remOrderedWords.get(i).add(word);
            }
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

            for (int i = 0; i <= longestWord; i++){
                remOrderedWords.add(new ArrayList());
            }

            resetRemOrderedWords();

        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary.txt: " + ex);
        }
    }
}