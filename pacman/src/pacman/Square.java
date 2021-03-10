package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 * @invar The MazeMap of this Square is not null
 * 	| getMazeMap() != null
 * @invar The row index of this Square is not negative and in the bounds of the MazeMap
 * 	| (getRowIndex() >= 0) &&
 * 	| (getRowIndex() < getMazeMap().getHeight())
 * @invar The column index of this Square is not negative and in the bounds of the MazeMap
 * 	| (getColumnIndex() >= 0) &&
 * 	| (getRowIndex() < getMazeMap().getWidth())
 */
public class Square {
	
	/**
	 * @representationObject
	 * This variable represents the mazemap that this square is on.
	 * @invar | mazemapOfSquare != null
	 */
	private final MazeMap mazemapOfSquare;
	/**
	 * @representationObject
	 * This variable represents the position of this square.
	 * @invar The position cannot be a negative number.
	 * | position >= 0
	 * @invar The position cannot be larger than the height of the mazemap times its weight.
	 * | position < mazemapOfSquare.getWidth() * mazemapOfSquare.getHeight()
	 */
	private final int position;
	
	
	/**
	 * Returns the MazeMap that this square is a part of.
	 * @basic
	 */
	public MazeMap getMazeMap() { return mazemapOfSquare; } //no inspects needed because MazeMap is immutable
	/**
	 * Returns the row index of this square.
	 */
	public int getRowIndex() { 
		int mapWidth = mazemapOfSquare.getWidth();
		return position/mapWidth;
	}
	/**
	 * Returns the column index of this square.
	 */
	public int getColumnIndex() {
		int mapWidth = mazemapOfSquare.getWidth();
		return position - (this.getRowIndex() * mapWidth);
	}
	
	
	/**
	 * This method checks if this Square is a passable square on the MazeMap.
	 * @post The result is a boolean.
	 * 	| result == true || result == false
	 */
	//no inspects needed because MazeMap is immutable
	public boolean isPassable() {
		MazeMap mazeMap = getMazeMap();
		int row = getRowIndex();
		int col = getColumnIndex();
		return mazeMap.isPassable(row, col);
	}
	
	/**
	 * This is a private constructor used by the of method.
	 * 
	 * @throws mazeMap cannot be null 
	 * 	| mazeMap == null
	 * @throws rowIndex cannot be negative or outside the bounds of mazeMap
	 * 	| (rowIndex < 0) || (rowIndex >= mazeMap.getHeight())
	 * @throws columnIndex cannot be negative or outside the bounds of mazeMap
	 * 	| (columnIndex < 0) || (columnIndex >= mazeMap.getWidth())
	 */
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null) { 
			throw new IllegalArgumentException("mazeMap is null");}
		if (rowIndex < 0 || rowIndex >= mazeMap.getHeight()) { 
			throw new IllegalArgumentException("rowIndex is negative or outside the bounds of mazeMap");}
		if (columnIndex < 0 || columnIndex >= mazeMap.getWidth()) { 
			throw new IllegalArgumentException("columnIndex is negative or outside the bounds of mazeMap");}
		
		mazemapOfSquare = mazeMap;
		int newPosition = (rowIndex * mazeMap.getWidth())+columnIndex;
		position = newPosition;
	}
	
	/**
	 * This method returns a square object with the given values.
	 * 
	 * @post The result cannot be null
	 * 	| result != null
	 * @throws mazeMap cannot be null 
	 * 	| mazeMap == null
	 * @throws rowIndex cannot be negative or outside the bounds of mazeMap
	 * 	| (rowIndex < 0) || (rowIndex >= mazeMap.getHeight())
	 * @throws columnIndex cannot be negative or outside the bounds of mazeMap
	 * 	| (columnIndex < 0) || (columnIndex >= mazeMap.getWidth())
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null) { 
			throw new IllegalArgumentException("mazeMap is null");}
		if (rowIndex < 0 || rowIndex >= mazeMap.getHeight()) { 
			throw new IllegalArgumentException("rowIndex is negative or outside the bounds of mazeMap");}
		if (columnIndex < 0 || columnIndex >= mazeMap.getWidth()) { 
			throw new IllegalArgumentException("columnIndex is negative or outside the bounds of mazeMap");}
		Square newSquare = new Square(mazeMap, rowIndex, columnIndex);
		return newSquare;
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neighbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		// Implementation hint: use method java.lang.Math.floorMod.
		if (direction != Direction.LEFT && direction != Direction.RIGHT && direction != Direction.UP && direction != Direction.DOWN) {
			throw new IllegalArgumentException("direction is not a valid direction.");
		}
		
		MazeMap currentMazeMap = this.getMazeMap();
		int currentRow = this.getRowIndex();
		int currentColumn = this.getColumnIndex();
		int mazeMapWidth = currentMazeMap.getWidth();
		int mazeMapHeight = currentMazeMap.getHeight();
		
		if (direction == Direction.LEFT) {
			if (currentColumn == 0) {
				return Square.of(currentMazeMap, currentRow, mazeMapWidth - 1);
			}
			else {return Square.of(currentMazeMap, currentRow, currentColumn - 1);}
		}
		
		if (direction == Direction.RIGHT) {
			if (currentColumn == mazeMapWidth - 1) {
				return Square.of(currentMazeMap, currentRow, 0);
			}
			else {return Square.of(currentMazeMap, currentRow, currentColumn + 1);}
		}
		
		if (direction == Direction.UP) {
			if (currentRow == 0) {
				return Square.of(currentMazeMap, mazeMapHeight - 1, currentColumn);
			}
			else {return Square.of(currentMazeMap, currentRow - 1, currentColumn);}
		}
		
		if (direction == Direction.DOWN) {
			if (currentRow == mazeMapHeight - 1) {
				return Square.of(currentMazeMap, 0, currentColumn);
			}
			else {return Square.of(currentMazeMap, currentRow + 1, currentColumn);}
		}
		
		//In case a value somehow got past all those checks, which should normally be impossible.
		throw new IllegalArgumentException("direction is not a valid direction.");
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		if (direction != Direction.LEFT && direction != Direction.RIGHT && direction != Direction.UP && direction != Direction.DOWN) {
			throw new IllegalArgumentException("direction is not a valid direction.");
		}
		Square neighbor = getNeighbor(direction);
		return neighbor.isPassable();
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		if (excludedDirection != Direction.LEFT && excludedDirection != Direction.RIGHT && excludedDirection != Direction.UP && excludedDirection != Direction.DOWN) {
			throw new IllegalArgumentException("direction is not a valid direction.");
		}
		
		boolean[] tempResultArray = {false,false,false,false};
		int resultLength = 0;
		
		if (excludedDirection != Direction.LEFT && canMove(Direction.LEFT)) {
			tempResultArray[0] = true;
			resultLength += 1;
		}
		if (excludedDirection != Direction.RIGHT && canMove(Direction.RIGHT)) {
			tempResultArray[1] = true;
			resultLength += 1;
		}
		if (excludedDirection != Direction.UP && canMove(Direction.UP)) {
			tempResultArray[2] = true;
			resultLength += 1;
		}
		if (excludedDirection != Direction.DOWN && canMove(Direction.DOWN)) {
			tempResultArray[3] = true;
			resultLength += 1;
		}
		
		Direction[] result = new Direction[resultLength];
		int resultIndex = 0;
		int tempResultIndex = 0;
		while (resultIndex < resultLength) {
			if (tempResultArray[tempResultIndex] && tempResultIndex == 0) {
				result[resultIndex] = Direction.LEFT;
				resultIndex+=1;
			}
			if (tempResultArray[tempResultIndex] && tempResultIndex == 1) {
				result[resultIndex] = Direction.RIGHT;
				resultIndex+=1;
			}
			if (tempResultArray[tempResultIndex] && tempResultIndex == 2) {
				result[resultIndex] = Direction.UP;
				resultIndex+=1;
			}
			if (tempResultArray[tempResultIndex] && tempResultIndex == 3) {
				result[resultIndex] = Direction.DOWN;
				resultIndex+=1;
			}
			tempResultIndex+=1;
		}
		
		return result;
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.
	 * @throws the given Square is not a null-pointer.
	 * 	| other == null  
	 * @post the result is a boolean.
	 * 	| result == true || result == false
	 */
	public boolean equals(Square other) {
		if (other == null) {
			throw new IllegalArgumentException("Other Square is null.");
		}
		
		boolean result = (this.getMazeMap() == other.getMazeMap());
		if (result){
			result = (this.getRowIndex() == other.getRowIndex());
			if (result) {
				result = (this.getColumnIndex() == other.getColumnIndex());
			}
		}
		
		return result;
	}
	
}
