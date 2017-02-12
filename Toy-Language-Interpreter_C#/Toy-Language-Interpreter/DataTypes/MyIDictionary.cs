using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_Interpreter.DataTypes
{
    interface MyIDictionary<K, V>
    {
        void Clone(MyIDictionary<K, V> toClone);
        Dictionary<K, V> GetDict();
        V Get(K key);
        V Put(K key, V value);
        bool Remove(K key);
        bool ContainsKey(K key);
        bool ContainsValue(V value);
        List<K> KeySet();
        List<V> Values();
        int Size();
        bool IsEmpty();
        string ToString();
    }
}
