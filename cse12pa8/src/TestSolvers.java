import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

class IntComparator implements Comparator<Integer> {
	public int compare(Integer a, Integer b) {
		return b - a;
	}
}


public class TestSolvers {

	/* Helper method to compare two mazes */
	public void checkMaze(PriorityQueue<Integer,Square> pq, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, pq);
		if(expected == null) { assertNull(s); }
		else {
			ArrayList<Square> sp = startMaze.storePath();
			String actualStr = formatMaze(startMaze.showSolution(sp));
			System.out.println(actualStr);
			String expectedStr = formatMaze(expected);
			System.out.println("\n" + expectedStr);
			assertEquals(expectedStr, actualStr);
		}
	}	

	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}

	/* Add your own Worklist tests below */

	/* ************** HINT ******************** 
	 * Use the helper methods to create simple
	 * tests that are easier to debug. 
	 */


	@Test
	public void testshortExample() {
		String[] mazeString = new String[] { 
				"#_#_", 
				"____", 
				"_##S", 
				"F___" 
				};
		int[][] costArray = new int[][] {	{0,0,0,0}, 
											{4,3,2,1}, 
											{5,0,0,0}, 
											{50,8,2,1} };

		Maze m = new Maze(mazeString, costArray);
		String[] queueExpected = { 
				"#_#_", 
				"____", 
				"_##S", 
				"F***", 
				};
		checkMaze(new Heap<Integer, Square>(new IntComparator()), m, queueExpected);
	}

	@Test
	public void testshortExamplePt2() {
		String[] mazeString = new String[]   {
		"___S",
		"_##_",
		"_##_",
		"___F"};
		int[][] costArray = new int[][]  {{2,2,2,0},
										{2,0,0,5},
										{2,0,0,5},
										{2,2,2,1}};

		Maze m = new Maze(mazeString, costArray);
		String[] queueExpected =   
		{
		"___S",
		"_##*",
		"_##*",
		"___F"};
		checkMaze(new Heap<Integer, Square>(new IntComparator()), m, queueExpected);

		// ArrayList<Square> sp = m.storePath();
		// PriorityQueue<Integer,Square> pq = new Heap<Integer, Square>(new IntComparator());
		// MazeSolver.solve(m, pq);
		// String actualStr = formatMaze(m.showSolution(sp));
		// System.out.println(actualStr);
	}

	@Test
	public void testMazeSolver_solution_1x8() {
		String[] mazeString = new String[]     {"F______S"};
		int[][] costArray = new int[][]    {{1,1,1,1,1,1,1,0}};

		Maze m = new Maze(mazeString, costArray);
		String[] queueExpected =   
		{"F******S"};
		checkMaze(new Heap<Integer, Square>(new IntComparator()), m, queueExpected);

		// ArrayList<Square> sp = m.storePath();
		// PriorityQueue<Integer,Square> pq = new Heap<Integer, Square>(new IntComparator());
		// MazeSolver.solve(m, pq);
		// String actualStr = formatMaze(m.showSolution(sp));
		// System.out.println(actualStr);
	}


	@Test
	public void testMazeSolver_solutionShort() {
		String[] mazeString = new String[]     {"___S",
		"_##_",
		"_##_",
		"___F"};
		int[][] costArray = new int[][]    {{2,2,2,0},
		{2,0,0,5},
		{2,0,0,5},
		{2,2,2,1}};

		Maze m = new Maze(mazeString, costArray);
		String[] queueExpected =   
		{"___S",
		"_##*",
		"_##*",
		"___F"};
		checkMaze(new Heap<Integer, Square>(new IntComparator()), m, queueExpected);

		// ArrayList<Square> sp = m.storePath();
		// PriorityQueue<Integer,Square> pq = new Heap<Integer, Square>(new IntComparator());
		// MazeSolver.solve(m, pq);
		// String actualStr = formatMaze(m.showSolution(sp));
		// System.out.println(actualStr);
	}


	@Test
	public void testMazeSolver_Solution_10x10() {
		String[] mazeString = new String[]     {
			"##S____#_#",
			"_#_#_#_#__",
			"#_##______",
			"____##__#_",
			"##_##_____",
			"__#_______",
			"##_#______",
			"_#___##___",
			"____##___#",
			"_#_##____F"
	};
		int[][] costArray = new int[][]    {
			{0,  0, 0,  6, 17,  5,  8,  0,  2,  0},
			{6,  0, 12,  0, 17,  0,  7,  0,  5,  2},
			{0, 17,  0,  0,  7,  8, 10, 11, 15,  3},
			{6, 17,  3,  4,  0,  0, 12, 10,  0,  2},
			{0,  0, 10,  0,  0,  9,  1, 10,  6,  1},
			{12, 17,  0, 10,  3,  1,  7, 16, 16,  9},
			{0,  0, 10,  0,  2,  2, 14, 18,  7, 15},
			{7,  0,  7, 15,  4,  0,  0,  9, 15, 17},
			{5, 17, 14, 19,  0,  0,  4,  2,  7,  0},
			{3,  0, 12,  0,  0,  5, 12, 13, 18, 14}
	};

		Maze m = new Maze(mazeString, costArray);
		String[] queueExpected =   
		{"##S****#_#",
		"_#_#_#*#__",
		"#_##__*___",
		"____##*_#_",
		"##_##_*___",
		"__#___*___",
		"##_#__**__",
		"_#___##*__",
		"____##_**#",
		"_#_##___*F"};
		checkMaze(new Heap<Integer, Square>(new IntComparator()), m, queueExpected);

		// ArrayList<Square> sp = m.storePath();
		// PriorityQueue<Integer,Square> pq = new Heap<Integer, Square>(new IntComparator());
		// MazeSolver.solve(m, pq);
		// String actualStr = formatMaze(m.showSolution(sp));
		// System.out.println(actualStr);
	}


	
}