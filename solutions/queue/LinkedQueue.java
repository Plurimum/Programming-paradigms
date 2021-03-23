package queue;

public class LinkedQueue extends AbstractQueue implements Queue {

    private Node head = new Node(null, null);
    private Node tail = head;

    protected void enqueueImpl(Object element) {
        if (size == 0){
            tail = new Node(element, null);
            head = tail;
        }
        Node newNode = new Node(element, null);
        tail.next = newNode;
        tail = newNode;
    }

    protected Object dequeueImpl() {
        Object result = head.value;
        head = head.next;
        if (size == 0){
            tail = new Node(null, null);
            head = tail;
        }
        return result;
    }

    protected Object elementImpl() {
        if (size > 0) {
            return head.value;
        } else {
            System.out.println("queue is empty");
            return null;
        }
    }

    protected void clearImpl() {
        int startedSize = size;
        for (int i = 0; i < startedSize; i++) {
            dequeue();
        }
    }
}
