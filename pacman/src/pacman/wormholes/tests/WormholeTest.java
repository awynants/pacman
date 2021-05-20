package pacman.wormholes.tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import pacman.*;
import pacman.wormholes.*;

class WormholeTest {
	
	@Test
	void tests() {
		MazeMap testMazeMap = new MazeMap(5, 3, new boolean[] {false, true, true, false, true, true, true, false, true, false, true, true, true, false, true});
		Square squareDeparture1 = Square.of(testMazeMap, 0, 1);
		DeparturePortal departurePortal1 = new DeparturePortal(squareDeparture1);
		Square squareDeparture2 = Square.of(testMazeMap, 1, 3);
		DeparturePortal departurePortal2 = new DeparturePortal(squareDeparture2);
		Square squareDeparture3 = Square.of(testMazeMap, 2, 0);
		DeparturePortal departurePortal3 = new DeparturePortal(squareDeparture3);
		
		Square squareArrival1 = Square.of(testMazeMap, 0, 4);
		ArrivalPortal arrivalPortal1 = new ArrivalPortal(squareArrival1);
		Square squareArrival2 = Square.of(testMazeMap, 2, 2);
		ArrivalPortal arrivalPortal2 = new ArrivalPortal(squareArrival2);
		
		Wormhole wormhole1 = new Wormhole(departurePortal1, arrivalPortal1);
		Wormhole wormhole2 = new Wormhole(departurePortal3, arrivalPortal1);
		Wormhole wormhole3 = new Wormhole(departurePortal2, arrivalPortal2);
		Wormhole wormhole4 = new Wormhole(departurePortal2, arrivalPortal1);
		
		//getSquare tests
		assert(departurePortal1.getSquare() == squareDeparture1);
		assert(arrivalPortal2.getSquare() == squareArrival2);
		
		//getWormholes tests
		Set<Wormhole> wormholetest1 = new HashSet<Wormhole>();
		wormholetest1.add(wormhole1);
		wormholetest1.add(wormhole2);
		wormholetest1.add(wormhole4);
		assert(arrivalPortal1.getWormholes().equals(wormholetest1));
		Set<Wormhole> wormholetest2 = new HashSet<Wormhole>();
		wormholetest2.add(wormhole3);
		wormholetest2.add(wormhole4);
		assert(departurePortal2.getWormholes().equals(wormholetest2));
		
		//getDeparturePortal tests
		assert(wormhole1.getDeparturePortal().equals(departurePortal1));
		assert(wormhole3.getDeparturePortal().equals(departurePortal2));
		assert(wormhole4.getDeparturePortal().equals(departurePortal2));
		
		//getArrivalPortal tests
		assert(wormhole1.getArrivalPortal().equals(arrivalPortal1));
		assert(wormhole2.getArrivalPortal().equals(arrivalPortal1));
		assert(wormhole3.getArrivalPortal().equals(arrivalPortal2));
		
		//setDeparturePortal tests
		wormhole3.setDeparturePortal(departurePortal1);
		Set<Wormhole> wormholetest3 = new HashSet<Wormhole>();
		wormholetest3.add(wormhole3);
		wormholetest3.add(wormhole1);
		assert(departurePortal1.getWormholes().equals(wormholetest3));
		assert(wormhole3.getDeparturePortal().equals(departurePortal1));
		
		assert(wormhole4.getDeparturePortal().equals(departurePortal2));
		wormhole4.setDeparturePortal(departurePortal2);
		Set<Wormhole> wormholetest4 = new HashSet<Wormhole>();
		wormholetest4.add(wormhole4);
		assert(departurePortal2.getWormholes().equals(wormholetest4));
		assert(wormhole4.getDeparturePortal().equals(departurePortal2));
		
		//setArrivalPortal tests
		assert(wormhole1.getArrivalPortal().equals(arrivalPortal1));
		wormhole1.setArrivalPortal(arrivalPortal1);
		Set<Wormhole> wormholetest5 = new HashSet<Wormhole>();
		wormholetest5.add(wormhole1);
		wormholetest5.add(wormhole2);
		wormholetest5.add(wormhole4);
		assert(arrivalPortal1.getWormholes().equals(wormholetest5));
		assert(wormhole1.getArrivalPortal().equals(arrivalPortal1));
		
		wormhole2.setArrivalPortal(arrivalPortal2);
		Set<Wormhole> wormholetest6 = new HashSet<Wormhole>();
		wormholetest6.add(wormhole3);
		wormholetest6.add(wormhole2);
		assert(arrivalPortal2.getWormholes().equals(wormholetest6));
		assert(wormhole2.getArrivalPortal().equals(arrivalPortal2));
	}
}
