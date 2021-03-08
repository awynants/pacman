package pacman;

import java.util.stream.IntStream;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @immutable
 * @invar the height of the mazemap is greater than 0
 *   | 0 < getWidth()
 * @invar the width of the mazemap is greater than 0
 *   | 0 < getHeight()
 */
public class MazeMap {
	
	/**
	 * @representationObject
	 * This variable represents the width of the MazeMap
	 * @invar | 0 < w
	 */
	private final int w;
	
	/**
	 * @representationObject
	 * this variable represents the height of the MazeMap
	 * @invar | 0 < h
	 */
	private final int h;
	
	/**
	 * @representationObject
	 * this array represents, for each square of the MazeMap, whether it is passable
	 * @invar the length of the passable array is greater than 0
	 *   | 0 < passarray.length
	 * @invar | passarray != null
	 */
	private final boolean[] passarray;

	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 */
	public final int getWidth() { return w; } //geen representation exposure want int is een immutable primitive type
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map. 
	 */
	public final int getHeight() { return h; } //geen representation exposure want int is een immutable primitive type
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @throws IllegalArgumentException if the given row index is not between 0 and the number of rows
	 *   | rowIndex < 0 || getHeight() <= rowIndex
	 * @throws IllegalArgumentException if the given column index is not between 0 and the number of columns
	 *   | columnIndex < 0 || getWidth() <= columnIndex
	 */
	//geen @inspects want de klasse is immutable
	public boolean isPassable(int rowIndex, int columnIndex) { 
		if (rowIndex < 0 || getHeight() <= rowIndex) 
			throw new IllegalArgumentException("Row index not in range");
		if (columnIndex < 0 || getWidth() <= columnIndex) 
			throw new IllegalArgumentException("Column index not in range");
		return passarray[getWidth()*rowIndex + columnIndex];
		}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @throws IllegalArgumentException if the given width is less than 1
	 *   | width < 1
	 * @throws IllegalArgumentException if the given height is less than 1
	 *   | height < 1
	 * @throws IllegalArgumentException if the length of the passable array is not equal to the width * height
	 *   | width*height != passable.length
	 * @post this object's width equals the given width
	 *   | getWidth() == width
	 * @post this object's height equals the given height
	 *   | getHeight() == height
	 * @post this object's passable squares equal those in the given array
	 *   | IntStream.range(0, passable.length).
	 *   | allMatch(i -> passable[i] == isPassable(Math.floorDiv(i, getWidth()), i % getWidth())) 
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (width < 1)
			throw new IllegalArgumentException("Width must be at least 1");
		if (height < 1)
			throw new IllegalArgumentException("Height must be at least 1");
		if (width*height != passable.length)
			throw new IllegalArgumentException("Passable array length does not match size of the MazeMap");
		h = height;
		w = width;
		passarray = passable.clone(); 
	}
}
