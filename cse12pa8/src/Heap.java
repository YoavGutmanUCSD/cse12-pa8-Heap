import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

// This is a heap that attempts to emulate a priority queue.
// It compares keys and sorts using a MaxHeap algorithm.
public class Heap<K,V> implements PriorityQueue {
    // instance variables
    public List<Entry<K, V>> entries = new ArrayList<Entry<K,V>>();
    public Comparator<K> comparator;

	// Constructor for heap. Only takes comparator as input.
	// This uses the given comparator to compare keys later.
	// Initializes entries and comparator.
    public Heap(Comparator comparator){
        // Constructor for the max heap
		this.entries = new ArrayList<Entry<K,V>>();
		this.comparator = comparator;
    }

    // Defining some helper methods 
	
	// Get parent
	private int getParentIndex(int index) { 
		return (index - 1) / 2; 
	}
 
    // Get left child index
    private int getLeftChildIndex(int index) { 
		return (2 * index) + 1; 
	}
 
    // Get right child index
    private int getRightChildIndex(int index){ 
		return (2 * index) + 2; 
	}

	// swapping function 
	//Takes the index of two entries and swaps them.
	private void swap(int index1, int index2) {
        Entry<K,V> elementToSwap = entries.get(index1);
        entries.set(index1, entries.get(index2));
        entries.set(index2, elementToSwap);
    }

    	// function that will make the tree satisfy max heap property 
	// should only take the highest input, index 0
	// A recursive method that moves the entry at the specified index to a larger index 
	// (down the tree) while maintaining the heap structure.
	private void bubbleDown(int index) {
        int heapSize = 0;
        if(entries.size() > 0) {
            heapSize = entries.size()-1;
        }

		// checking if its a leaf so i can end early
		if (index <= heapSize && index > (heapSize / 2)) {
            return; 
		}

		// keys to compare
		try {
			K currentKey = entries.get(index).getKey();  //(and replace heapentry with K) (or k with HeapEntry<V, K>)
			K leftKey = entries.get(getLeftChildIndex(index)).getKey();
			K rightKey = entries.get(getRightChildIndex(index)).getKey();
		} catch(Exception IndexOutOfBoundsException) {
			return;
		}

		// pain, fix later
		K currentKey = entries.get(index).getKey();  //(and replace heapentry with K) (or k with HeapEntry<V, K>)
		K leftKey = entries.get(getLeftChildIndex(index)).getKey();
		K rightKey = entries.get(getRightChildIndex(index)).getKey();
		
		// how compare works:
		// compare(Object obj1, Object obj2)
		// if obj1 > obj2, return positive value
		// if obj1 < obj2, return negative value
		// if obj1 = obj2, return 0

		// current key compared to the left child key (is left greater than currnet)
		int leftCurrentCompared = comparator.compare(leftKey, currentKey);
		boolean leftGreaterThanCurrent = leftCurrentCompared > 0;

		// current key compared to the right child key  (is right greater than current)
		int rightCurrentCompared = comparator.compare(rightKey, currentKey);
		boolean rightGreaterThanCurrent = rightCurrentCompared > 0;

		// left key compared to right child key  key (is left greater than right)
		int leftRightCompared = comparator.compare(leftKey, rightKey);
		boolean leftGreaterThanRight = leftRightCompared > 0;

		// does the current parent need to be swapped?
        if (leftGreaterThanCurrent|| rightGreaterThanCurrent) {

			// if the left child is greater than the right child,
			// then I will swap the left with the parent
            if (leftGreaterThanRight) {
                swap(index, getLeftChildIndex(index));
                bubbleDown(getLeftChildIndex(index));
			} 
			
			// otherwise, swap the right with the parent 
			// (which is what should happen if right=left)
			else {
                swap(index, getRightChildIndex(index));
                bubbleDown(getRightChildIndex(index));
            }

        }

    }

	// should take the lowest input! such as the size of entries.
	// A recursive method that moves the entry at the specified index to a smaller index (up the tree) while maintaining the heap structure. 
	private void bubbleUp(int index) {
        int heapSize = 0;
        if(entries.size() > 0) {
            heapSize = entries.size()-1;
        }

		// checV if the index is 0 so i can end early (cant go up more than 0)
		if(index == 0) {
			return;
		}

		// not sure if this is entirely necessary
		int currentIndex = index;

		// keys to compare (and replace heapentry with K) HeapEntry<V, K> 
		K currentKey = entries.get(currentIndex).getKey();
		K parentKey = entries.get(getParentIndex(currentIndex)).getKey();


		// current key compared to parent key key (is current greater than parent)
		int currentParentCompared = comparator.compare(currentKey, parentKey);
		boolean currentGreaterThanParent = currentParentCompared > 0;
		//System.out.println(getParentIndex(currentIndex));

		if(!currentGreaterThanParent) {
			return;
		}

		// swap (FIX THIS INTO WHILE LOOP IF NEEDED)
		else {
			//int tempParentIndex = getParentIndex(currentIndex);
			swap(currentIndex, getParentIndex(currentIndex));

			// currentIndex = tempParentIndex;
			if(getParentIndex(currentIndex) == 0) {
				return;
			}

			bubbleUp(getParentIndex(currentIndex));
		}

	}

    @Override
	// Insert a new entry with the given key and value to the end of the heap. 
	// Then, bubbleUp so that the heap properties are not violated
    public void add(Object k, Object v) {
		if(k == null) {
			return;
		}

        Entry<K,V> newEntry = new Entry(k, v);

        int heapSize = 0;
        if(entries.size() > 0) {
            heapSize = entries.size()-1;
        }

        //if(heapSize < entries.size()+1) {
			entries.add(newEntry);
		//} 
		// // NOT SURE IF THIS IS PROPER BEHAVIOR
		// // returning and adding nothing if its at full capacity
		// else {return;}

		// FIX THIS IF NEEDED
		bubbleUp(heapSize);
        
    }

    @Override
	// Remove and return the root element in the heap.
	// Set the last entry in the heap to the root. 
	// Use bubbleDown to fix the heap after the removal. 
	// If the size is zero, throw NoSuchElementException()
    public Entry<K, V> poll() throws NoSuchElementException {
		
        //Method to remove the max element in the heap, remember to satisfy max heap Property
        int heapSize = 0;
        if(entries.size() > 0) {
            heapSize = entries.size()-1;
        } else {
			throw new NoSuchElementException();
		}
        
        Entry<K,V> maxEntry = peek();
		Entry<K,V> minEntry = entries.get(entries.size() -1);

		//ending early if it's already empty
		if (heapSize == 0) {
			return maxEntry;
		}

		// size goes down when remove

		entries.remove(0);

		// i will be re-adding the smallest so I can bubbleDown effectively, 
		// but i must remove it first
		if (entries.size() -1 != -1) {
			entries.remove(entries.size() -1);
		}

		entries.add(0,minEntry);

		bubbleDown(0);
		bubbleUp(entries.size()-1);

		return maxEntry;
    }

    @Override
	// Return the root element of the heap. 
	// If the size is zero, throw NoSuchElementException()
    public Entry<K, V> peek() {
        if(entries.size() <= 0) {
			throw new NoSuchElementException();
		}

		// just in case, i'm going to bubble down...
		if (entries.get(0) != null) {
			bubbleDown(0); 
		}
		return entries.get(0);
    }

    @Override
	// Return the list of entries.
    public List<Entry<K,V>> toArray() {
		bubbleDown(0);
		bubbleUp(entries.size()-1);
        return entries;
    }

    @Override
	// If the List of entries is empty, return true. Otherwise, return false.
    public boolean isEmpty() {
        return entries.size() == 0;
    }
    
}

// Holds entries for the heap.
// Has the key to a value and a toString method.
class Entry<K, V> {
    K key; // aka the _priority_
    V value;
    public Entry(K k, V v) { this.key = k; this.value = v; }
    public String toString() {
        return key + ": " + value;
    }

    public K getKey() {
		return key;
	}

}



