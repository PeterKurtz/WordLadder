import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {

    private final ArrayList<ArrayList<String>> orderedWords = new ArrayList<>();
    private ArrayList<String> remOrderedWords = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    public void play(String start, String end) {

        int lenOfWord = start.length();

        resetRemOrderedWords(lenOfWord);

        if (start.length() != end.length()){
            System.out.println("The words are not the same length");
            return;
        }

        if (!remOrderedWords.contains(start) || !remOrderedWords.contains(end)) {
            System.out.println("The start word and or end word is not in the dictionary");
            return;
        }

        Queue wordInfoQueue = new Queue();
        WordInfo startObject = new WordInfo(start, 0);

        int totalEnqueued = 0;

        wordInfoQueue.enqueue(startObject);

        WordInfo firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();

        while (!wordInfoQueue.checkIfFinished()) {
            String word = firstInQueue.getWord();
            ArrayList<String> oneAwayWords = oneAway(word, true);

            for (String wordInArray:oneAwayWords) {
                enqueueWord(wordInArray, wordInfoQueue, firstInQueue);
                totalEnqueued += 1;
                if (wordInArray.equals(end)) {
                    WordInfo endWordInfo = (WordInfo) wordInfoQueue.getLastValue();
                    System.out.println(start + " -> " + end + " : " + endWordInfo.getMoves() + " Moves [" + endWordInfo.getHistory() + "] total enqueues " + totalEnqueued);
                    return;
                }
            }

            wordInfoQueue.dequeue();

            if (wordInfoQueue.checkIfFinished() == false) {
                firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();
            }
            else{
                System.out.println(start + " -> " + end + " : No ladder was found");
            }
        }
    }

    private void enqueueWord(String word, Queue queueObject, WordInfo wordInfoObject) {
        WordInfo addWord = new WordInfo(word, wordInfoObject.getMoves() + 1, wordInfoObject.getHistory() + " " + word);
        queueObject.enqueue(addWord);
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