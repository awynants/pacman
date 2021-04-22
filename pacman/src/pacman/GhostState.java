package pacman;

import java.util.Random;

public abstract class GhostState {

	public GhostState move(Ghost ghost, Random random) {
		throw new RuntimeException("Not yet implemented");
	}
	
	public GhostState hitBy(Ghost ghost, PacMan pacman) {
		throw new RuntimeException("Not yet implemented");
	}
}
