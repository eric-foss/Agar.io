import java.awt.Graphics;
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
	
	Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	
	
	
	public void paint(Graphics g) {
		
		
		
		super.paintComponent(g);
		
		
		for (Enemy e: enemies) {
			e.paint(g);
		
		}
		
		
		
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).checkBounds();
		}
		
		isColliding();
		
		
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	Driver(){
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		
		for (int i =0; i < 50; i++) {
			enemies.add(new Enemy());
		}
		
		
		Timer t = new Timer(16, this);
		t.start();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	public void isColliding() {
		int temp = enemies.size();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < temp; i++) {
			for(int o = 0; o < temp; o++) {
				if (i == o) {
					continue;
				}
				if ((int) distance(i, o) <= enemies.get(i).getRad() + enemies.get(o).getRad()) {
					if (enemies.get(i).getRad() >= enemies.get(o).getRad()) {
						enemies.remove(o);
						temp--;
					}
					
					else {
						enemies.remove(i);
						temp--;
					}
				}
				
			}
		}
	}
	
	public double distance(int i, int o) {
		return Math.sqrt(Math.pow((enemies.get(i).getX() + enemies.get(i).getRad()) - (enemies.get(o).getX() + enemies.get(o).getRad()), 2.0) +
				Math.pow((enemies.get(i).getY() + enemies.get(i).getRad()) - (enemies.get(o).getY() + enemies.get(o).getRad()), 2.0));
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
