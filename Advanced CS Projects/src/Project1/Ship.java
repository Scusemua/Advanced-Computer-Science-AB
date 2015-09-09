package Project1;

import java.util.Random;

public class Ship {
	private int initialXCord;
	private int initialYCord;
	private int direction; // 0 = horizontal, 1 = vertical
	
	// Number of times the ship has been hit
	private int hits;
	
	// Array of Buttons that the ship is on
	private int[][] locations = new int[4][2];

	// Whether or not the ship has been sunk
	private boolean sunk;
	
	// Constructor
	public Ship() {
		locations = generateLocation();
		sunk = false;
		hits = 0;
		System.out.println("Ship created at " + initialXCord +", " + initialYCord + " with direction: " + direction);
	}
	
	// Check if the ship was hit 
	public boolean hit(BenButton b) {
		int buttonX = b.getXCord(); // Create a local variable equal to the x-coordinate of the clicked button 
		int buttonY = b.getYCord(); // Create a local variable equal to the y-coordinate of the clicked button 
		for(int i = 0; i < 4; i++) {
			// See if the x-coordinate matches a ship's x-coordinate
			if(buttonX == locations[i][0]) {
				// If there was a match, see if the button clicked's y-coordinate is also equal to a ship's y-coordinate.
				// If it is, the player has hit a ship! Increment the number of hits and update the ship to see if it has sunk yet.
				if(buttonY == locations[i][1]) {
					hits++;
					update();
					return true;
				}
			}
		}
		return false;
	}
	
	// Check if the ship is sunk
	public void update() {
		if(hits == 4) {
			sunk = true;
		}
	}
	
	// Print all occupied spaces
	public void printSpaces() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(locations[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	// Generate location of ship
	public int[][] generateLocation() {
		// Randomly generate the x & y coordinates of the left-most or top-most part of the ship.
		Random RNGesus = new Random(System.nanoTime()); // Create a random number generator seeded with the current time in nano-seconds 
		initialXCord = RNGesus.nextInt(8) + 1; // Eight is the maximum coordinate that the ship can start at without going off the board
		initialYCord = RNGesus.nextInt(8) + 1;// Eight is the maximum coordinate that the ship can start at without going off the board
		locations[0][0] = initialXCord; // Store initial X coordinate
		locations[0][1] = initialYCord; // Store initial y coordinate 
		direction = RNGesus.nextInt(2); // 0 = horizontal, 1 = vertical
		
		// If the direction is horizontal...
		// Put the rest of the ship in increasingly large x-coordinates since it's going left-to-right
		// Keep the y-coordinate constant since the ship isn't oriented up and down
		if (direction == 0) { 
			locations[1][0] = initialXCord + 1; 
			locations[1][1] = initialYCord; 
			locations[2][0] = initialXCord + 2; 
			locations[2][1] = initialYCord; 
			locations[3][0] = initialXCord + 3; 
			locations[3][1] = initialYCord;
			
		// If the direction is vertical...
		// Put the rest of the ship in increasingly large y-coordinates (which are lower on the board) since it's going up and down
		// Leave the x-coordinate constant since the ship isn't oriented left-to-right
		} else {
			locations[1][0] = initialXCord;
			locations[1][1] = initialYCord + 1;
			locations[2][0] = initialXCord;
			locations[2][1] = initialYCord + 2;
			locations[3][0] = initialXCord;
			locations[3][1] = initialYCord + 3;
		}
		
		return locations;
	}
	
	// Get x-coordinate 
	public int getInitialXCord() {
		return initialXCord;
	}
	
	// Get y-coordinate
	public int getInitialYCord() {
		return initialYCord;
	}
	
	// Set x-coordinate to new value
	public void setXCord(int newX) {
		initialXCord = newX;
	}
	
	// Set y-coordinate to new value
	public void setYCord(int newY) {
		initialXCord = newY;
	}
	
	// Return the array of button-locations
	public int[][] getLocations() {
		return locations;
	}
	
	// Returns true if ship has been sunk, returns false if it hasn't been sunk yet
	// A ship is considered sunk if it has been hit 4 times (since the ship is 4 tiles big)
	public boolean getSunk() {
		return sunk;
	}
}
