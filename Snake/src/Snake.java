import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Snake extends Canvas implements Runnable {
	
	public Node[] nodeSnake = new Node[13];
	
	public Snake() {
		this.setPreferredSize(new Dimension(280,280));
		for(int i = 0; i < nodeSnake.length; i++) {
			nodeSnake[i] = new Node(0,0);
		}
	}
	
	public void tick() {
		
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
}
