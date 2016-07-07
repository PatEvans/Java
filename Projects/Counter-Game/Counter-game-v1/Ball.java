package ball;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class Ball extends JFrame{
	Ball(JPanel pane){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(pane);
		setSize(300,575);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
			Pane pane = new Pane();
			Ball frame = new Ball(pane);
			while (true){
				Thread.sleep(10);
				pane.BallMove();
			}
	}

}
class Pane extends JPanel implements KeyListener{
    int win=0;
    int level=1;
	int x=150;
	double y=60;
	int rectheight=0;
	int xspeed=0;
	double yspeed=0;
	int arrow=60;
	int firstsize=100;
	JLabel relative=new JLabel();
	Pane(){
		int x=0;
		relative.setLocation(150,250);
		add(relative);
		setBackground(new Color(250,250,250));
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
                win=0;
		Graphics2D dg= (Graphics2D) g;
		dg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		
		//indigo line
		dg.setPaint(new Color(103,58,183));
        dg.fillRect(0, 260, 500, 140);
        
        
		dg.setPaint(new Color(244,67,54));
		Shape theCircle = new Ellipse2D.Double(x - 30, y - 30, 2.0 * 30, 2.0 * 30);
                dg.fill(theCircle);	
	    
	    Shape triangle=new Polygon(
                new int[]{50, 55, 45},
                new int[]{10, 0, 0},
                3);
	    
	    
	    dg.setPaint(Color.black);
	    if (arrow>62){
	    	dg.fillRect(150, 60, 2, rectheight);
	    	dg.translate(101, arrow);
	    	dg.fill(triangle);
	    }
	    
	}
	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_SPACE:
	        	rectheight=rectheight+5;
	    	    arrow=arrow+5;
	            break;
	     }
	    
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		yspeed=(arrow-50)*0.1;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void BallMove(){
		y=y+yspeed;
		if (yspeed>0){
                        arrow=50;
			yspeed=yspeed-0.2;
		}
        if (yspeed<0){
               yspeed=0;
        }
        if (win==0 && yspeed==0 && y>280 && y<400 ){
               win=1;
        
               JOptionPane.showMessageDialog(relative,"You Win");
               x=150;
               y=60;
               rectheight=0;
               xspeed=0;
               yspeed=0;
               arrow=60;
               repaint();
        }
        
        else if(win==0 && y>60 && yspeed==0){
        	win=1;
            JOptionPane.showMessageDialog(relative,"You Lose");
            x=150;
            y=60;
            rectheight=0;
            xspeed=0;
            yspeed=0;
            arrow=60;
        }     
		repaint();
	}
        
}