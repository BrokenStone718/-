import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

//��Ϸ���
public class GamePanel extends JPanel implements KeyListener,ActionListener{
	
	//�����ߵ����ݽṹ
	int length;//�ߵĳ���
	int[] snakeX = new int[600]; //�ߵ�x����
	int[] snakeY = new int[500]; //�ߵ�y����
	String fx; //��ʼ����������
	boolean isStart = false; //Ĭ�ϲ���ʼ
	boolean isFail = false;//Ĭ�ϲ�ʧ��
	
	//ʳ�������
	int foodx;
	int foody;
	Random random = new Random();
	
	int score;
	
	//��ʱ��  ��msΪ��λ 
	Timer timer = new Timer(100,this);//100msִ��һ��
	
	//������
	public GamePanel() {
		init();
		//��ý���ͼ����¼�
		this.setFocusable(true); //��ý����¼�
		this.addKeyListener(this);//��ü��̼����¼�
		timer.start();
	}
	
	//��ʼ������
	public void init() {
		length = 3;
		snakeX[0] = 100;snakeY[0] = 100;//�Դ�
		snakeX[1] = 75;snakeY[1] = 100;//��һ������
		snakeX[2] = 50;snakeY[2] = 100;//�ڶ�������
		fx = "R";
		
		//��ʳ��������ڽ�����
		foodx = 25 + 25*random.nextInt(30);
		foody = 25 + 25*random.nextInt(20);
		
		score = 0;
		
	}
	
	
	//�������
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.fillRect(15, 35, 850, 600);
		
		//������
		g.setColor(Color.BLACK);
		g.setFont(new Font("΢���ź�",Font.BOLD,20));
		g.drawString("����" + length,500,20);
		g.drawString("����"+score, 750, 20);
		
		
		//��ʳ��
		Data.food.paintIcon(this, g, foodx, foody);
		
		//��С�߻���ȥ
		if(fx.equals("R")) {
			Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
		}else if(fx.equals("L")) {
			Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
		}else if(fx.equals("U")) {
			Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
		}else if(fx.equals("D")) {
			Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
		}
		
		for(int i = 1;i<length;i++) {
			Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
		}
		//��Ϸ״̬
		if (isStart == false) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("΢���ź�",Font.BOLD,40));
			g.drawString("���¿ո�ʼ��Ϸ", 300, 300);
		}
		
		if(isFail) {
			g.setColor(Color.RED);
			g.setFont(new Font("΢���ź�",Font.BOLD,40));
			g.drawString("���¿ո����¿�ʼ��Ϸ", 300, 300);
		}
		
		
		
	}

	//���̼����¼�
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); //��ü��̰�������һ��
		
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFail) {
				//���¿�ʼ
				isFail = false;
				init();
			}else {
				isStart = !isStart;//ȡ��
			}

			repaint();
		}
		
		//С���ƶ�
		if(keyCode == KeyEvent.VK_UP) {
			fx = "U";
		}else if(keyCode == KeyEvent.VK_DOWN){
			fx = "D";
		}else if(keyCode == KeyEvent.VK_LEFT){
			fx = "L";
		}else if(keyCode == KeyEvent.VK_RIGHT){
			fx = "R";
		}
		
	}

	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//�¼�����---��Ҫͨ���̶��¼���ˢ��
	@Override
	public void actionPerformed(ActionEvent e) {
		if (isStart && isFail==false) {//�����Ϸ�ǿ�ʼ״̬������С�߶�����
			
			//ʳ��
			if (snakeX[0] == foodx && snakeY[0] == foody) {
				//����+1
				length++;
				//����+10
				score+=10;
				//�ٴ����ʳ��
				foodx = 25 + 25*random.nextInt(30);
				foody = 25 + 25*random.nextInt(20);
			}
			
			
			//��
			for(int i = length-1;i>0;i--) {
				snakeX[i] = snakeX[i-1]; //��ǰ�ƶ�һ��
				snakeY[i] = snakeY[i-1];
						
			}
			
			//����
			if (fx.equals("R")) {
				snakeX[0] = snakeX[0]+25;
				if(snakeX[0]>850) {
					snakeX[0]=25;
				}
			}else if (fx.equals("L")) {
				snakeX[0] = snakeX[0]-25;
				if(snakeX[0]<25) {
					snakeX[0]=850;
				}
			}else if (fx.equals("U")) {
				snakeY[0] = snakeY[0]-25;
				if(snakeY[0]<25) {
					snakeY[0]=600;
				}
			}else if (fx.equals("D")) {
				snakeY[0] = snakeY[0]+25;
				if(snakeY[0]>600) {
					snakeY[0]=25;
				}
			}
			
			//ʧ���ж�,ײ���Լ�
			for(int i = 1;i<length;i++) {
				if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]) {
					isFail = true;
				}
			}
			
			
			repaint(); //�ػ�
		}
		timer.start();
		
	}
	
	

}
