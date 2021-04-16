package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 * @invar the square of this dot is not null
 *  | getSquare() != null
 * @invar the square that this dot is on must be passable
 * 	| getSquare().isPassable()
 */
public class Dot extends FoodItem {
	
	/**
	 * @representationObject
	 * This variable represents the square that this dot is on
	 * 
	 * @invar the square a dot is on is not null
	 *  | squareOfDot != null
	 * @invar the square a dot is on is passable
	 *  | squareOfDot.isPassable() == true
	 */
	private Square squareOfDot;
	
	@Override
	/**
	 * Returns the square that this dot is on.
	 * 
	 * @post the result is not null
	 *   | result != null
	 * @basic
	 */
	public Square getSquare() { 
		return squareOfDot;
		}
	
	/**
	 * Initializes a dot at the given square.
	 * 
	 * @throws IllegalArgumentException if the given square is null
	 *   | square == null
	 * @throws IllegalArgumentException if the given square is not passable
	 *   | square.isPassable() != true
	 * @post this dot's square equals the given square
	 *   | getSquare() == square
	 */
	public Dot(Square square) {
		if (square == null)
			throw new IllegalArgumentException("The given square cannot be null");
		if (square.isPassable() != true)
			throw new IllegalArgumentException("The square a dot is on must be passable");
		squareOfDot = square; 
		}

	@Override
	/**
	 * Returns the size of the dot relative to a dot. All dots are equal size, so it returns 1.
	 * 
	 * @post | result == 1
	 */
	public int getSize() {
		return 1;
	}

}
