package DataTypes;


import Exception.EmptyMyStackException;
/**
 * Created by vladc on 22.10.2016.
 */
public interface MyIStack<T> {
    T pop() throws EmptyMyStackException;
    void push(T value);
    boolean isEmpty();
}
