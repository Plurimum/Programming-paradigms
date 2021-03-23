package queue;

import java.lang.*;

public class ArrayQueue extends AbstractQueue implements Queue{
    private int head;
    private int myCapacity = 5;
    private int tail;
    private Object[] elements = new Object[myCapacity];

    public void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        this.elements[this.tail] = element;
        this.tail = (this.tail + 1) % this.myCapacity;
    }

    private void ensureCapacity(int capacity) {
        if (capacity < this.elements.length) {
            return;
        }
        int newCapacity = this.elements.length;
        while (newCapacity < capacity) {
            newCapacity *= 2;
        }
        Object[] newElements = new Object[newCapacity];
        if (this.tail > this.head) {
            System.arraycopy(this.elements, this.head, newElements, 0, this.size);
        } else {
            System.arraycopy(this.elements, this.head, newElements, 0, this.myCapacity - this.head);
            System.arraycopy(this.elements, 0, newElements, this.myCapacity - this.head, this.tail);
        }
        this.myCapacity = newCapacity;
        this.elements = newElements;
        this.head = 0;
        this.tail = this.size;
    }

    protected void clearImpl() {
        for (int i = 0; i < this.size; i++) {
            dequeue();
        }
    }

    protected Object dequeueImpl() {
        Object forReturn = this.elements[this.head];
        this.head = (this.head + 1) % this.myCapacity;
        return forReturn;
    }

    protected Object elementImpl() {
        return this.elements[this.head];
    }
    public Object[] toArray(){
        int headAtStart = this.head, tailAtStart = this.tail, sizeAtStart = this.size, size = this.size;
        Object[] returnedArray = new Object[this.size];
        for (int i = 0; i < this.size; i++){
            returnedArray[i] = dequeue();
        }
        this.head = headAtStart;
        this.tail = tailAtStart;
        this.size = sizeAtStart;
        return returnedArray;
    }
}
