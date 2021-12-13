import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class Snake extends Canvas implements Runnable, KeyListener {
	
	public boolean right, left, up, down;
	
	public Node[] nodeSnake = new Node[13];
	
	public int macaX = 0, macaY = 0;
	
	public int score = -1;
	
	public int spd = 0;
	
	public Snake() {
		this.setPreferredSize(new Dimension(280,280));
		for(int i = 0; i < nodeSnake.length; i++) {
			nodeSnake[i] = new Node(0,0);
		}
		this.addKeyListener(this);
	}
	
	public void tick() {
		for(int i= nodeSnake.length -1; i > 0; i-- ) {
			nodeSnake[i].x = nodeSnake[i-1].x;
			nodeSnake[i].y = nodeSnake[i-1].y;
		}
		if(right) {
			nodeSnake[0].x+=spd;
		}else if(left) {
			nodeSnake[0].x-=spd;
		}else if(up) {
			nodeSnake[0].y-=spd;
		}else if(down) {
			nodeSnake[0].y+=spd;
		}
		if(new Rectangle(nodeSnake[0].x, nodeSnake[0].y, 13,13).intersects(new Rectangle(macaX, macaY,13,13))) {
			macaX = new Random().nextInt(280-13);
			macaY = new Random().nextInt(280-13);
			score++;
			spd++;
			System.out.println("Pontos: " +score);
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 280, 280);
		for(int i = 0; i < nodeSnake.length; i++) {
			g.setColor(Color.yellow);
			g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 13, 13);
		}
		g.setColor(Color.red);
		g.fillRect(macaX, macaY, 13, 13);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		Snake snake = new Snake();
		JFrame frame = new JFrame("SNAKE");
		frame.add(snake);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		new Thread(snake).start();
	}

	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			left = false;
			up = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			right = false;
			up = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
			right = false;
			left = false;
			down = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
			right = false;
			left = false;
			up = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
