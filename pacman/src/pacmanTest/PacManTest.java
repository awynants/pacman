package pacmanTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class PacManTest {

	@Test
	void test() {
		int num = 3; 
		MazeMap testMazeMap = new MazeMap(4, 4, new boolean[] {false, false, true, true, true, true, true, true, false, false, false, true, true, true, true, true});
		Square testSquare = Square.of(testMazeMap, 1, 0);
		Square testSquare2 = Square.of(testMazeMap, 1, 1);
		Square testSquare3 = Square.of(testMazeMap, 1, 3);
		Square testSquare4 = Square.of(testMazeMap, 0, 3);
		Square testSquare5 = Square.of(testMazeMap, 3, 3);
		PacMan myPacman = new PacMan(num, testSquare);
		PacMan myPacman2 = new PacMan(5, testSquare4);
		
		//test of getters
		assert myPacman.getNbLives() == 3;
		num += 1;
		assert myPacman.getNbLives() == 3;
		assert myPacman.getSquare() == testSquare;
		
		//test of setSquare(Square square)
		myPacman.setSquare(testSquare2);
		assert myPacman.getSquare() == testSquare2;
		assert myPacman.getNbLives() == 3;
		myPacman.setSquare(testSquare);
		myPacman.setSquare(testSquare3);
		assert myPacman.getSquare() == testSquare3;
		assert myPacman.getNbLives() == 3;
		myPacman2.setSquare(testSquare5);
		assert myPacman2.getSquare() == testSquare5;
		assert myPacman2.getNbLives() == 5;
		
		//test of die()
		myPacman.die();
		assert myPacman.getNbLives() == 2;
		myPacman.die();
		assert myPacman.getNbLives() == 1;
		assert myPacman.getSquare() == testSquare3;
		myPacman2.die();
		assert myPacman2.getNbLives() == 4;
	}

}
