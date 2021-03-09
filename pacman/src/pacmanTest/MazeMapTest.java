package pacmanTest;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;

class MazeMapTest {

	@Test
	void test() {
		int num = 3;
		MazeMap myMap = new MazeMap(num, 5, new boolean[] {true, false, false, true, false, false, true, true, true, false, false, false, true, false, false});
		assert myMap.getWidth() == 3;
		num += 1;
		assert myMap.getWidth() == 3;
		assert myMap.getHeight() == 5;
		assert myMap.isPassable(1, 0) == true;
		assert myMap.isPassable(2, 2) == true;
		assert myMap.isPassable(0, 2) == false;
		assert myMap.isPassable(1, 1) == false;
		assert myMap.isPassable(3, 2) == false;
	}

}
