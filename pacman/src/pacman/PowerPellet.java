package pacman;

public class PowerPellet extends FoodItem{
	
	private Square  squareOfPellet;
	
	@Override
	/**
	 * Returns the square that this power pellet is on.
	 * 
	 * @post the result is not null
	 *   | result != null
	 * @basic
	 */
	public Square getSquare() { 
		return squareOfPellet;
		}
	
	/**
	 * Initializes a dot at the given square.
	 * 
	 * @throws IllegalArgumentException if the given square is null
	 *   | square == null
	 * @throws IllegalArgumentException if the given square is not passable
	 *   | square.isPassable() != true
	 * @post this power pellet's square equals the given square
	 *   | getSquare() == square
	 */
	public PowerPellet(Square square) {
		if (square == null)
			throw new IllegalArgumentException("The given square cannot be null");
		if (square.isPassable() != true)
			throw new IllegalArgumentException("The square a power pellet is on must be passable");
		squareOfPellet = square; 
		}
	
	@Override
	/**
	 * Returns the size of the power pellet relative to a dot. The size of a power pellet is twice that of a dot.
	 * 
	 * @post A dot always has a size of 1, so a power pellet has a size of 2.
	 * 	| result == 2
	 */
	public int getSize() {
		return 2;
	}
}
