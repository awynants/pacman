package pacman;

import java.util.Random;

public class RegularGhostState extends GhostState {

	@Override
	public GhostState move(Ghost ghost, Random random) {
		ghost.reallyMove(random);
		return this;
	}

	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacman) {
		pacman.die();
		return this;
	}

	@Override
	public boolean isVulnerableState() {
		return false;
	}

}
