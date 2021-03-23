package queueonarray;

import java.lang.*;

public class ArrayQueueModule {
    private static int size;
    private static int head;
    private static int myCapacity = 5;
    private static Object[] elements = new Object[myCapacity];
    //INV: (size > 0 && queue[0...size - 1] != null) || size == 0

    //PRE: element != null
    //POST: elements[size] = element && size` = size + 1 && tail' = (tail + 1) % myCapacity
    //POST i =0..size - 1 elements[i]' = elements[i]
    public static void enqueue(Object element) {
        assert element != null;
        elements[(head + size) % elements.length] = element;
        ensureCapacity(size + 1);
        size++;
    }

    //PRE: myCapacity >= 0
    //POST: result[i] = queue[i] && i = [0..size]
    private static void ensureCapacity(int capacity) {

        if ((capacity * 4 > elements.length && capacity < elements.length) || capacity < myCapacity) {
            return;
        } else {
            int newSize = 0;
            if (capacity == elements.length) {
                newSize = elements.length * 2;
            } else {
                if (capacity * 4 <= elements.length) {
                    newSize = elements.length / 2;
                }
            }
            Object[] newArray = new Object[newSize];

            int tail = (head + capacity - 1) % elements.length;
            if (head < tail){
                System.arraycopy(elements, head, newArray, 0, capacity);
            } else {
                System.arraycopy(elements, head, newArray, 0, elements.length - head);
                System.arraycopy(elements, 0, newArray, elements.length - head, tail + 1);
            }
            elements = newArray;
            head = 0;
        }
    }

    //POST: result = size
    public static int size() {
        return size;
    }

    //POST: result = (size == 0)
    public static boolean isEmpty() {
        return size == 0;
    }

    //POST: size = 0
    public static void clear() {
        for (int i = head; i < size + head; i++) {
            dequeue();
        }
    }

    //PRE: size > 0
    //POST: result = queue[0] && elements[i] = elements[i + 1] && size` = size - 1
    public static Object dequeue() {
        assert size != 0;
        Object result = element();
        elements[head] = null;
        head = (head + 1) % elements.length;
        ensureCapacity(size - 1);
        size--;
        return result;
    }

    //PRE: size > 0
    //POST: result = queue[0]
    public static Object element() {
        assert size > 0;
        return elements[head];
    }

    //POST: result[i] = queue[i]
    public static Object[] toArray(){
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = dequeue();
            enqueue(newArray[i]);
        }
        return newArray;
    }

    //POST: result = concat(queue[0]..queue[size - 1])
    public static String toStr(){
        Object[] array = toArray();
        int newSize = size;
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


    public static void push(Object element) {
        assert element != null;
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = element;
        ensureCapacity(size + 1);
        size++;
    }

    public static Object peek() {
        assert size > 0;
        return elements[(head + size - 1 + elements.length) % elements.length];
    }

    public static Object remove() {
        assert size > 0;
        Object result = peek();
        elements[(head + size - 1) % elements.length] = null;
        ensureCapacity(size - 1);
        size--;
        return result;
    }
}
