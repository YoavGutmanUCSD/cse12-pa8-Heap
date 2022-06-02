public abstract class MazeSolver {
	public static Square solve(Maze maze, PriorityQueue<Integer,Square> pq) {


		// row, column
		int[] North = {-1,0};
		int[] South = {1,0};	
		int[] East = {0,1};	
		int[] West = {0,-1};	
		
		// making a list of directions to move in for ez access
		int directionList[][] = {North, South, East, West};

		
		// 1. initialize pq to be a new empty heap // This will be done in the tester file
		// // and passed into solve as a parameter.
		// 2. add the start square's cost as the key
		// and the start square itself as the value to pq
		pq.add(maze.start.getCost(), maze.start);

		//maze.start.visit();

		// 3. while pq is not empty:
		while(!pq.isEmpty()) {
// 4.     let current = remove the first entry from pq (poll)
			Entry<Integer,Square>  current = pq.poll();
// 5.     let currentSquare = current's value
			Square currentSquare = current.value;
// 6.     Mark currentSquare as visited
			currentSquare.visit();
// 7.     if currentSquare is the finishing square
			if(currentSquare == maze.finish) {
				//System.out.println("thislookslikeYAMOM");
// 8.         return currentSquare
				return currentSquare;
			}
// 9.     else
			else {
// 10.         for each neighbor of currentSquare that isn't a wall and isn't visited

				for (int i = 0; i < directionList.length; i++) {
					// going to add these to content's index to move around..
					// current dir can be north, south, east, west
					int currentDirectionRow = directionList[i][0];
					int currentDirectionColumn = directionList[i][1];
					
					if(availableNeighbor(maze.contents, currentSquare, currentDirectionRow, currentDirectionColumn)) {
						// just setting up the neighbor
						
						// location rn
						int currentRow = currentSquare.getRow();
						int currentCol = currentSquare.getCol();
						
						// neighbor location
						int neighborRow = currentRow + currentDirectionRow;
						int neighborCol = currentCol + currentDirectionColumn;
// 11.            let currentCost = current's key plus the neighbors cost
						int currentCost = current.key + maze.contents[neighborRow][neighborCol].getCost();
// 12.            if currentCost is less than neighbor's runningCost
						if (currentCost < maze.contents[neighborRow][neighborCol].getRunningCost()) {
							maze.contents[neighborRow][neighborCol].visit();
	// 13.                set the previous of the neighbor to currentSquare
							maze.contents[neighborRow][neighborCol].setPrevious(currentSquare);
	// 14.                set the neighbors runningCost to currentCost
							maze.contents[neighborRow][neighborCol].setRunningCost(currentCost);
	// 15.                add the currentCost as key and neighbor as value to the pq (add)
							pq.add(currentCost, maze.contents[neighborRow][neighborCol]);

						}
					}
				}

			}
		}
		// 16. if the loop ended, return null (no path found)

		return null;
	}


	static boolean availableNeighbor(Square[][] contents, Square s, int rowOffset, int colOffset) {
		
		// index cannot exceed these vals
		int maxRow = contents.length;
		int maxCol = contents[0].length;
		
		// location rn
		int currentRow = s.getRow();
		int currentCol = s.getCol();
		
		// neighbor location
		int neighborRow = currentRow + rowOffset;
		int neighborCol = currentCol + colOffset;
		
		// row too big or small
		if (neighborRow >= maxRow || neighborRow < 0) {
			return false;
		}
		
		// col too big or small
		if (neighborCol >= maxCol || neighborCol < 0) {
			return false;
		}
		
		// other flags
		boolean wasVisited = contents[neighborRow][neighborCol].isVisited();
		boolean isWall = contents[neighborRow][neighborCol].getIsWall();
		
		// self explan
		if (wasVisited || isWall) {
			return false;
		}
		
		return true;
		
	}
	
}

	
