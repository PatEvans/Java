package ball1;
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
		//setSize(530,575);
		setSize(180,188);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
			Elements elements= new Elements();
			JLabel[] label = elements.numbers();
			JLabel[] scoreboard = elements.scoreboard();
			JLabel[] scores= elements.scores();
			JButton[] choices = elements.choices();
			Pane pane = new Pane(label,choices,scoreboard,scores);
			//Ball frame = new Ball(pane);
			//choicePane choice = new choicePane(label);
			while (true){
				Thread.sleep(10);
				pane.BallMove();
			}
	}

}
class Pane extends JPanel implements KeyListener{
	Ball frame;
	boolean choice=false;
    int win=0;
    int level=1;
	int x=150;
	double y=60;
	int rectheight=0;
	int xspeed=0;
	double yspeed=0;
	int arrow=60;
	int firstsize=100;
	int turn=0;
	int totalblue=0;
	int totalred=0;
	Integer[] bluepoints;
	Integer[] redpoints;
	JLabel[] scoreboard;
	JLabel[] scores;
	JButton[] choices;
	JLabel relative=new JLabel();
	Pane pane;
	int pick;
	
	Pane(JLabel[] labels, JButton[] choices,JLabel[] scoreboard,JLabel[]scores){
		int x=0;
		relative.setLocation(400,250);
		add(relative);
		while(x<=4){
			add(labels[x]);
			x++;
		}
		x=0;
		while (x<=1){
			add(choices[x]);
			choices[x].addActionListener(action);
			x++;
		}
		x=0;
		while(x<=2){
			add(scoreboard[x]);
			x++;
		}
		x=0;
		while(x<=11){
			add(scores[x]);
			x++;
		}
		setBackground(new Color(250,250,250));
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
		choices[0].setFocusable(false);
		choices[1].setFocusable(false);
		this.choices=choices;
		this.scoreboard=scoreboard;
		this.scores=scores;
		pane=this;
		frame=new Ball(this);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
                win=0;
		Graphics2D dg= (Graphics2D) g;
		dg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		
		//indigo line
		dg.setPaint(new Color(103,58,183));
        dg.fillRect(0, 200, 280, 1);
        
        //blue line
        dg.setPaint(new Color(25,118,210));
        dg.fillRect(0, 290, 280, 1);
        
        //green line
        dg.setPaint(new Color(46,125,50));
        dg.fillRect(0, 370, 280, 1);
        
      //orange line
        dg.setPaint(new Color(255,87,34));
        dg.fillRect(0, 440, 280, 1);
        
      //red line
        dg.setPaint(new Color(211,47,47));
        dg.fillRect(0, 500, 280, 1);
        
        //separator
        dg.setPaint(new Color(129,129,129));
        dg.fillRect(280, 0, 1, 700);
        
        dg.setPaint(new Color(33,33,33));
        dg.fillRect(281, 0, 700, 700);
        
        if (choice==true){
        	if (turn%2==0){
        		dg.setPaint(new Color(229,57,53));
        	}else{
        		dg.setPaint(new Color(30,136,229));
        	}
        	Shape theCircle = new Ellipse2D.Double(x - 30, y - 30, 2.0 * 30, 2.0 * 30);
        	dg.fill(theCircle);	
        }
		
	    Shape triangle=new Polygon(
                new int[]{151, 156, 146},
                new int[]{70+(arrow-61), 60+(arrow-61), 60+(arrow-61)},
                3);
	    
	    
	    dg.setPaint(Color.black);
	    if (arrow>60){
	    	dg.fillRect(150, 60, 2, rectheight);
	    	//dg.translate(101, arrow);
	    	dg.fill(triangle);
	    }
	    
	}
	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (choice==true){
	    	switch( keyCode ) { 
	        	case KeyEvent.VK_SPACE:
	        		if (arrow<330){
	        			rectheight=rectheight+5;
	        			arrow=arrow+5;
	        		}
	        		break;
	    	}
	    }
	    
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (choice==true){
	    	switch( keyCode ) { 
	        	case KeyEvent.VK_SPACE:
	        		yspeed=(arrow-50)*0.1;
	        		break;
	    	}
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void BallMove() throws InterruptedException{
		y=y+yspeed;
		if (yspeed>0){
            arrow=50;
			yspeed=yspeed-0.2;
		}
        if (yspeed<0.2 && yspeed>-0.2){
        	yspeed=0;
        }
        else if (yspeed<0){
        	yspeed=yspeed+0.2;
        }
        //indigo
        if (win==0 && yspeed==0 && y>60 ||y<60 && win==0 && yspeed==0){
        	   Integer points=0;
               win=1;
               if(y>170 && y<260){
            	   //indigo
            	   points=5;
               }
               else if(y>=260 && y<340 ){
            	   //blue
            	   points=10;
               }
               else if(y>=340 && y<410 ){
            	   //blue
            	   points=20;
               }
               else if(y>=410 && y<470 ){
            	   //blue
            	   points=50;
               }
               else if(y>=470 && y<545 ){
            	   //blue
            	   points=100;
               }
               JOptionPane.showMessageDialog(relative,points+" points");
               turn++;
               if(turn%2==1 && (turn/2)<=redpoints.length){
            	   scoreboard[2].setText("<html><font color='#2196F3'><strong>&nbsp&nbsp&nbsp BLUE TURN</strong></font><br> TURN NUMBER: "+(turn/2+1)+"</html>");
            	   redpoints[turn/2]=points;
            	   scores[turn/2].setText("<html> RED TURN "+(turn/2+1)+":&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "+redpoints[turn/2].toString()+"</html>");
               }
               else if((turn/2)<=redpoints.length+1){
            	   if(pick==3 && (turn/2+1)==4){
            		   scoreboard[2].setText("<html><font color='#2196F3'><strong>&nbsp&nbsp&nbsp&nbsp BLUE TURN</strong></font><br> TURN NUMBER: 3</html>");            	   
            	   }
            	   else if(pick==5 && (turn/2+1)==6){
            		   scoreboard[2].setText("<html><font color='#2196F3'><strong>&nbsp&nbsp&nbsp&nbsp BLUE TURN</strong></font><br> TURN NUMBER: 3</html>");
            	   }
            	   else{
            		   scoreboard[2].setText("<html><font color='#EF5350'><strong>&nbsp&nbsp&nbsp RED TURN</strong></font><br> TURN NUMBER: "+(turn/2+1)+"</html>");
            	   }
            	   bluepoints[(int) (turn/2)]=points;
            	   scores[turn/2+4].setText("BLUE TURN "+(turn/2)+":    "+bluepoints[(int) (turn/2)].toString());
               }
               totalblue=0;
               totalred=0;
               for(int i =0; i<bluepoints.length;i++){
            	   if(!(bluepoints[i]==null)){
            	   	totalblue=totalblue+bluepoints[i];
            	   }
               }
               for(int i =0; i<redpoints.length;i++){
            	   if(!(redpoints[i]==null)){
            	   	totalred=totalred+redpoints[i];
            	   }
               }
               scores[10].setText("TOTAL BLUE :    "+totalblue);
               scores[11].setText("TOTAL RED :    "+totalred);
               if((turn/2)>redpoints.length-1){
            	   if(totalred>totalblue){
            		   JOptionPane.showMessageDialog(pane,"RED WINS");
            		   frame.dispose();
            		   frame.main(new String[] {"arg1", "arg2", "arg3"});
            		   
            	   }
            	   else if(totalred<totalblue){
            		   JOptionPane.showMessageDialog(pane,"BLUE WINS");
            		   frame.dispose();
            		   frame.main(new String[] {"arg1", "arg2", "arg3"});
            	   }
            	   choice=false;
               }
               x=150;
               y=60;
               rectheight=0;
               xspeed=0;
               yspeed=0;
               arrow=60;
               repaint();
        }
        if (y>515){
        	yspeed=-yspeed;
        }
        if (y<30){
        	yspeed=-yspeed;
        }
		repaint();
	}
	ActionListener action = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(((Component) event.getSource()).getName()=="3"){
				pick=3;
				redpoints=new Integer[3];
				bluepoints=new Integer[4];
				pane.remove(scores[3]);
				pane.remove(scores[4]);
				pane.remove(scores[8]);
				pane.remove(scores[9]);
				scoreboard[0].setSize(210,340);
				scoreboard[0].setLocation(300,120);
				for(int i=0;i<=4;i++){
					scores[i].setLocation(300,160+(i*70));
				}
				for(int i=5;i<=9;i++){
					scores[i].setLocation(300,180+((i-5)*70));
				}
				
			}else{
				pick=5;
				redpoints=new Integer[5];
				bluepoints=new Integer[6];
				scores[10].setLocation(300,470);
				scores[11].setLocation(300,450);
			}
			
			choice=true;
			frame.setSize(new Dimension(530,575));
			pane.setSize(new Dimension(530,575));
			pane.remove(choices[0]);
			pane.remove(choices[1]);
			pane.remove(scoreboard[1]);
		}
		
	};
        
}

class Elements{
	public JLabel[] numbers (){
		JLabel[] numbers = new JLabel[5];
		Integer x=0;
		while (x<=4){
			numbers[x]=new JLabel();
			numbers[x].setSize(150,50);
			x++;
		}
		numbers[0].setLocation(30,220);
		numbers[0].setText("5");
		
		numbers[1].setLocation(30,305);
		numbers[1].setText("10");
		
		numbers[2].setLocation(30,380);
		numbers[2].setText("20");
		numbers[3].setLocation(30,445);
		numbers[3].setText("50");
		numbers[4].setLocation(30,500);
		numbers[4].setText("100");
		return numbers;
	}
	public JLabel[] scoreboard(){
		JLabel[] scoreboard = new JLabel[3];
		scoreboard[0]=new JLabel();
		scoreboard[0].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),"<html><font color='white'>SCOREBOARD </font></html>"));
		scoreboard[0].setForeground(Color.white);
		scoreboard[0].setSize(210,430);
		scoreboard[0].setLocation(300,100);
		
		scoreboard[1]=new JLabel();
		scoreboard[1].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"MODE SELECTION"));
		scoreboard[1].setSize(150,140);
		scoreboard[1].setLocation(10,10);
		
		scoreboard[2]=new JLabel("<html><font color='#EF5350'><strong>&nbsp&nbsp&nbsp&nbsp RED TURN</strong></font><br> TURN NUMBER: 1</html>",SwingConstants.CENTER);
		scoreboard[2].setFont(new Font("Sans Serif", Font.TRUETYPE_FONT, 15));
		scoreboard[2].setForeground(Color.white);
		scoreboard[2].setSize(200,200);
		scoreboard[2].setLocation(300,-45);
		return scoreboard;
	}
	public JButton[] choices(){
		JButton[] choices = new JButton[2];
		choices[0]= new JButton("BEST OF 3");
		choices[0].setLocation(30,48);
		choices[0].setSize(105,30);
		choices[0].setName("3");
		choices[0].setFocusPainted(false);
		choices[0].setContentAreaFilled(true);
		choices[0].setBackground(Color.white);
		choices[0].setBorderPainted(true);
		
		choices[1]=new JButton("BEST OF 5");
		choices[1].setLocation(30,93);
		choices[1].setSize(105,30);
		choices[1].setFocusPainted(false);
		choices[1].setContentAreaFilled(true);
		choices[1].setBackground(Color.white);
		choices[1].setBorderPainted(true);
		return choices;
	}
	public JLabel[] scores(){
		JLabel[] scores = new JLabel[12];
		for(int i=0;i<=4;i++){
			scores[i]=new JLabel("<html> RED TURN "+(i+1)+":&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp X</html>",SwingConstants.CENTER);
			scores[i].setSize(200,25);
			scores[i].setLocation(300,130+(i*65));
			scores[i].setForeground(Color.white);
		}
		for(int i=5;i<=9;i++){
			scores[i]=new JLabel("BLUE TURN "+(i-4)+":    X",SwingConstants.CENTER);
			scores[i].setSize(200,25);
			scores[i].setLocation(300,150+((i-5)*65));
			scores[i].setForeground(Color.white);
		}
		scores[10]= new JLabel("TOTAL BLUE :    0",SwingConstants.CENTER);
		scores[10].setSize(200,25);
		scores[10].setLocation(300,400);
		scores[10].setForeground(Color.white);
		
		scores[11]= new JLabel("TOTAL RED :     0",SwingConstants.CENTER);
		scores[11].setSize(200,25);
		scores[11].setLocation(300,380);
		scores[11].setForeground(Color.white);
		return scores;
	}
}
