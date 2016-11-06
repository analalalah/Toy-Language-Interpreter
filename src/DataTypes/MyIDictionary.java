package DataTypes;

import Exception.InvalidKeyMyDictionaryException;

/**
 * Created by vladc on 22.10.2016.
 */
public interface MyIDictionary<K, V> {
    V get(Object key) throws InvalidKeyMyDictionaryException;
    V put(K key, V value);
    V remove(Object key) throws InvalidKeyMyDictionaryException;
//    V replace(K key, V value) throws InvalidKeyMyDictionaryException;
//    boolean containsKey(Object key);
//    boolean containsValue(Object value);
//    int size();
    boolean isEmpty();
}
