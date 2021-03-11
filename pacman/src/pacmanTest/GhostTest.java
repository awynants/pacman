package pacmanTest;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.MazeMap;
import pacman.Square;
import pacman.Ghost;

public class GhostTest {
	
	@Test
	void test() {
		MazeMap testMazeMap = new MazeMap(5, 3, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square testSquare = Square.of(testMazeMap, 1, 1);
		Ghost testGhost = new Ghost(testSquare, Direction.LEFT);
		
		assert testGhost.getSquare() == testSquare;
		assert testGhost.getDirection() == Direction.LEFT;
		
		Square testSquare2 = Square.of(testMazeMap, 0, 4);
		testGhost.setSquare(testSquare2);
		assert testGhost.getSquare() == testSquare2;
		assert testGhost.getSquare() != testSquare;
		assert testGhost.getDirection() == Direction.LEFT;
		
		testGhost.setDirection(Direction.UP);
		assert testGhost.getDirection() == Direction.UP;
		assert testGhost.getDirection() != Direction.LEFT;
		assert testGhost.getSquare() == testSquare2;
		
		//The last two methods don't have to be tested because they were given, and the assignment stated we only have to test methods we wrote
	}
}
