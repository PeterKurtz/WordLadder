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

    public Queue() {this.enqueuedSize = 0; tail = head;}

    public int getEnqueuedSize() {return this.enqueuedSize;}

    public void enqueue(E newValue) {

        QueueNode<E> node = new QueueNode<>(newValue);
        QueueNode<E> current = head.next;
        QueueNode<E> previous = head;

        while (current != null) {
            previous = current;
            current = current.next;
        }

        previous.next = node;
        node.next = current;
        tail = node;

        this.enqueuedSize++;

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
