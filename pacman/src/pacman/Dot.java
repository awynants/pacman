package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 * @invar the square of this dot is not null
 *   | getSquare() != null
 */
public class Dot {
	
	/**
	 * @representationObject
	 * this variable represents the square that this dot is on
	 * 
	 * @invar the square a dot is on is not null
	 *  | squareOfDot != null
	 * @invar the square a dot is on is passable
	 *  | squareOfDot.isPassable() == true
	 */
	private Square squareOfDot;
	
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

}
