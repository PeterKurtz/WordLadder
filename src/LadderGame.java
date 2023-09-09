import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/*The LadderGame class contains the methods that create word ladders.*/
public class LadderGame {

    //The characteristic orderedWords will contain the entire dictionary.
    //The characteristic remOrderedWords will contain the array of words of a specific length specified by the words being analyzed.
    private ArrayList<ArrayList<String>> orderedWords = new ArrayList<>();
    private ArrayList<String> remOrderedWords = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    /*The method play makes the word ladder if it exists and if not then it will print that it does not exist.*/
    public void play(String start, String end) {

        int lenOfWord = start.length();

        resetRemOrderedWords(lenOfWord);

        if (start.length() != end.length()){
            System.out.println("The words are not the same length");
            return;
        }

        start = start.toLowerCase();
        end = end.toLowerCase();

        if (!remOrderedWords.contains(start) || !remOrderedWords.contains(end)) {
            System.out.println("The start word and or end word is not in the dictionary");
            return;
        }

        Queue wordInfoQueue = new Queue();
        WordInfo startObject = new WordInfo(start, 0);

        int totalEnqueued = 0;

        wordInfoQueue.enqueue(startObject);

        WordInfo firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();

        //if wordInfoQueue.isEmpty() is false then the Queue wordInfoQueue does not have any more ladders to check and the word ladder is impossible.
        while (!wordInfoQueue.isEmpty()) {
            String word = firstInQueue.getWord();
            ArrayList<String> oneAwayWords = oneAway(word, true);

            //This for loop inputs each one away word into the queue with the assistance of the method enqueueWord.
            for (String wordInArray:oneAwayWords) {
                enqueueWord(wordInArray, wordInfoQueue, firstInQueue);
                totalEnqueued += 1;

                //This occurs if a word ladder was successfully made. It will end the method.
                if (wordInArray.equals(end)) {
                    WordInfo endWordInfo = (WordInfo) wordInfoQueue.getLastValue();
                    System.out.println(start + " -> " + end + " : " + endWordInfo.getMoves() + " Moves [" + endWordInfo.getHistory() + "] total enqueues " + totalEnqueued);
                    return;
                }
            }

            //This will take out the queue that was used to find more word ladders.
            wordInfoQueue.dequeue();

            if (!wordInfoQueue.isEmpty()) {
                firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();
            }
            else{
                System.out.println(start + " -> " + end + " : No ladder was found");
            }
        }
    }

    /*The method enqueueWord adds a word ladder into the queue with the purpose of finding the shortest word ladder.*/
    private void enqueueWord(String word, Queue queueObject, WordInfo wordInfoObject) {
        WordInfo addWord = new WordInfo(word, wordInfoObject.getMoves() + 1, wordInfoObject.getHistory() + " " + word);
        queueObject.enqueue(addWord);
    }

    /*The method diff returns the difference between the startWord and word in the dictionary with the purpose of finding one away words.
    This method assists the method oneAway.*/
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

    /*The method oneAway returns a list array of all the one away words in the dictionary of the given word.
    * This has the purpose of making new word ladders that may be the word ladder with the final word.*/
    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();
        int lenOfWord = word.length();

        if (!withRemoval) {
            resetRemOrderedWords(lenOfWord);
            word = word.toLowerCase();
        }

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

    /*The method listWords prints a given amount of words of a given length. This has the purpose of validating that the
    * dictionary is implemented correctly.*/
    public void listWords(int length, int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.println(orderedWords.get(length).get(i));
        }
    }

    /*The method resetRemOrderedWords makes the list array remOrderedWords contain only the words of a given length which
    * will be used to make the word ladder.*/
    private void resetRemOrderedWords (int wordLength) {
        remOrderedWords.clear();
        remOrderedWords.addAll(orderedWords.get(wordLength));
    }

    /*The method readDictionary reads a list of words from a file, putting all words of the same length into the same array.*/
    private void readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
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