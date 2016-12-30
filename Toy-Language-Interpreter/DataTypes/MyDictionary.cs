using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Toy_Language_Interpreter.MyException;

namespace Toy_Language_Interpreter.DataTypes
{
    class MyDictionary<K, V> : MyIDictionary<K, V>
    {
        private Dictionary<K, V> dict;

        public MyDictionary()
        {
            this.dict = new Dictionary<K, V>();
        }

        public void Clone(MyIDictionary<K, V> toClone)
        {
            dict = new Dictionary<K, V>();
            foreach (K k in toClone.KeySet())
            {
                this.dict[k] = toClone.Get(k);
            }
        }

        public Dictionary<K, V> GetDict()
        {
            return this.dict;
        }

        public V Get(K key)
        {
            if (!dict.ContainsKey(key))
                throw new InvalidKeyMyDictionaryException("Invalid key: " + key.ToString());
            return dict[key];
        }

        public V Put(K key, V value)
        {
            dict[key] = value;
            return dict[key];
        }

        public bool Remove(K key)
        {
            if (!dict.ContainsKey(key))
                throw new InvalidKeyMyDictionaryException("Invalid key: " + key.ToString());
            return dict.Remove(key);
        }

        public bool ContainsKey(K key)
        {
            return dict.ContainsKey(key);
        }

        public bool ContainsValue(V value)
        {
            return dict.ContainsValue(value);
        }

        public List<K> KeySet()
        {
            return new List<K>(dict.Keys);
        }

        public List<V> Values()
        {
            return new List<V>(dict.Values);
        }

        public int Size()
        {
            return dict.Count;
        }

        public bool IsEmpty()
        {
            return dict.Count == 0;
        }

        public override string ToString()
        {
            String str = "";
            
            foreach(K k in dict.Keys)
            {
                str += k.ToString();
                str += " --> ";
                str += dict[k].ToString();
                str += "\n";
            }
            return str;
        }
    }
}
