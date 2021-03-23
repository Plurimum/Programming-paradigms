package queueonarray;

import java.lang.*;

public class ArrayQueueADT {
    private int size;
    private int head;
    private int myCapacity = 5;
    private Object[] elements = new Object[myCapacity];
    //INV: (size > 0 && queue[0...size - 1] != null) || size == 0

    //PRE: element != null
    //POST: queue[size] = element && size` = size + 1
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        queue.elements[(queue.head + queue.size) % queue.elements.length] = element;
        ensureCapacity(queue, queue.size + 1);
        queue.size++;
    }

    //POST: result[i] = queue[i] && i = [0..size]
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {

        if ((capacity * 4 > queue.elements.length && capacity < queue.elements.length) || capacity < queue.myCapacity) {
            return;
        } else {
            int newSize = 0;
            if (capacity == queue.elements.length) {
                newSize = queue.elements.length * 2;
            } else {
                if (capacity * 4 <= queue.elements.length) {
                    newSize = queue.elements.length / 2;
                }
            }
            Object[] newArray = new Object[newSize];

            int tail = (queue.head + capacity - 1) % queue.elements.length;
            if (queue.head < tail){
                System.arraycopy(queue.elements, queue.head, newArray, 0, capacity);
            } else {
                System.arraycopy(queue.elements, queue.head, newArray, 0, queue.elements.length - queue.head);
                System.arraycopy(queue.elements, 0, newArray, queue.elements.length - queue.head, tail + 1);
            }
            queue.elements = newArray;
            queue.head = 0;
        }
    }

    //POST: result = size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    //POST: result = (size == 0)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    //POST: size = 0
    public static void clear(ArrayQueueADT queue) {
        for (int i = queue.head; i < queue.size + queue.head; i++) {
            dequeue(queue);
        }
    }

    //PRE: size > 0
    //POST: result = queue[0] && elements[i] = elements[i + 1] && size` = size - 1
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size != 0;
        Object result = element(queue);
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        ensureCapacity(queue, queue.size - 1);
        queue.size--;
        return result;
    }

    //PRE: size > 0
    //POST: result = queue[0]
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.head];
    }

    //POST: result[i] = queue[i]
    public static Object[] toArray(ArrayQueueADT queue){
        Object[] newArray = new Object[queue.size];
        for (int i = 0; i < queue.size; i++) {
            newArray[i] = dequeue(queue);
            enqueue(queue, newArray[i]);
        }
        return newArray;
    }

    //POST: result = concat(queue[0]..queue[size - 1])
    public static String toStr(ArrayQueueADT queue){
        Object[] array = toArray(queue);
        int newSize = queue.size;
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < newSize; i++){
            result.append(array[i].toString()).append(", ");
        }
        if (result.length() > 1) {
            result.deleteCharAt(result.lastIndexOf(","));
            result.deleteCharAt(result.lastIndexOf(" "));
        }
        result.append("]");
        return result.toString();
    }


    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null;
        queue.head = (queue.head - 1 + queue.elements.length) % queue.elements.length;
        queue.elements[queue.head] = element;
        ensureCapacity(queue, queue.size + 1);
        queue.size++;
    }

    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[(queue.head + queue.size - 1 + queue.elements.length) % queue.elements.length];
    }

    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object result = peek(queue);
        queue.elements[(queue.head + queue.size - 1) % queue.elements.length] = null;
        ensureCapacity(queue,queue.size - 1);
        queue.size--;
        return result;
    }
/*
    public void push(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue,size + 1);
        queue.size++;
        queue.elements[queue.head] = element;
        queue.head = (queue.head + 1) % queue.myCapacity;
    }

    public Object peek(ArrayQueueADT queue){
        assert queue.size != 0;
        return queue.elements[queue.tail];
    }

    public Object remove(ArrayQueueADT queue){
        assert queue.size > 0;
        queue.size--;
        Object forReturn = queue.elements[queue.tail];
        queue.tail = (queue.tail + 1) % queue.myCapacity;
        return forReturn;
    }
    */
}
