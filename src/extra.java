public class extra {
    //Outputs aa
    //System.out.println(orderedWords.get(2).get(0));

    //length of first word
    //System.out.println(allWords.get(0).length());
    //size of sizeOfAllWords, gets last word
    //int sizeOfAllWords = allWords.size();
    //System.out.println(sizeOfAllWords);
    //System.out.println(allWords.get(sizeOfAllWords - 1));

    //System.out.println(diff("word", "word", 4));
    //System.out.println(diff("word", "weee", 4));
    //System.out.println(diff("word", "weed", 4));
    //System.out.println(orderedWords.get(5).get(100)); Returns the same as below
    //System.out.println(remOrderedWords.get(5).get(100));

    /* This code works which tested withRemoval = true
    System.out.println(g.remOrderedWords.get(2));
    for (String word : g.oneAway("aa", true)) {
        System.out.println(word);
     }
     System.out.println(g.remOrderedWords.get(2));
     */

    //System.out.println(g.oneAway("floccinaucinihilipilification", false)); returns and empty list.

    //g.play("aa", "tot"); exits the program.
    //        g.play("aa", "run"); words in the program.
    //        g.play("aa", "uu");
    //System.out.println(wordInfoQueue.isEmpty()); tested and works in LadderGame

    //System.out.println(word.getMoves() + " " + word.getWord() + " " + word.getHistory()); gets moves word and history

    /*Testing Queue It works!*/
        /*
        Queue testingQueue = new Queue();
        testingQueue.enqueue("Hello");
        testingQueue.enqueue("World");
        testingQueue.enqueue("!");
        System.out.println("");*/

    /*For Testing It Works!*/
        /*
        WordInfo endingObject = new WordInfo(end, 0);
        wordInfoQueue.enqueue(wordObject);
        wordInfoQueue.enqueue(endingObject);*/

    //System.out.println(oneAway(wordObject.getWord(), false)); this works!

    /*
    This works!!!
    System.out.println(wordInfoQueue.isEmpty());
        wordInfoQueue.enqueue(wordObject);
        System.out.println(wordInfoQueue.isEmpty());*/

    /*This works!!!        wordInfoQueue.enqueue(startObject);
        wordInfoQueue.enqueue(endObject);
        wordInfoQueue.dequeue(startObject);*/

    /*        //For debugging WORKS!!!
        WordInfo endObject = new WordInfo(end, 0);

        WordInfo testingObject = new WordInfo("Testing", 0);

        wordInfoQueue.enqueue(startObject);
        wordInfoQueue.enqueue(endObject);
        wordInfoQueue.enqueue(testingObject);
                System.out.println(firstInQueue);

        wordInfoQueue.dequeue();

        firstInQueue = (WordInfo) wordInfoQueue.getFirstValue();
        System.out.println(firstInQueue);
        */
}
