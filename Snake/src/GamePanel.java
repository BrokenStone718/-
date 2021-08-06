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

//游戏面板
public class GamePanel extends JPanel implements KeyListener,ActionListener{
	
	//定义蛇的数据结构
	int length;//蛇的长度
	int[] snakeX = new int[600]; //蛇的x坐标
	int[] snakeY = new int[500]; //蛇的y坐标
	String fx; //初始化方向向右
	boolean isStart = false; //默认不开始
	boolean isFail = false;//默认不失败
	
	//食物的坐标
	int foodx;
	int foody;
	Random random = new Random();
	
	int score;
	
	//定时器  以ms为单位 
	Timer timer = new Timer(100,this);//100ms执行一次
	
	//构造器
	public GamePanel() {
		init();
		//获得焦点和键盘事件
		this.setFocusable(true); //获得焦点事件
		this.addKeyListener(this);//获得键盘监听事件
		timer.start();
	}
	
	//初始化方法
	public void init() {
		length = 3;
		snakeX[0] = 100;snakeY[0] = 100;//脑袋
		snakeX[1] = 75;snakeY[1] = 100;//第一个身体
		snakeX[2] = 50;snakeY[2] = 100;//第二个身体
		fx = "R";
		
		//把食物随机放在界面上
		foodx = 25 + 25*random.nextInt(30);
		foody = 25 + 25*random.nextInt(20);
		
		score = 0;
		
	}
	
	
	//绘制面板
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.fillRect(15, 35, 850, 600);
		
		//画积分
		g.setColor(Color.BLACK);
		g.setFont(new Font("微软雅黑",Font.BOLD,20));
		g.drawString("长度" + length,500,20);
		g.drawString("分数"+score, 750, 20);
		
		
		//画食物
		Data.food.paintIcon(this, g, foodx, foody);
		
		//把小蛇画上去
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
		//游戏状态
		if (isStart == false) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("微软雅黑",Font.BOLD,40));
			g.drawString("按下空格开始游戏", 300, 300);
		}
		
		if(isFail) {
			g.setColor(Color.RED);
			g.setFont(new Font("微软雅黑",Font.BOLD,40));
			g.drawString("按下空格重新开始游戏", 300, 300);
		}
		
		
		
	}

	//键盘监听事件
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); //获得键盘按键是哪一个
		
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFail) {
				//重新开始
				isFail = false;
				init();
			}else {
				isStart = !isStart;//取反
			}

			repaint();
		}
		
		//小蛇移动
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

	//事件监听---需要通过固定事件来刷新
	@Override
	public void actionPerformed(ActionEvent e) {
		if (isStart && isFail==false) {//如果游戏是开始状态，就让小蛇动起来
			
			//食物
			if (snakeX[0] == foodx && snakeY[0] == foody) {
				//长度+1
				length++;
				//分数+10
				score+=10;
				//再次随机食物
				foodx = 25 + 25*random.nextInt(30);
				foody = 25 + 25*random.nextInt(20);
			}
			
			
			//移
			for(int i = length-1;i>0;i--) {
				snakeX[i] = snakeX[i-1]; //向前移动一节
				snakeY[i] = snakeY[i-1];
						
			}
			
			//走向
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
			
			//失败判断,撞到自己
			for(int i = 1;i<length;i++) {
				if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]) {
					isFail = true;
				}
			}
			
			
			repaint(); //重画
		}
		timer.start();
		
	}
	
	

}
