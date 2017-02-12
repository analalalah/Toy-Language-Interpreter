package DataTypes;

import Exception.EmptyMyListException;

import java.util.Iterator;

/**
 * Created by vladc on 22.10.2016.
 */
public interface MyIList<T> {
    void enqueue(T e);
    T dequeue() throws EmptyMyListException ;
    boolean isEmpty();
    Iterator iterator();
}
