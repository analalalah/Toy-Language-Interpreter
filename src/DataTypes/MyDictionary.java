package DataTypes;

import java.util.HashMap;
import Exception.InvalidKeyMyDictionaryException;

/**
 * Created by vladc on 22.10.2016.
 */
public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private HashMap<K, V> dict;

    public MyDictionary() {
        dict = new HashMap<K, V> ();
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

//    public boolean containsKey(Object key) {
//        return dict.containsKey(key);
//    }
//
//    public boolean containsValue(Object value) {
//        return dict.containsValue(value);
//    }
//
//    public int size() {
//        return dict.size();
//    }

    public boolean isEmpty() {
        return dict.isEmpty();
    }

    @Override
    public String toString() {
        return dict.toString();
    }
}
