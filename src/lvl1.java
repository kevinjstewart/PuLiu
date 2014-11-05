import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.awt.geom.Line2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.color.*;

/**
 * This class displays the JLabels and scores and paints the the lines used as platforms.
 * @author Kevin
 *
 */
@SuppressWarnings("unused")
public class lvl1 extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel llabel = new JLabel("Level " + GameFrame.getLevelNum());
	JLabel instructions = new JLabel("A & S to move, W to jump. Reach the top. Press P to save. Press L to load.");
	JLabel pressG = new JLabel("Press G to start the Dragon Oath Experience. \n Your score and difficulty is based on the level you're on.");
	static double currentYdub;
	static int currentY = 550;
	public lvl1() {
		super();
		
	}
	
	
	
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		
		llabel.setBounds(0,0,50,50);
		instructions.setBounds(200, 8, 600, 14);
		pressG.setBounds(250, 150, 600, 150);
		add(pressG);
		add(llabel);
		add(instructions);
		llabel.setText("Level " + GameFrame.getLevelNum());
		
		if (GameFrame.getLevelNum() == 1) {
			g.setColor(Color.black);
			g.drawLine(400, 400, 600, 400);
			g.drawLine(200, 300, 300, 300);
			g.drawLine(600, 200, 800, 200);
			g.drawLine(300, 100, 400, 100);
			//initial ground
			g.setColor(Color.green);
			g.fillRect(0,550,800,550);
		}
		if (GameFrame.getLevelNum() == 2) {
			instructions.setText("");
			g.setColor(Color.blue);
			g.drawLine(100, 450, 150, 450);
			g.drawLine(200, 350, 210, 350);
			g.drawLine(100, 200, 200, 200);
			g.drawLine(600, 300, 800, 300);
			g.drawLine(700, 150, 800, 150);
			
			g.fillRect(0,550,800,550);
		}
		if (GameFrame.getLevelNum() == 3) {
			instructions.setText("This is the last level unfortunately :(");
			g.setColor(Color.green);
			g.drawLine(350, 450, 450, 450);
			g.drawLine(000, 350, 100, 350);
			g.drawLine(700, 350, 800, 350);
			g.drawLine(350, 250, 450, 250);
			g.drawLine(350, 150, 450, 150);
			g.drawLine(350, 100, 450, 100);
			
			g.fillRect(0,550,800,550);
		}
		if (GameFrame.getLevelNum() == 4) {
			instructions.setText("The End of Dragon Oath V: The Next Generation - The Journey of Pu Liu (Public Beta)");
			g.fillRect(0,550,800,550);
		}

	}
	
	/**
	 * Method to for the main to get the currentY value from this class
	 * @return returns currentY
	 */
	public static int getCurrentY () {
		return currentY;
	}
	
	/**
	 * Removes the initial text describing levels and difficulty.
	 */
	public void removeG() {
		pressG.setVisible(false);
		revalidate();
	}
	
	/**
	 * Returns a true or false statement to GameFrame based on whether the hero is onGround or not based on the current level and the rectangles specified.
	 * @param heroRect takes the position of the hero as a rectangle
	 * @return returns a true or false statement of whether the hero intercepts the line
	 */
	public static boolean collisionCheck (Rectangle heroRect) {
		if (GameFrame.getLevelNum() == 1) {
			if ((heroRect.getBounds().intersectsLine(400,400,600,400)) || (heroRect.getBounds().intersectsLine(200,300,300,300)) || (heroRect.getBounds().intersectsLine(600, 200, 800, 200)) || (heroRect.getBounds().intersectsLine(300, 100, 400, 100))) {
				currentYdub = heroRect.getY();
				currentY = ((int)currentYdub +49);
				return true;
//				set local currenty based on the one there and then somehow sends to gameframe... 
			}
			else {
				currentY = 550;
				return false;
			}
		}
		else if (GameFrame.getLevelNum() == 2) {
			if ((heroRect.getBounds().intersectsLine(100, 450, 150, 450)) || (heroRect.getBounds().intersectsLine(200, 350, 210, 350)) || (heroRect.getBounds().intersectsLine(100, 200, 200, 200)) || (heroRect.getBounds().intersectsLine(600, 300, 800, 300)) || (heroRect.getBounds().intersectsLine(700, 1500, 800, 150))) {
				currentYdub = heroRect.getY();
				currentY = ((int)currentYdub +49);
				return true;
//				set local currenty based on the one there and then somehow sends to gameframe... 
			}
			else {
				currentY = 550;
				return false;
			}
		}
		
		else if (GameFrame.getLevelNum() == 3) {
			if ((heroRect.getBounds().intersectsLine(350, 450, 450, 450)) || (heroRect.getBounds().intersectsLine(000, 350, 100, 350)) || (heroRect.getBounds().intersectsLine(700, 350, 800, 350)) || (heroRect.getBounds().intersectsLine(350, 250, 450, 250)) || (heroRect.getBounds().intersectsLine(350, 150, 450, 150)) || (heroRect.getBounds().intersectsLine(350, 100, 450, 100))) {
				currentYdub = heroRect.getY();
				currentY = ((int)currentYdub +49);
				return true;
//				set local currenty based on the one there and then somehow sends to gameframe... 
			}
			else {
				currentY = 550;
				return false;
			}
		}
		else {
			//makes error go away
			return false;
		}
		
	}
}
