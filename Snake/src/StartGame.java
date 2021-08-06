import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class StartGame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(10,10,900,720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//正常游戏界面都应在面上！
		frame.add(new GamePanel());
		frame.setVisible(true);
		
	}
	

	
	
	
}
