package DataTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vladc
 */
public class MyLatchTable implements MyILatchTable {
    private Map<Integer, Integer> table;
    private int next_free_location;

    public MyLatchTable() {
        this.table = new HashMap<>();
        this.next_free_location = 0;
    }

    public int alloca(int value) {
        table.put(next_free_location, value);
        return next_free_location++;
    }

    public void dealloca(int address) {
        this.table.remove(address);
    }

    public int valueAt(int address) {
        return table.get(address);
    }

    public int valueAt(int address, int newValue) {
        table.put(address, newValue);
        return table.get(address);
    }

    public boolean indexExists(int index) {
        for (int i : table.keySet()) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public Map<Integer, Integer> getContent() {
        return table;
    }

    public void setContent(Map<Integer, Integer> heap) {
        this.table = heap;
    }

    @Override
    public String toString() {
        String str = "";
        for (Integer addr : table.keySet()) {
            str += addr.toString();
            str += " --> ";
            str += table.get(addr).toString();
            str += "\n";
        }
        return str;
    }
}
