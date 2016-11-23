package DataTypes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;
import Exception.EmptyMyListException;

/**
 * Created by vladc on 22.10.2016.
 */
public class MyList<T> implements MyIList<T>, Serializable {
    private ArrayList<T> queue;

    public MyList() {
        this.queue = new ArrayList<> ();
    }

    public void enqueue(T e) {
        this.queue.add(e);
    }

    public T dequeue() throws EmptyMyListException {
        if (queue.isEmpty())
            throw new EmptyMyListException("Exception. MyList is empty.");
        T ret = this.queue.get(0);
        this.queue.remove(0);
        return ret;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";

        for (T t : this.queue) {
            str += t.toString();
            str += "\n";
        }
        return str;
    }
}
