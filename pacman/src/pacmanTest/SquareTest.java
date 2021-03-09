package pacmanTest;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;

public class SquareTest {
	
	@Test
	void test() {
		int num = 2;
		MazeMap testMazeMap = new MazeMap(3, 5, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square testSquare = Square.of(testMazeMap, 1, num);
		
		assert testSquare.getMazeMap() == testMazeMap;
		assert testSquare.getColumnIndex() == 2;
		num+=1;
		assert testSquare.getColumnIndex() == 2;
		assert testSquare.getRowIndex() == 1;
		assert testSquare.isPassable() == false;
		Square testSquare2 = Square.of(testMazeMap, 0, 4);
		assert testSquare2.isPassable() == true;
		
	}
}
