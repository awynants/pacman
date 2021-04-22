package pacman;

import java.util.Arrays;
import java.util.Random;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foods;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foods.clone(); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foods) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foods = foods.clone();
	}
	
	public boolean isCompleted() {
		return foods.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	private void removeDotAtIndex(int index) {
		FoodItem[] newDots = new FoodItem[foods.length - 1];
		System.arraycopy(foods, 0, newDots, 0, index);
		System.arraycopy(foods, index + 1, newDots, index, newDots.length - index);
		foods = newDots;
	}
	
	private void removeDotAtSquare(Square square) {
		for (int i = 0; i < foods.length; i++) {
			if (foods[i].getSquare().equals(square)) {
				if (foods[i] instanceof PowerPellet) {
					for (int j = 0; j < ghosts.length; j++)
						ghosts[j].pacManAtePowerPellet();
				}
				removeDotAtIndex(i);
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			pacMan.setSquare(newSquare);
			removeDotAtSquare(newSquare);
			checkPacManDamage();
		}
	}
	
}
