import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class StartGame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(10,10,900,720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//������Ϸ���涼Ӧ�����ϣ�
		frame.add(new GamePanel());
		frame.setVisible(true);
		
	}
	

	
	
	
}
