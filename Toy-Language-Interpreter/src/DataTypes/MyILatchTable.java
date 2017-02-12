package DataTypes;

import Exception.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
/**
 * @author vladc
 */
public interface MyILatchTable {
    int alloca(int value);
    void dealloca(int key);
    int valueAt(int address);
    int valueAt(int address, int newValue);
    Map<Integer, Integer> getContent();
    void setContent(Map<Integer, Integer> heap);
    String toString();
    boolean indexExists(int index);
}
