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
	private MazeMap mazemapOfSquare;
	/**
	 * @representationObject
	 * This variable represents the position of this square.
	 * @invar The position cannot be a negative number.
	 * | position >= 0
	 * @invar The position cannot be larger than the height of the mazemap times its weight.
	 * | position < mazemapOfSquare.getWidth() * mazemapOfSquare.getHeight()
	 */
	private int position;
	
	
	/**
	 * Returns the MazeMap that this square is a part of.
	 * 
	 * The client shall not mutate the resulting object.
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
	 */
	//no inspects needed because MazeMap is immutable
	public boolean isPassable() {
		MazeMap mazeMap = getMazeMap();
		int row = getRowIndex();
		int col = getColumnIndex();
		return mazeMap.isPassable(row, col);
	}
	
	/**
	 * This method returns a square object with the given values.
	 * 
	 * @post The result cannot be null
	 * 	| result != null
	 * @throws mazeMap cannot be null 
	 * 	| mazeMap != null
	 * @throws rowIndex cannot be negative or outside the bounds of mazeMap
	 * 	| (rowIndex >= 0) && (rowIndex < mazeMap.getHeight())
	 * @throws columnIndex cannot be negative or outside the bounds of mazeMap
	 * 	| (columnIndex >=0) && (columnIndex < mazeMap.getWidth())
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null) { 
			throw new IllegalArgumentException("mazeMap is null");}
		if (rowIndex < 0 || rowIndex >= mazeMap.getHeight()) { 
			throw new IllegalArgumentException("rowIndex is negative or outside the bounds of mazeMap");}
		if (columnIndex < 0 || columnIndex >= mazeMap.getWidth()) { 
			throw new IllegalArgumentException("columnIndex is negative or outside the bounds of mazeMap");}
		Square newSquare = new Square();
		newSquare.mazemapOfSquare = mazeMap;
		int newPosition = (rowIndex * mazeMap.getWidth())+columnIndex;
		newSquare.position = newPosition;
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
		if (direction == Direction.LEFT) {
			if (currentColumn == 0) {
				int newColumn = currentMazeMap.getWidth() - 1;
				return Square.of(currentMazeMap, currentRow, newColumn);
			}
			else {return Square.of(currentMazeMap, currentRow, currentColumn-1);}
		}
		throw new IllegalArgumentException("not yet finished.");
		//nog schrijven voor de andere richtingen eens dat er een antwoord op het forum is gekomen.
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
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 */
	public boolean equals(Square other) {
		throw new RuntimeException("Not yet implemented");
	}
	
}
