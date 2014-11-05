import java.awt.AWTException;
import java.io.IOException;
import javax.swing.JFrame;

public class GThread implements Runnable {
	
	public GThread() {
		
	}
	
	public void run() {
		GameFrame gFrame;
		
		try {
			gFrame = new GameFrame("Dragon Oath V: The Next Generation - The Journey of Pu Liu");
			gFrame.setVisible(true);
			gFrame.setDefaultCloseOperation(JFrame.ABORT);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
