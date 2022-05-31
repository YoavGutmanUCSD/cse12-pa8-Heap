public class Heap {
    
}

class Entry<K, V> {
    K key; // aka the _priority_
    V value;
    public Entry(K k, V v) { this.key = k; this.value = v; }
    public String toString() {
        return key + ": " + value;
    }
}