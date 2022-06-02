// import static org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;


public class HeapTest {

    @Test
    public void isHEAPempty() {
     	Comparator<Integer> comparator = new Comparator<Integer>() {
	    	public int compare(Integer o1, Integer o2) {
	    		return Integer.compare(o1, o2);
	    		}
     	};
    	
    	Heap newTree = new Heap(comparator);
     	newTree.add(50,50);
		System.out.println(newTree.toArray());
    	
        assertEquals(false, newTree.isEmpty());
    }


	@Test
    public void addRemoveMin() {
     	Comparator<Integer> comparator = new Comparator<Integer>() {
	    	public int compare(Integer o1, Integer o2) {
	    		return Integer.compare(o1, o2);
	    		}
     	};
    	
    	Heap newTree = new Heap(comparator);
     	// newTree.add(50,50);
		// newTree.add(40,50);
		System.out.println(newTree.poll());
		System.out.println(newTree.toArray());
    	
        assertEquals(true, newTree.isEmpty());
    }


}
