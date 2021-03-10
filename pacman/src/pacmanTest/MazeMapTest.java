package pacmanTest;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;

class MazeMapTest {

	@Test
	void test() {
		int num = 3;
		int num2 = 5;
		MazeMap myMap = new MazeMap(num, num2, new boolean[] {true, false, false, true, false, false, true, true, true, false, false, false, true, false, false});
		
		//test of getWidth()
		assert myMap.getWidth() == 3;
		num += 1;
		assert myMap.getWidth() == 3;
		
		//test of getHeight()
		assert myMap.getHeight() == 5;
		num2 += 1;
		assert myMap.getHeight() == 5;
		
		//test of isPassable(rowIndex, columnIndex)
		assert myMap.isPassable(1, 0) == true;
		assert myMap.isPassable(2, 2) == true;
		assert myMap.isPassable(0, 2) == false;
		assert myMap.isPassable(1, 1) == false;
		assert myMap.isPassable(3, 2) == false;
	}

}
