package DataTypes;

import java.util.LinkedList;
import Exception.EmptyMyListException;

/**
 * Created by vladc on 22.10.2016.
 */
public class MyList<T> implements MyIList<T> {
    private LinkedList<T> queue;

    public MyList() {
        this.queue = new LinkedList<T> ();
    }

    public void enqueue(T e) {
        this.queue.addFirst(e);
    }

    public T dequeue() throws EmptyMyListException {
        if (queue.isEmpty())
            throw new EmptyMyListException("Exception. MyList is empty.");
        return queue.removeLast();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
