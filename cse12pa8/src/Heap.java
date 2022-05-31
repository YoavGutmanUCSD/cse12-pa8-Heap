
import java.util.*;

public class MaxHeap<K, V> {

    List<HeapEntry<K,V>> entries;
    int capacity;
    int heapSize = 0;
    Comparator comparator;

    public MaxHeap(int capacity, Comparator comparator){
        // Constructor for the max heap
        this.capacity = capacity;
        this.comparator = comparator;
        this.entries = new ArrayList<HeapEntry<K,V>>(capacity);
        this.entries.add(null);
    }

    public void add(K key, V value){
        // Method to add the key value pair in the heap, remember to satisfy max heap Property
        HeapEntry<K,V> current = new HeapEntry<K,V>(key, value);
        HeapEntry<K,V> temp;
        if(this.entries.size() == 1){
            entries.add(current);
            return;
        }
        int comparison = compare(entries.get(1), current);
        if(comparison < 0){
            temp = entries.get(1);
            entries.set(1, current);
            refactor(1, temp);
        }
        else {
            refactor(1, current);
        }
    }

    public HeapEntry<K,V> peek() {
        // Method to return the max element in the heap
        return entries.get(1);
    }

    public HeapEntry<K,V> remove() {
        //Method to remove the max element in the heap, remember to satisfy max heap Property
        HeapEntry<K, V> toReturn = this.entries.remove(1);
        // this.entries.set(1, null);
        refactor(1);
        return toReturn;
    }
    // because i don't want to write "comparator.compare() every time"
    // private int compare(K entry1, K entry2){
    //     return comparator.compare(entry1, entry2);
    // }
    private int compare(HeapEntry<K,V> entry1, HeapEntry<K,V> entry2){
        return comparator.compare(entry1.getValue(), entry2.getValue());
    }
    // this refactor is intended to fix a branch that might be broken
    private void refactor(int index){
        // System.out.format("Refactor iteration running on index %s", index);
        int leftIndex = index*2;
        int rightIndex = leftIndex+1;
        HeapEntry<K, V> leftEntry;
        HeapEntry<K, V> rightEntry;
        HeapEntry<K, V> current = this.entries.get(index);
        int compareLeft;
        int compareRight;
        if(leftIndex >= this.entries.size()) return;
        if(rightIndex >= this.entries.size()) {
            leftEntry = entries.get(leftIndex);
            compareLeft = compare(current, leftEntry);
            if(compareLeft < 0){
                swap(leftIndex, index);
                refactor(leftIndex);
            }
        }
        else {
            leftEntry = entries.get(leftIndex);
            rightEntry = entries.get(rightIndex);
            current = this.entries.get(index);
            compareLeft = compare(current, leftEntry);
            compareRight = compare(current, rightEntry);
            if(compareLeft < 0){
                swap(leftIndex, index);
                refactor(leftIndex);
            }
            else if(compareRight < 0) {
                swap(rightIndex, index);
                refactor(rightIndex);
            }
        }
    }
    // this version of refactor is intended to insert a value
    private void refactor(int index, HeapEntry<K,V> relocateTarget){
        if(relocateTarget == null){
            return;
        }
        // System.out.format("Refactor iteration running on index %s, using object with values (%s, %s)\n", index, relocateTarget.getKey(), relocateTarget.getValue());
        int leftIndex = index*2;
        int rightIndex = leftIndex+1;
        if(leftIndex >= entries.size()) {
            entries.add(relocateTarget);
            return;
        }
        if(rightIndex >= entries.size()){
            refactor(leftIndex, operateOnIndex(leftIndex, relocateTarget));
            return;
        }
        HeapEntry<K,V> leftOperate = operateOnIndex(leftIndex, relocateTarget);
        if(leftOperate != null){
            refactor(leftIndex, leftOperate);
        }
        else {
            HeapEntry<K,V> rightOperate = operateOnIndex(rightIndex, relocateTarget);
            if(rightOperate != null){
                refactor(rightIndex, rightOperate);
            }
        }
    }
    private HeapEntry<K,V> operateOnIndex(int index, HeapEntry<K,V> relocateTarget){
        if(index >= entries.size()){
            return null;
        }
        HeapEntry<K,V> entry = entries.get(index);
        int comparison = compare(entry, relocateTarget);
        if(comparison < 0){
            HeapEntry<K,V> newRelocateTarget = entries.get(index);
            entries.set(index, relocateTarget);
            return newRelocateTarget;
        }
        if(comparison == 0){
            return relocateTarget;
        }
        return null;
    }
    private boolean swap(int start, int end){
        if(start >= entries.size() || end >= entries.size()) {return false;}
        HeapEntry<K, V> temp = this.entries.get(start);
        entries.set(start, entries.get(end));
        entries.set(end, temp);
        return true;
    }

    // public String toString(){
    //     String toReturn = "[";
    //     for(int i = 1; i < this.entries.size()-1; i++){
    //         toReturn += String.format("%s, ", this.entries.get(i).getKey());
    //     }
    //     toReturn += String.format("%s]", this.entries.get(this.entries.size()-1).getKey());
    //     return toReturn;
    // }
    // public boolean sanityCheck(){
    //     return sane(1);
    // }
    private boolean sane(int index){
        if(index * 2 >= entries.size()){
            return true;
        }
        if(index * 2 + 1 >= entries.size()){
            HeapEntry<K,V> curr = entries.get(index);
            HeapEntry<K,V> left = entries.get(index*2);
            return compare(curr, left) >= 0;
        }
        HeapEntry<K,V> curr = entries.get(index);
        HeapEntry<K,V> left = entries.get(index*2);
        HeapEntry<K,V> right = entries.get(index*2+1);
        return compare(curr, left) >= 0 & compare(curr, right) >= 0 & sane(index*2) & sane(index*2+1);
    }
}

class HeapEntry<K, V> implements DefaultMap.Entry<K,V>{
    K key;
    V value;

    HeapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value){
        this.value = value;
    }
}
