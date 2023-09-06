public class Queue <E>{
    private class QueueNode<E> {
        public E value;
        public QueueNode<E> next;

        public QueueNode() {
        }

        public QueueNode(E o) { this.value = o;}
    }

    private QueueNode<E> head = new QueueNode<>();
    private QueueNode<E> tail;
    private int enqueuedSize;

    public void enqueue(E value) {
        // TODO
    }

    public void dequeue(E value) {
        // TODO
    }

    public boolean isEmpty() {
        // TODO
        boolean isEmpty;

        isEmpty = true;//Testing

        return isEmpty;
    }

}
