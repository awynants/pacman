package pacmanTest;

import org.junit.jupiter.api.Test;

import pacman.Dot;
import pacman.MazeMap;
import pacman.Square;

class DotTest {

	@Test
	void test() {
		int num = 2;
		MazeMap testMazemap = new MazeMap(3, 5, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square testSquare = Square.of(testMazemap, 1, num);
		Dot testDot = new Dot(testSquare);
		MazeMap testMazemapcopy = new MazeMap(3, 5, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square testSquarecopy = Square.of(testMazemapcopy, 1, num);
		
		//test of getSquare()
		assert testDot.getSquare() == testSquare;
		assert testDot.getSquare() != testSquarecopy;
		num += 1;
		assert testDot.getSquare() == testSquare;
		assert testDot.getSquare() != testSquarecopy;
	}

}
