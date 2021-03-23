package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue(){
        //assert size > 0;
        if (size > 0) {
            size--;
            return dequeueImpl();
        } else {
            System.out.println("can't dequeue");
            return null;
        }
    }

    protected abstract Object dequeueImpl();

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public void clear(){
        clearImpl();
    }
    protected abstract void clearImpl();
}
