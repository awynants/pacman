package pacmanTest;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.MazeMap;
import pacman.Square;

public class SquareTest {
	
	@Test
	void test() {
		int num = 2;
		MazeMap testMazeMap = new MazeMap(5, 3, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square testSquare = Square.of(testMazeMap, 1, num);
		Square testSquare2 = Square.of(testMazeMap, 0, 4);
		Square testSquare3 = Square.of(testMazeMap, 2, 0);
		MazeMap testMazeMapCopy = new MazeMap(5, 3, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square testSquareMazeMapCopy = Square.of(testMazeMapCopy, 1, 2);
		
		//tests of getter methods
		assert testSquare.getMazeMap() == testMazeMap;
		assert testSquare.getMazeMap() != testMazeMapCopy;
		assert testSquare.getColumnIndex() == 2;
		num+=1;
		assert testSquare.getColumnIndex() == 2;
		assert testSquare.getRowIndex() == 1;
		
		//tests of isPassable()
		assert testSquare.isPassable() == false;
		assert testSquare2.isPassable() == true;
		
		//tests of getNeighbor(Direction direction)
		assert testSquare.getNeighbor(Direction.UP).equals(Square.of(testMazeMap, 0, 2));
		assert testSquare.getNeighbor(Direction.DOWN).equals(Square.of(testMazeMap, 2, 2));
		assert testSquare.getNeighbor(Direction.LEFT).equals(Square.of(testMazeMap, 1, 1));
		assert testSquare.getNeighbor(Direction.RIGHT).equals(Square.of(testMazeMap, 1, 3));
		
		assert testSquare2.getNeighbor(Direction.UP).equals(Square.of(testMazeMap, 2, 4));
		assert testSquare2.getNeighbor(Direction.RIGHT).equals(Square.of(testMazeMap, 0, 0));
		
		assert testSquare3.getNeighbor(Direction.DOWN).equals(Square.of(testMazeMap, 0, 0));
		assert testSquare3.getNeighbor(Direction.LEFT).equals(Square.of(testMazeMap, 2, 4));
		
		//tests of canMove(Direction direction)
		assert testSquare2.canMove(Direction.DOWN) == false;
		assert testSquare2.canMove(Direction.LEFT) == false;
		assert testSquare2.canMove(Direction.RIGHT) == false;
		assert testSquare2.canMove(Direction.UP) == true;
		
		assert testSquare.canMove(Direction.UP) == true;
		assert testSquare.canMove(Direction.DOWN) == true;
		assert testSquare.canMove(Direction.LEFT) == true;
		assert testSquare.canMove(Direction.RIGHT) == true;
		
		assert testSquare3.canMove(Direction.UP) == true;
		assert testSquare3.canMove(Direction.DOWN) == false;
		assert testSquare3.canMove(Direction.LEFT) == true;
		assert testSquare3.canMove(Direction.RIGHT) == true;
		
		//tests of getPassableDirectionsExcept(Direction excludedDirection)
		Direction[] testDirections = testSquare.getPassableDirectionsExcept(Direction.LEFT);
		Direction[] testDirections2 = testSquare2.getPassableDirectionsExcept(Direction.UP);
		Direction[] testDirections3 = testSquare3.getPassableDirectionsExcept(Direction.RIGHT);
		
		assert testDirections.length == 3;
		assert testDirections[0] == Direction.RIGHT;
		assert testDirections[1] == Direction.UP;
		assert testDirections[2] == Direction.DOWN;
		
		assert testDirections2.length == 0;
		
		assert testDirections3.length == 2;
		assert testDirections3[0] == Direction.LEFT;
		assert testDirections3[1] == Direction.UP;
		
		//tests of equals(Square other)
		//this function has already been summoned a few times in previous tests, but a few aspects of it have yet to be tested.
		Square testSquareCopy = Square.of(testMazeMap, 1, 2);
		Square testSquareRowCopy = Square.of(testMazeMap, 0, 2);
		Square testSquareColumnCopy = Square.of(testMazeMap, 1, 3);
		
		assert testSquare.equals(testSquareCopy);
		assert !(testSquare2.equals(testSquareCopy));
		
		assert !(testSquare.equals(testSquareMazeMapCopy));
		assert !(testSquare.equals(testSquareRowCopy));
		assert !(testSquare.equals(testSquareColumnCopy));
	}
}
