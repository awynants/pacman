package pacman;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foods;
	private DeparturePortal[] departureportals;
	private ArrivalPortal[] arrivalportals;
	private Wormhole[] wormholes;
 	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foods.clone(); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foods, DeparturePortal[] departureportals, ArrivalPortal[] arrivalportals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foods = foods.clone();
		this.departureportals = departureportals.clone();
		this.arrivalportals = arrivalportals.clone();
		this.wormholes = new Wormhole[0];
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
			checkPacManDamage();
			for (DeparturePortal departurePortal : departureportals) {
				if (newSquare.equals(departurePortal.getSquare())) {
					int nrofarrivals = departurePortal.getWormholes().size();
					int number = random.nextInt(nrofarrivals);
					Wormhole[] connectedWormholes = departurePortal.getWormholes().toArray(new Wormhole[nrofarrivals]);
					
					pacMan.setSquare(connectedWormholes[number].getArrivalPortal().getSquare());
				}
			}
			removeDotAtSquare(newSquare);
			checkPacManDamage();
		}
	}
	
	public DeparturePortal[] getDeparturePortals() {
		return departureportals.clone();
	}
	
	public ArrivalPortal[] getArrivalPortals() {
		return arrivalportals.clone();
	}
	
	public Wormhole[] getWormholes() {
		return wormholes.clone();
	}
	
	public void addWormhole(Wormhole newwormhole) {
		if (!IntStream.range(0, departureportals.length).anyMatch(i -> departureportals[i] == newwormhole.getDeparturePortal())) {
			throw new RuntimeException("Wormhole's departure portal not in Maze's list of departure portals");
		}
		if (!IntStream.range(0, arrivalportals.length).anyMatch(i -> arrivalportals[i] == newwormhole.getArrivalPortal())) {
			throw new RuntimeException("Wormhole's arrival portal not in Maze's list of arrival portals");
		}
		Wormhole[] newwormholes = Arrays.copyOf(wormholes, wormholes.length + 1);
		newwormholes[wormholes.length] = newwormhole;
		this.wormholes = newwormholes;
	}
}
