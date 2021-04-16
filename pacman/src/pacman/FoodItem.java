package pacman;

public abstract class FoodItem {
	
	/**
	 * Returns the square that the food item is on.
	 * @post | result != null
	 */
	public abstract Square getSquare();
	
	/**
	 * Returns the size of the food item relative to the size of a dot. This size cannot be negative.
	 * @post | result >= 0
	 */
	public abstract int getSize();
}
