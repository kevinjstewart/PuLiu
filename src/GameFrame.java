import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.event.*;


/*
 * TODO: Add music. @home
 * 
 * TODO: Clean code
 */

@SuppressWarnings("unused")
public class GameFrame extends JFrame implements ActionListener, ChangeListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private static RandomAccessFile lvlFile;
	private static final int RECORD_SIZE = 1;
	lvl1 lv;
	static int levelnum = 1;
	public final int initGroundY = 550;
	public static int currentGroundY = 550;
	private JPanel holder;
	private Hero hero;
	private Thread heroControlThread;
	private HeroThread heroControl;
	public JLabel levelLbl;
	
	
	
	private final double gravity = 10.0; 
	private double velocity = 0;
	public int posx = 100;
	public int posy = 100;
	private boolean onGround = false;
	private boolean left = false;
	private boolean right = false;
	private Timer timer;
	
	GameFrame(String title) throws AWTException, IOException {
		super(title); 	
		addKeyListener(this); 
		setLayout(null);
		this.setBounds(0, 0, 800, 600);	
		startGame();		
	}
	
	/**
	 * This is ran when the game starts. (A lot of the initial base GameFrame code is based on the example one posted with Sonic)
	 * @throws IOException
	 */
	public void startGame() throws IOException {
		//makes holder panel
		holder = new JPanel();
		holder.setBounds(0,0,800,600);
		holder.setOpaque(true);
		holder.setVisible(true);
		holder.setLayout(null);
		holder.setBackground(Color.black);
		
		lvlFile = new RandomAccessFile("lvlFile.txt", "rw");
	
		
		//Create timer that updates hero position
		timer = new Timer(50,this);
		timer.setActionCommand("timing");
		timer.setInitialDelay(0);
		
		//init hero
		hero = new Hero();
		hero.setVisible(true);
		hero.setOpaque(true);
		hero.setRight();
		

		lv = new lvl1();

		lv.setVisible(true);
		lv.setOpaque(true);
		lv.setBounds(0, 0, 800, 600);

		//adds hero to holder and holder to JPanel
		holder.add(hero);
		holder.add(lv);
		add(holder);


		//spam
		validate();
		repaint();
	}
	
	/**
	 * Updates hero's location and revalidates JFrame.
	 */
	public void reDrawHero() {
		hero.setLocation(posx,posy);
		validate();
		repaint();
	}
	
	
	/**
	 * gets Keypresses and starts a timer for the movement ones.
	 */
	public void keyPressed(KeyEvent event) {
		
		
		if (event.getKeyChar() == 'g') {
			lv.removeG();
			heroControl = new HeroThread();
			heroControl.startThread();
			heroControlThread = new Thread(heroControl);
			heroControlThread.start();
		}
		
		//W pressed
		if ((event.getKeyCode() == KeyEvent.VK_W) && (onGround == true)) {
			System.out.println("w pressed");
			hero.isJumping();
			posy = currentGroundY - 51;
			velocity = -60;
		}
		
		//A pressed
		if ((event.getKeyCode() == KeyEvent.VK_A) && (left == false)) {
			left = true;
			hero.setLeft();
			timer.start();
		}
		
		//D pressed
		if ((event.getKeyCode() == KeyEvent.VK_D) && (right == false)) {
			right = true;
			hero.setRight();
			timer.start();
		}
		
		//P pressed
		if (event.getKeyCode() == KeyEvent.VK_P) {
			System.out.println("P pressed");
			try {
				saveMeth();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//L pressed
		if (event.getKeyCode() == KeyEvent.VK_L) {
			try {
				loadMeth();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * Stops timer when key is released for movement.
	 */
	public void keyReleased(KeyEvent event) {
		
		if (event.getKeyChar() == 'a') {
			left = false;
			timer.stop();
		}
		
		if (event.getKeyChar() == 'd') {
			right = false;
			timer.stop();
		}
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	public void stateChanged(ChangeEvent arg0) {
		
	}
	
	/**
	 * moves left/right while timing initiated from the keypressed is going
	 */
	public void actionPerformed(ActionEvent event) {
		
		if (event.getActionCommand().equals("timing")) {
			if (left == true) {
				posx -= 30;
			}
			
			if (right == true) {
				posx += 30;
			}
		}
		
	}
	
	/**
	 * Saves level when called upon.
	 * @throws IOException
	 */
	public void saveMeth () throws IOException{
		lvlFile.seek(0*RECORD_SIZE);
		String levelString = Integer.toString(levelnum);
		lvlFile.writeBytes(levelString);
	}
	
	/**
	 * Loads level when called upon.
	 * @throws IOException
	 */
	public void loadMeth () throws IOException{
		byte[] lvtemp = new byte[1];
		
		lvlFile.seek(0*RECORD_SIZE);
		for (int j = 0; j < 1; j++) {
			lvtemp[j] = lvlFile.readByte();
		}
		String yolo = new String(lvtemp, "UTF-8");
		System.out.println("YOLO = " + yolo);
		levelnum = Integer.parseInt(yolo);
	}
	
	/**
	 * Allows other classes (lvl1.java) to update the currentY
	 * @param yolo :)
	 */
	public static void setCurrentY(int yolo) {
		currentGroundY = yolo;
	}
	
	/**
	 * Allows lvl1 class to see lvl number
	 * @return level the player is on
	 */
	public static int getLevelNum() {
		return levelnum;
	}
	/**
	 * Thread that the hero(player) is based on. Based heavily on the code in the sonic example posted to EDU 2.0
	 * @author Kevin
	 *
	 */
	private class HeroThread implements Runnable {
		
		private boolean begin = false;
		
		public HeroThread () {
			
		}
		
		public void startThread() {
			begin = true;
		}
		
		public void run() {
			if (begin == true) {
			}
			
			while (begin == true) {
				if (posx < 0) {
					posx = 0;
				}
				if (posx > 750) {
					posx = 750;
				}
				if (posy < 0) {
					levelnum = levelnum + 1;
					posx = 90;
					posy = 500;
					currentGroundY = 550;
					onGround = true;
					repaint();
				}
				if (posy < currentGroundY - 50) { //pu above ground check
					onGround = false;
					System.out.println("onGround == false");
					System.out.println(hero.getBounds());
					System.out.println("onGround = false");
					velocity += gravity;
					onGround = lvl1.collisionCheck(hero.getBounds());
					currentGroundY = lvl1.getCurrentY();
					if ((posy + velocity) > (currentGroundY - 50)) {
						posy = currentGroundY - 50;
						onGround = true;
						hero.isNotJumping();
						if (posy > 550) {
							onGround = true;
							System.out.println("onGround = true");
						}
					}
					else {
						posy += (int) velocity;
						onGround = false;
					}
					if (lvl1.collisionCheck(hero.getBounds() ) == true ) {
					}
					reDrawHero();
				}
				else {
					if (lvl1.collisionCheck(hero.getBounds() ) == false) {
						currentGroundY = 550;
					}
					System.out.println(hero.getBounds());
					reDrawHero();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

