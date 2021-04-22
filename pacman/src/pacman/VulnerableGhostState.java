package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState {
	
	private int moveDelay;
	private int timesMoved;
	
	public VulnerableGhostState() {
		moveDelay = 1;
		timesMoved = 0;
	}
	
	@Override
	public GhostState move(Ghost ghost, Random random) {
		if (moveDelay == 1){
			moveDelay = 0;
			return this;
		}
		else {
			moveDelay = 1;
			timesMoved = timesMoved+1;
			if (timesMoved >= 6) {
				ghost.reallyMove(random);
				return new RegularGhostState();
			}
			else {
				ghost.reallyMove(random);
				return this;
			}
		}
	}

	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacman) {
		Square originalSquare = ghost.getOriginalSquare();
		ghost.setSquare(originalSquare);
		return new RegularGhostState();
	}

	@Override
	public boolean isVulnerableState() {
		return true;
	}

}
