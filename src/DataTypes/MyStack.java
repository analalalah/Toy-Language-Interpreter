package DataTypes;

import java.util.Stack;
import Exception.EmptyMyStackException;

/**
 * Created by vladc on 22.10.2016.
 */
public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    public T pop() throws EmptyMyStackException {
        if (stack.isEmpty())
            throw new EmptyMyStackException("Exception. MyStack is empty.");
        return stack.pop();
    }

    public void push(T value) {
        stack.push(value);
    }

    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
