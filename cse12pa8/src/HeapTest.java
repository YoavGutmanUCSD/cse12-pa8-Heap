// import static org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

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
    	
        assertEquals(false, newTree.isEmpty());
    }


}
