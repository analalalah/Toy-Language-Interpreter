package DataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import Exception.EmptyMyStackException;

/**
 * Created by vladc on 22.10.2016.
 */
public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<>();
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
        List<T> arr = new ArrayList<>(this.stack);
        String str = "";

        for (int i = arr.size() - 1; i > -1; i--) {
            str += arr.get(i).toString();
            str += "\n";
        }
        return str;
    }
}
