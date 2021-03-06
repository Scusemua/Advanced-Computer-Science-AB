package Project1;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 *  Class the handles the UI of the game, the gameplay, etc.
 */

public class BattleShip extends JFrame {
	
	// Variable declaration  
	private static final long serialVersionUID = 2939590021227966552L;
	
	// Create the array of buttons and arraylist of ships
	// I am using an ArrayList for the ships as I am going to implement the option
	// to have more than one ship
	private BenButton[][] buttons = new BenButton[13][13];
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	
	// Number of shots fired (effectively number of buttons clicked)
	private static int shotsFired;
	
	// All of the icons for the program
	private Icon pepe = new ImageIcon(getClass().getResource("/pepe.jpg"));
	private Icon doritos = new ImageIcon(getClass().getResource("/doritos.png"));
	private Icon mountain_dew = new ImageIcon(getClass().getResource("/mountain dew.png"));
	private Icon john_cena = new ImageIcon(getClass().getResource("/johncena.png"));
	private Icon water = new ImageIcon(getClass().getResource("/water.png"));
	private Icon small = new ImageIcon(getClass().getResource("/small.png"));
	
	private AudioInputStream audioInJohnCena;
	
	private Clip johnCenaClip;
	
	private ButtonListener buttonListener = null;
	
	// Singleton
	private static BattleShip instance = null;
	
	// Creates the Singleton if it hasn't already been created,
	// then returns it 
	public static BattleShip getInstance() {
		if(instance == null) {
			instance = new BattleShip();
		}
		return instance;
	}
	
	// Initializes the UI
	public void initialize() {
		
		try {
			buttonListener = new ButtonListener();
			// In Java 1.8, you can put all of the catches in a single statement
			// However, in order to keep in compatible for older versions, I'm keeping them
			// all seperate
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (LineUnavailableException e3) {
			e3.printStackTrace();
		}
		
		// Load the audio for the John Cena sound
		try {
			audioInJohnCena = AudioSystem.getAudioInputStream(getClass().getResource("/johncena.wav"));
			johnCenaClip = AudioSystem.getClip();
			johnCenaClip.open(audioInJohnCena);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (LineUnavailableException e3) {
			e3.printStackTrace();
		}
		
		// Set the layout to GridLayout
		setLayout(new GridLayout(13, 13));
		
		// Set the title of the window
		setTitle("Memeship - The Dankening");
		
		// Set the default close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set the component orientation
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		// Create a button in the top left corner because dank memes are important
		// to every program
		BenButton firstButton = new BenButton();
		
		// Make the button borderless by creating an empty border and setting the button's border to the newly created empty border
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		firstButton.setBorder(emptyBorder);
		
		// Set the button's icon to a picture of WWE Wrestler John Cena
		firstButton.setIcon(john_cena);
		
		// Add an event listener for that button
		firstButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					firstButtonActionPerformed(evt);
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (LineUnavailableException e3) {
					e3.printStackTrace();
				}
			}
		});
		
		// This button isn't added to the array of buttons
		add(firstButton);
		
		// Add JLabels across the very top to number each column by 
		// creating chars with their ASCII values 
		for(int i = 65; i < 77; i++) {
			char c = (char) i;
			JLabel label = new JLabel ("        " + c);
		
			//label.setIcon(doritos);
			
			add(label);
		}
	
		// Create either the buttons for the ocean OR JLabels down the left side to number the rows
		// I have to keep track of the x and y coordinates of the buttons separately because of how I'm creating the buttons
		int xCord = 1;
		int yCord = 0; // If I initialize this to a value of 1, I get an out of bounds error.
		
		// This boolean is in place so I can check to see if it's the first time I'm creating a label. If it is, I don't increment the
		// y-coordinate variable after creating a label like normal.
		boolean first = true;
		
		for(int i = 13; i < 169; i++) {
			
			// Every 13th spot is where a JLabel should go
			// Check to see if we're at a 13th spot
			// If so, put a label there instead of a button
			if (i % 13 == 0) {
				// Create the J Label. The spaces are so the number is centered more.
				JLabel label = new JLabel("             " + (i/13));
				
				// Add the label to the JFrame
				add(label);
				
				// After this executes, it is not the first iteration of the for-loop, so set first to false
				first = false;
				
				// Increment the y-cord each iteration after the first iteration.
				// If you incremented the y-cord during the first iteration, it would be "ahead" of where it should be
				if(!first) {
					yCord++;
				}
				
			} else {
				// Create a new button at the next coordinates
				BenButton button = new BenButton(xCord, yCord);
				
				// Set the button's icon to the water icon
				button.setIcon(water);
				
				// Set the button's action listener to the standard button action listener 
				button.addActionListener(buttonListener);
				
				// Place the new button in the array of buttons
				buttons[xCord][yCord] = button;
				
				// Add the button to the JFrame
				add(button);
				
				// Increment the x-coordinate so the next button will be placed in the next available spot
				xCord++;
				
				// The maximum x-coordinate is twelve. After that, a new row will begin and the x-coordinate will have to start at one.
				// Instead of incrementing the y-coordinate here (as a new row is beginning at this point), it is incremented during the portion of the
				// for-loop that handles the JLabels.
				if(xCord > 12) xCord = 1;
			}
		}
		
		// Set the size of the window and whether or not the user can resize it
		setSize(700,700);
		setResizable(false);
		
		// Size everything to fit preferred size and layouts
		// pack();
		// Make the window visible
		setVisible(true);
	}
	
	private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// Play the John Cena theme song
		johnCenaClip.setFramePosition(0);
		johnCenaClip.start();
		
		// When the John Cena button is pressed, it randomly changes all the icons of the buttons
		// to various memes. The meme is selected randomly.
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 13; j++) {
				if(buttons[i][j] != null) {
					Random rand = new Random(System.nanoTime());
					int k = rand.nextInt(5) + 1;
					if(k == 1) buttons[i][j].setDisabledIcon(small);
					if(k == 2) buttons[i][j].setDisabledIcon(pepe);
					if(k == 3) buttons[i][j].setDisabledIcon(mountain_dew);
					if(k == 4) buttons[i][j].setDisabledIcon(doritos);
					if(k == 5) buttons[i][j].setDisabledIcon(john_cena);
				}
			}
		}
		System.out.println("John Cena!!");
	}
	
	// Restarts the game
	public void restartGame() throws IOException {	
		
		System.out.println("***** RESTARTING GAME *****");
		
		// Reset the BattleShip JFrame
		
		getContentPane().removeAll(); // Since this class extends JFrame, I can do .getContentPane().removeAll() to remove all components
		initialize(); // Initialize the UI again, thus readding all the components to the JFrame
		
		System.out.println("Event: Clearing ArrayList of ships for game restart...");
		
		ships.removeAll(ships);
		
		// Reset the number of shots fired to 0, since the game restarted
		shotsFired = 0;
		
		// Create new ship, add it to the ArrayList of ships, and print all of the coordinates in inhabits 
		Ship s1 = new Ship();
		ships.add(s1);
		s1.printSpaces();
	}
	
	// Returns the ArrayList of ships 
	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	// Returns two-dimensional array of buttons
	public BenButton[][] getButtons() {
		return buttons;
	}
	
	// Returns the number of shots fired
	public int getShotsFired() {
		return shotsFired;
	}
	
	// The shotsFired variable will only ever need to be incremented by
	// one at the most, so instead of a setShotsFired() method, I just created
	// an incrementShotsFired() method that increments shotsFired by one
	public void incrementShotsFired() {
		shotsFired++;
	}

}
