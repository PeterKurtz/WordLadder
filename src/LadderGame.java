import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {

    private ArrayList<ArrayList<String>> orderedWords = new ArrayList<>();
    private ArrayList<String> remOrderedWords = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    public void play(String start, String end) {

        System.out.println(start + " " + end); // Don't forget to remove. Only for testing.

        int lenOfWord = start.length();

        if (start.length() != end.length()){
            System.out.println("The words are not the same length");
            return;
        }

        if (!remOrderedWords.contains(start) || !remOrderedWords.contains(end)) {
            System.out.println("The start word and or end word is not in the dictionary");
            return;
        }

        resetRemOrderedWords(lenOfWord);

        Queue wordInfoQueue = new Queue();
        WordInfo startObject = new WordInfo(start, 0);

        //For debugging
        WordInfo endObject = new WordInfo(end, 0);

        WordInfo testingObject = new WordInfo("Testing", 0);

        wordInfoQueue.enqueue(startObject);
        wordInfoQueue.enqueue(endObject);

        WordInfo firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();
        System.out.println(firstInQueue);

        //wordInfoQueue.dequeue();
        //System.out.println(firstInQueue);


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

        resetRemOrderedWords(lenOfWord);

        int numOfWords = remOrderedWords.size();

        for (int i = 0; i < numOfWords; i++) {
            String wordInDict = remOrderedWords.get(i);
            boolean isOneAway = diff(word, remOrderedWords.get(i), lenOfWord);
            if (isOneAway) {
                words.add(wordInDict);
            }
        }

        if (withRemoval) {
            remOrderedWords.remove(word);
            for (String removeWord : words) {
                remOrderedWords.remove(removeWord);
            }
        }

        return words;
    }

    public void listWords(int length, int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.println(orderedWords.get(length).get(i));
        }
    }

    private void resetRemOrderedWords (int wordLength) {
        remOrderedWords.clear();
        remOrderedWords.addAll(orderedWords.get(wordLength));
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