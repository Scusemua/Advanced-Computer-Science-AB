package Project1;

import javax.swing.Icon;
import javax.swing.JButton;

/*
 * Button class that extends JButton.
 * The purpose of having this class is so the buttons
 * can have information showing where on the grid they are.
 * 
 * There are no set methods for the x & y coordinates because the coordinates won't change 
 */

public class BenButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 127936698718085552L;
	int xCord, yCord;
	
	// Constructor when the button is created with X and Y coordinates
	public BenButton(int row, int col) {
		super();
		xCord = row;
		yCord = col;
		setVisible(true);
	}
	
	// Constructor for when the button is created simply with an icon 
	// Used for the John Cena button
	public BenButton(Icon ico) {
		super(ico);
		setVisible(true);
	}

	// Default constructor 
	public BenButton() {
		super();
		setVisible(true);
	}
	
	// Get the x-coordinate of the button
	public int getXCord() {
		return xCord;
	}
	
	// Get the y-coordinate of the button
	public int getYCord() {
		return yCord;
	}
	

}
