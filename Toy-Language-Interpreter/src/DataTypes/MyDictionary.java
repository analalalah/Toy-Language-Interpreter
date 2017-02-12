package DataTypes;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Exception.InvalidKeyMyDictionaryException;

/**
 * Created by vladc on 22.10.2016.
 */
public class MyDictionary<K, V> implements MyIDictionary<K, V>, Serializable {
    private Map<K, V> dict;

    public MyDictionary() {
        dict = new HashMap<>();
    }

    public void clone(MyIDictionary<K, V> toClone) {
        dict = new HashMap<>();
        for (K k : toClone.keySet()) {
            this.dict.put(k, toClone.getDict().get(k));
        }
    }

    public Map<K, V> getDict() {
        return this.dict;
    }

    public V get(Object key) throws InvalidKeyMyDictionaryException {
        if (!dict.containsKey(key))
            throw new InvalidKeyMyDictionaryException();
        return dict.get(key);
    }

    public V put(K key, V value) {
        if (!dict.containsKey(key))
            return dict.put(key, value);
        return dict.replace(key, value);
    }

    public V remove(Object key) throws InvalidKeyMyDictionaryException {
        if (!dict.containsKey(key))
            throw new InvalidKeyMyDictionaryException();
        return dict.remove(key);
    }

    public V replace(K key, V value) throws InvalidKeyMyDictionaryException {
        if (!dict.containsKey(key))
            throw new InvalidKeyMyDictionaryException();
        return dict.replace(key, value);
    }

    public boolean containsKey(Object key) {
        return dict.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return dict.containsValue(value);
    }

    public Set<K> keySet() {
        return dict.keySet();
    }

    public Collection<V> values() {
        return dict.values();
    }

    public int size() {
        return dict.size();
    }

    public boolean isEmpty() {
        return dict.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        for (K k : this.dict.keySet()) {
            str += k.toString();
            str += " --> ";
            str += this.dict.get(k).toString();
            str += "\n";
        }
        return str;
    }
}
