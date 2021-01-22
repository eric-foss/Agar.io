import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	
	private int x, y;
	private int vx, vy;
	private int vmag;
	private int theta;
	private int rad;
	private int mass;
	private int area;
	private Color color;
	private double mX, mY;
	private double pX, pY;
	
	
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	
	public Player() {
		
		rad =  15;
		area = (int) ((Math.PI)*(Math.pow(rad, 2.0))); 
		
		vmag = 1 + 100/rad;
		theta = (int) (Math.random()*(360-0+1)+0);
		
		vx = (int) (vmag*(Math.cos(theta)));
		vy = (int) (vmag*(Math.sin(theta)));
		
		
		
		
		
		
		x = 385;
		y = 285;
		
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue =  (int)(Math.random()*178);
		color = new Color(red,green,blue);
		
		
		vx = 0;
		vy = 0;
		
	}
	
	public void newPos() {
		x = 400 - rad;
		y = 300 - rad;
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
		update();
		g.setColor(color);
		g.fillOval(x,  y,  rad*2,  rad*2);
	}
	
	
	
	
	public void update() {
		x += vx;
		y += vy;
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
	
	public int getVmag() {
		return vmag;
	}
	
	
	public int getArea() {
		return area;
	}
	
	public void setArea(int area) {
		this.area = area;
		
	}
	
	public void setRad(int rad) {
		this.rad = rad;
		
		//System.out.println(rad);
	}
	
	

}
