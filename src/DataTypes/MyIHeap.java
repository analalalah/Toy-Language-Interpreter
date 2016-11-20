package DataTypes;

import java.util.Map;

/**
 * Created by vladc on 19.11.2016.
 */
public interface MyIHeap {
    int alloca(int value);
    void dealloca(int key);
    int valueAt(int address);
    int valueAt(int address, int newValue);
    Map<Integer, Integer> getContent();
    void setContent(Map<Integer, Integer> heap);
    String toString();
}
