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
	 * @invar | squareOfDot != null
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
	 * @post this dot's square equals the given square
	 *   | getSquare() == square
	 */
	public Dot(Square square) {
		if (square == null)
			throw new IllegalArgumentException("The given square cannot be null");
		squareOfDot = square; 
		}

}
