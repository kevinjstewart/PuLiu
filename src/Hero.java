import java.awt.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Hero extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int posx;
	private int posy;
	
	ImageIcon[] puIconLeft = new ImageIcon[1];
	Image puImageLeft;

	ImageIcon[] puIconRight = new ImageIcon[1];
	Image puImageRight;
	
	ImageIcon[] puJumpIconRight = new ImageIcon[1];
	Image puJumpRightImage;
	
	ImageIcon[] puJumpIconLeft = new ImageIcon[1];
	Image puJumpLeftImage;
	
	boolean movingLeft = false;
	boolean jumping = false;
	
	public Hero() {
		super();
		
		puIconRight[0] = new ImageIcon("puLiuRight.png");
		puImageRight = puIconRight[0].getImage();
		
		puIconLeft[0] = new ImageIcon("puLiuLeft.png");
		puImageLeft = puIconLeft[0].getImage();
		
		puJumpIconRight[0] = new ImageIcon("puJumpRight.png");
		puJumpRightImage = puJumpIconRight[0].getImage();
		
		puJumpIconLeft[0] = new ImageIcon("puJumpLeft.png");
		puJumpLeftImage = puJumpIconLeft[0].getImage();
		
		this.setSize(50,50);
		this.setBounds(0,0,50,50);
	}
	
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g2 = (Graphics2D) g1;
		
		if (jumping == false) {
			if (movingLeft == false) {
				g2.drawImage(puImageRight,0,0,this);
			}
			else {
				g2.drawImage(puImageLeft,0,0,this);
			}
		}
		else {
			if (movingLeft == false) {
				g2.drawImage(puJumpLeftImage, 0, 0,this);
			}
			else {
				g2.drawImage(puJumpRightImage, 0, 0, this);
			}
		}
	}
	
	public void setLeft() {
		this.movingLeft = true;
	}
	
	public void setRight() {
		this.movingLeft = false;
	}
	
	public void isJumping() {
		this.jumping = true;
	}
	
	public void isNotJumping() {
		this.jumping = false;
	}
	
	public void setx(int posx) {
		this.posx = posx;
	}
	
	public void sety(int posy) {
		this.posx = posy;
	}
	
	public int getx(int posx) {
		return posx;
	}
	
	public int gety(int posy) {
		return posy;
	}
	

}
