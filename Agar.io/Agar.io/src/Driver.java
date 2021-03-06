import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;

public class Driver extends JPanel implements MouseListener, ActionListener{
	
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	ArrayList<Food> food = new ArrayList<Food>();
	
	Player player = new Player();
	
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	
	double vx;
	double vy;
	
	
	public void paint(Graphics g) {
		
		double mX = MouseInfo.getPointerInfo().getLocation().getX();
		double mY = MouseInfo.getPointerInfo().getLocation().getY();
		double pX = player.getX() + player.getRad();
		double pY = player.getY() + player.getRad();
		double dX = mX - pX;
		double dY = mY - pY;
		
		double theta;
		if (dY == 0) {
			theta = 0;
		} else {
			theta = Math.atan(dX/dY);
		}
		
		
		
		vx = Math.sin(theta)*player.getVmag();
		vy = Math.cos(theta)*player.getVmag();
		
		if (dY > 0) {
			vx *= -1;
			vy *= -1;
		}
		//System.out.println(dX + "," + dY + ", " + theta);
		
		super.paintComponent(g);
		
		
		for (Enemy e: enemies) {
			e.paint(g);
		
		}
		
		for(Food e: food) {
			e.paint(g);
			//System.out.println("Hello");
			//e.setVx(vx);
			//e.setVy(vy);
			
		}
		
		player.paint(g);
		
		//System.out.println(vx + ", " + vy);
		
		
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).checkBounds();
		}
		
		isColliding();
		moveAll();
		
		g.setColor(new Color(0, 0, 0));
		//g.drawOval(player.getX(), player.getY(), player.getRad()*2, player.getRad()*2);
		
		
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	Driver(){
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		
		for (int i =0; i < 30; i++) {
			enemies.add(new Enemy());
		}
		
		for(int i = 0; i < 500; i++) {
			food.add(new Food());
		}
		
		
		
		
		Timer t = new Timer(16, this);
		t.start();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public void isColliding() {
		int temp = enemies.size();
		int temp2 = food.size();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < temp; i++) {
			for(int o = 0; o < temp; o++) {
				if (i == o) {
					continue;
				}
				if ((int) distance(i, o) <= enemies.get(i).getRad() + enemies.get(o).getRad()) {
					if (enemies.get(i).getRad() >= enemies.get(o).getRad()) {
						enemies.get(i).setArea(enemies.get(i).getArea()+enemies.get(o).getArea());
						newRad(i);
						enemies.set(i, new Enemy(0));
						
						
					}
					
					else {
						enemies.get(o).setArea(enemies.get(i).getArea()+enemies.get(o).getArea());
						newRad(o);
						enemies.set(i, new Enemy(0));
						
					}
				}
				
			}
			for(int o = 0; o < temp2; o++) {
				if ( foodDistance(i, o) <= enemies.get(i).getRad() + food.get(o).getRad()) {
					enemies.get(i).setArea(enemies.get(i).getArea() + food.get(o).getArea());
					food.set(i, new Food(0));
					
				}
			}
			
	
			
		}
		for (int i = 0; i < temp2; i++) {
			if ( playerDistanceFood(i) <= food.get(i).getRad() + player.getRad()) {
				//System.out.println(player.getRad());
				if (player.getRad() >= food.get(i).getRad()) {
					player.setArea(player.getArea() + food.get(i).getArea());
					player.setRad((int) (Math.sqrt(player.getArea() / Math.PI)));
					food.set(i, new Food(0));
					player.newPos();
					
				}
			}
		}
		
		for (int i = 0; i < temp; i++) {
			if ( playerDistanceEnemy(i) <= enemies.get(i).getRad() + player.getRad()) {
				if (player.getRad() >= enemies.get(i).getRad()) {
					player.setArea(player.getArea() + enemies.get(i).getArea());
					player.setRad((int) (Math.sqrt(player.getArea() / Math.PI)));
					enemies.set(i, new Enemy(0));
					player.newPos();
					
				}
				
			}
		}
	}
	
	public double distance(int i, int o) {
		return Math.sqrt(Math.pow((enemies.get(i).getX() + enemies.get(i).getRad()) - (enemies.get(o).getX() + enemies.get(o).getRad()), 2.0) +
				Math.pow((enemies.get(i).getY() + enemies.get(i).getRad()) - (enemies.get(o).getY() + enemies.get(o).getRad()), 2.0));
	}
	
	public double foodDistance(int i, int o) {
		return Math.sqrt(Math.pow((enemies.get(i).getX() + enemies.get(i).getRad()) - (food.get(o).getX() + food.get(o).getRad()), 2.0) +
				Math.pow((enemies.get(i).getY() + enemies.get(i).getRad()) - (food.get(o).getY() + food.get(o).getRad()), 2.0));
	}
	
	public double playerDistanceFood(int i) {
		return Math.sqrt((Math.pow((player.getX() + player.getRad()) - (food.get(i).getX() + food.get(i).getRad()), 2.0)) + 
				Math.pow((player.getY() + player.getRad()) - (food.get(i).getY() + food.get(i).getRad()), 2.0));
				
	}
	
	public double playerDistanceEnemy(int i) {
		return Math.sqrt((Math.pow((player.getX() + player.getRad()) - (enemies.get(i).getX() + enemies.get(i).getRad()), 2.0)) + 
				Math.pow((player.getY() + player.getRad()) - (enemies.get(i).getY() + enemies.get(i).getRad()), 2.0));
				
	}
	
	public void newRad(int i) {
		enemies.get(i).setRad((int) (Math.sqrt(enemies.get(i).getArea() / Math.PI)));
	}
	
	public void moveAll() {
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setX(enemies.get(i).getX() + (int) (vx));
			enemies.get(i).setY(enemies.get(i).getY() + (int) (vy));
		}
		
		for(int i = 0; i < food.size(); i++) {
			food.get(i).setX(food.get(i).getX() + (int) (vx));
			food.get(i).setY(food.get(i).getY() + (int) (vy));
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}
	
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		
	}

}
