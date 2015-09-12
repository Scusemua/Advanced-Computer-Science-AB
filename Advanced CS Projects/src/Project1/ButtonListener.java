package Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * This class will handle the pressing of ALL buttons aside from
 * special buttons, such as the John Cena button
 */

public class ButtonListener implements ActionListener {
	
	private Icon explosion = new ImageIcon(getClass().getResource("/explosion.png"));
	
	private Clip soundMiss = null;
	private Clip soundHit = null;
	private Clip underFifteen = null;
	
	AudioInputStream audioInRPGfired = null;
	AudioInputStream audioInRPGhit = null;
	AudioInputStream audioInUnderFifteen = null;
	
	// Constructor - mainly used to load sounds
	public ButtonListener() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInRPGhit = AudioSystem.getAudioInputStream(getClass().getResource("/RPGhit.wav"));
		soundHit = AudioSystem.getClip();
		soundHit.open(audioInRPGhit);
		
		audioInRPGfired = AudioSystem.getAudioInputStream(getClass().getResource("/RPGfired.wav"));
		soundMiss = AudioSystem.getClip();
		soundMiss.open(audioInRPGfired);
		
		audioInUnderFifteen = AudioSystem.getAudioInputStream(getClass().getResource("/under15.wav"));
		underFifteen = AudioSystem.getClip();
		underFifteen.open(audioInUnderFifteen);
	}
	
	public void actionPerformed(ActionEvent e) {
		// Create a button and initialize it to whatever button was clicked
		BenButton b = ((BenButton)e.getSource());
		
		// Print information about the clicked button (location and number of buttons clicked so far)
		System.out.println("Button Clicked at (" + b.getXCord() + "," + b.getYCord() + ") ---- " + BattleShip.getInstance().getShotsFired() + " shots fired so far!");
		
		// Disable the button since it has been clicked and increment the number of buttons clicked (shotsFired)
		((BenButton) e.getSource()).setEnabled(false);
		BattleShip.getInstance().incrementShotsFired();
		
		// Check to see if any of the ships have been hit
		for(int i = 0; i < BattleShip.getInstance().getShips().size(); i++) {
			// Pass the clicked button to the hit() method of the ship
			// to check if the ship was hit. If the ship was hit, play the hit sound
			if(BattleShip.getInstance().getShips().get(i).hit(b) == true) {
				try {
					playSoundHit();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				// Print information about where the ship was hit
				System.out.println("Ship hit at " + b.getXCord() + ", " + b.getYCord());
				
				// Set the disabled icon of the button to the explosion icon
				// If you simply set the icon of the button to explosion icon, instead of changing the DISABLED icon, it will be
				// a grayscale version of the explosion icon.
				b.setDisabledIcon(explosion);
				
				// Check if the ship that was hit is now sunk. If so, the player has won the game! 
				if(BattleShip.getInstance().getShips().get(i).getSunk()) {
					try {
						win();
					} catch (UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (LineUnavailableException e3) {
						e3.printStackTrace();
					}
				}
			} else { // If the player did NOT hit a ship, play the miss sound!
				try {
					playSoundMiss();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (LineUnavailableException e3) {
					e3.printStackTrace();
				}
			}
		}
	}
	
	// Plays "gunshot" and then splash, indicating a miss
	public void playSoundMiss() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Reset the clip's frame position to 0 so it starts from the beginning
		soundMiss.setFramePosition(0);
		soundMiss.start();
	}
	
	// Plays "gunshot" and then explosion, indicating a hit
	public void playSoundHit() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Reset the clip's frame position to 0 so it starts from the beginning
		soundHit.setFramePosition(0);
		soundHit.start();
	}
	
	// Sound played when the player wins if it only took him 15 shots or less
	public void playUnderFifteen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// Reset the clip's frame position to 0 so it starts from the beginning
		underFifteen.setFramePosition(0);
		underFifteen.start();
	}
	
	// Executes after all four parts of the ship have been shot
	public void win() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (BattleShip.getInstance().getShotsFired() <= 15) {
			playUnderFifteen();
		}
		JOptionPane.showMessageDialog(null, "Congratulations - you sunk the ship!");
		JOptionPane.showMessageDialog(null, "It took you " + BattleShip.getInstance().getShotsFired() + " shots to sink it!");
		int response = JOptionPane.showConfirmDialog(null, "Play again?");
		System.out.println("Response: " + response);
		if(response == 0) { // The user clicked Yes
			// Restart the game...
			BattleShip.getInstance().restartGame();
		} else if(response == 1) { // The user clicked "No"
			System.exit(2);
		} else { // The user clicked "Cancel"
			// Do nothing...
		}
	}
	
}
