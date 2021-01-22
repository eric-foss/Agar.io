import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	
	private int x, y;
	private int vx, vy;
	private int vmag;
	private int theta;
	private int rad;
	private int mass;
	private int area;
	private Color color;
	
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	
	public Enemy() {
		
		rad =  (int) (Math.random()*(60-10+1)+10);
		area = (int) ((Math.PI)*(Math.pow(rad, 2.0))); 
		
		vmag = (int) (1 + 100/rad);
		theta = (int) (Math.random()*(360-0+1)+0);
		
		vx = (int) (vmag*(Math.cos(theta)));
		vy = (int) (vmag*(Math.sin(theta)));
		
		
		
		
		
		
		x = (int) (Math.random()*(2501)-500);
		y = (int) (Math.random()*(2501)-500);
		
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue =  (int)(Math.random()*178);
		color = new Color(red,green,blue);
		
		
		
		
		
	}
	
public Enemy(int i) {
		
		rad =  (int) (Math.random()*(60-10+1)+10);
		area = (int) ((Math.PI)*(Math.pow(rad, 2.0))); 
		
		vmag = (int) (1 + 100/rad);
		theta = (int) (Math.random()*(360-0+1)+0);
		
		vx = (int) (vmag*(Math.cos(theta)));
		vy = (int) (vmag*(Math.sin(theta)));
		
		
		
		
		
		
		x = (int)((Math.random()>.5)?Math.random()*(-15+500+1)-500:Math.random()*(2000-800+1)+800);
		y = (int)((Math.random()>.5)?Math.random()*(-15+500+1)-500:Math.random()*(2000-800+1)+800);
		
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue =  (int)(Math.random()*178);
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
	
	public void setRad(int rad) {
		this.rad= rad;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getArea() {
		return area;
	}
	
	public void setArea(int area) {
		this.area = area;
		
	}
}