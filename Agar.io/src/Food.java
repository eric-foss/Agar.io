import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
	
	private int x, y;
	private int vx, vy;
	private int vmag;
	private int theta;
	private int rad;
	private int mass;
	private int area;
	private Color color;
	
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	
	public Food() {
		
		rad =  10; 
		
		vmag = 0;
		theta = (int) (Math.random()*(360-0+1)+0);
		
		vx = (int) (vmag*(Math.cos(theta)));
		vy = (int) (vmag*(Math.sin(theta)));
		
		
		
		
		
		
		x = (int) (Math.random()*801);
		y = (int) (Math.random()*601);
		
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue =  (int)(Math.random()*256);
		color = new Color(red,green,blue);
		
		
		
		
		
	}
		

	public void checkBounds() {
		if (x < -500) {
			vx*= -1;
			x = -500;
		}
		
		if (x > 1500 - 2*rad) {
			vx *= -1;
			x = 1500 - 2*rad;
		}
		
		if (y < -500) {
			vy *= -1;
			y = -500;
		}
		
		if (y > 1500 - 2*rad) {
			vy *= -1;
			y = 1500 - 2*rad;
		}
	}
	
	
	public void collide(Enemy e) {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x,  y,  rad*2,  rad*2);
	}
	
	
	
	
	
	
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRad() {
		return rad;
	}
	
	
	public int getArea() {
		return area;
	}
	
	public void setArea(int area) {
		this.area = area;
		
	}

}
