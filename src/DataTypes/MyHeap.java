package DataTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vladc on 19.11.2016.
 */
public class MyHeap implements MyIHeap {
    private Map<Integer, Integer> heap;
    private int next_free_location;

    public MyHeap() {
        this.heap = new HashMap<>();
        this.next_free_location = 1;
    }

    public int alloca(int value) {
        heap.put(next_free_location, value);
        return next_free_location++;
    }

    public void dealloca(int address) {
        this.heap.remove(address);
    }

    public int valueAt(int address) {
        return heap.get(address);
    }

    public int valueAt(int address, int newValue) {
        heap.put(address, newValue);
        return heap.get(address);
    }

    public Map<Integer, Integer> getContent() {
        return heap;
    }

    public void setContent(Map<Integer, Integer> heap) {
        this.heap = heap;
    }
    
    @Override
    public String toString() {
        String str = "";
        for (Integer addr : heap.keySet()) {
            str += addr.toString();
            str += " --> ";
            str += heap.get(addr).toString();
            str += "\n";
        }
        return str;
    }
}
