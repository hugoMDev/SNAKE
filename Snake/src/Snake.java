import java.awt.Canvas;
import java.awt.Dimension;

public class Snake extends Canvas implements Runnable {
	
	public Snake() {
		this.setPreferredSize(new Dimension(280,280));
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
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
