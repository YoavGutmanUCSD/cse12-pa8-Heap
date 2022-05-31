//import static org.junit.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import java.util.*;

import org.junit.jupiter.api.Test;


public class HeapTest {
 
    @Test
    public void areYOUEmpty() {
        assertEquals(0, 0);
    }
    
    @Test
    public void isHEAPempty() {
     	Comparator<Integer> comparator = new Comparator<Integer>() {
	    	public int compare(Integer o1, Integer o2) {
	    		return Integer.compare(o1, o2);
	    		}
     	};
    	
    	Heap newTree = new Heap(comparator);
     	newTree.add(50,50);
    	
        assertEquals(false, newTree.isEmpty());
    }
    
    @Test
    public void minOfHeap() {
     	Comparator<Integer> comparator = new Comparator<Integer>() {
	    	public int compare(Integer o1, Integer o2) {
	    		return Integer.compare(o1, o2);
	    		}
     	};
    	
    	Heap newTree = new Heap(comparator);
    	ArrayList anArray = new ArrayList();
    	anArray.add(50);
    	anArray.add(30);
     	newTree.add(50,50);
     	newTree.add(30,50);
     	newTree.add(20,50);
     	newTree.add(80,50);
     	
     	newTree.print();
     	
     	System.out.println(newTree.toArray());
    	
     	assertArrayEquals(newTree.toArray().toArray(), anArray.toArray());
    }
 

}
