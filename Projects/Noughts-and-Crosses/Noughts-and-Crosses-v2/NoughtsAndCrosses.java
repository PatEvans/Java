package noughtscrosses;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;


public class MainFrame extends JFrame{
	MainFrame(JPanel panel){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(590,350);
		setResizable(false);
		add(panel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Elements elements=new Elements();
		JLabel[][] noclabels =elements.noclabels();
		JLabel[] gridlabels =elements.gridlabels();
		JLabel inplabel1 =elements.inplabel1();
		JLabel inplabel2  =elements.inplabel2();
		JLabel check  =elements.checklabel();
		JLabel box =elements.box();
		JLabel scorebox =elements.scorebox();
		JComboBox rowcombo = elements.rowcombo();
		JComboBox colcombo = elements.colcombo();
		JButton update=elements.update();
		JButton reset=elements.reset();
		
		Pane panel=new Pane(noclabels,gridlabels,box,scorebox,update,reset,inplabel1,inplabel2,check,rowcombo,colcombo);
		
		
		MainFrame frame=new MainFrame(panel);
	}

}

class Pane extends JPanel{
	public int count;
	public int crosscount;
	public int noughtcount;
	public boolean won;
	String current="crosses";
	Color cross = new Color(225, 238, 238);
	Color noughts= new Color(238, 210, 210);
	
	Pane(JLabel[][] noclabels,JLabel[] gridlabels,JLabel box,JLabel scorebox,JButton update,JButton reset,JLabel inplabel1,JLabel inplabel2,JLabel check,JComboBox rowcombo,JComboBox colcombo){
		int x=0;
		setLayout(null);
		
		JLabel reference=new JLabel();
		reference.setLocation(295,175);
		add(reference);
		
		Listener listen = new Listener(noclabels,box,check,rowcombo,colcombo,reference);
		update.addActionListener(listen.actionup);
		reset.addActionListener(listen.actionres);
		for (int n=0; n<=5; n++){
			add(gridlabels[n]);
		}
		for (int i=0; i<3;i++){
			while (x<3){
				//i is x coordinate
				//x is y
				add(noclabels[i][x]);
				x=x+1;
			}
			x=0;
		}
		add(inplabel1);
		add(inplabel2);
		add(box);
		add(scorebox);
		add(rowcombo);
		add(colcombo);
		add(check);
		add(reset);
		add(update);
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D dgraphics=  (Graphics2D) g;
		//box background
		Listener listen = new Listener(null,null,null,null,null,null);
		if((count%2)==0){
			dgraphics.setPaint(noughts);
		}
		else{
			dgraphics.setPaint(cross);
			
		}
		System.out.println(count);
		dgraphics.fillRect(20,20,275,275);
		dgraphics.setPaint(Color.GRAY);
		dgraphics.drawRect(20,20,275,275);
				
		//Grid drawing
		dgraphics.setPaint(Color.BLACK);
		int x=0;
		while (x<3){
				dgraphics.fillRect(55+(80*x),20,1,275);
				dgraphics.fillRect(20,55+(80*x),275,1);
				x=x+1;
		}
	}
	
	private class Listener{
		JLabel[][] noclabels;
		JLabel box;
		JLabel check;
		JComboBox rowcombo;
		JComboBox colcombo;
		JLabel reference;
		
		
		Listener(JLabel[][] pLabel, JLabel bLabel,JLabel check,JComboBox rowcombo,JComboBox colcombo,JLabel reference){
			this.reference=reference;
			this.noclabels=pLabel;
			this.box=bLabel;
			this.check=check;
			this.rowcombo=rowcombo;
			this.colcombo=colcombo;
		}
		
		ActionListener actionup = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				String rowNum = (String)rowcombo.getSelectedItem();
		        String colNum = (String)colcombo.getSelectedItem();
		        
		        //Add to array and change turn
		        //As well as using validation method to check if already in a location
				if((count%2)==1){
					if (validation(noclabels,rowNum,colNum)==false){
						JOptionPane.showMessageDialog(box, "INVALID");
					}
					else{
						noclabels[Integer.parseInt(rowNum)-1][Integer.parseInt(colNum)-1].setText("O");
						box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"CROSSES TURN"));
						current="noughts";
						count=count+1;
					}
				}
				else{
					if (validation(noclabels,rowNum,colNum)==false){
						JOptionPane.showMessageDialog(box, "INVALID");
					}
					else{
						noclabels[Integer.parseInt(rowNum)-1][Integer.parseInt(colNum)-1].setText("X");
						box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"NOUGHTS TURN"));
						current="crosses";
						count=count+1;
					}
				}
				
				//if won make win equal true
				won=check(noclabels);
				if (won==true){
					repaint();
					JOptionPane.showMessageDialog(box, current.toUpperCase()+" WINS");
					noclabels =cleararray(noclabels);
					if (current.equals("crosses")){
						crosscount++;
						count=9;
					}else{
						noughtcount++;
						count=0;
					}
					check.setText("CROSSES: "+crosscount+"       NOUGHTS: "+ noughtcount);
					won=false;
				}
				else if(count%9==0){
					repaint();
					if (current.equals("noughts")){
						count=0;
					}else{
						count=9;
					}
					JOptionPane.showMessageDialog(box, "DRAW");
					noclabels = cleararray(noclabels);
					repaint();
				}
				repaint();
			}
		};
		
		ActionListener actionres = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				if (current.equals("noughts")){
					count=0;
				}else{
					count=9;
				}
				
				JOptionPane.showMessageDialog(box, "GAME HAS BEEN RESET");
				noclabels = cleararray(noclabels);
				repaint();
			}
		};
		
		//Messy checking methods - work tho
		public boolean validation(JLabel[][] position,String rowNum, String colNum){
			boolean valid;
			if ((position[Integer.parseInt(rowNum)-1][Integer.parseInt(colNum)-1].getText()).equals("X")||position[Integer.parseInt(rowNum)-1][Integer.parseInt(colNum)-1].getText().equals("O")){
				valid=false;
			}
			else{
				valid=true;
			}
			return valid;
		}
		
		public boolean check(JLabel[][] position){
			int count=0;
			boolean win=false;
			if ((position[0][0].getText().equals("O") &&  position[1][0].getText().equals("O") && position[2][0].getText().equals("O"))||(position[0][0].getText().equals("X") &&  position[1][0].getText().equals("X") && position[2][0].getText().equals("X"))){
				win=true;
			}else if ((position[count][1].getText().equals("O") &&  position[count+1][1].getText().equals("O") && position[count+2][1].getText().equals("O"))||(position[count][1].getText().equals("X") &&  position[count+1][1].getText().equals("X") && position[count+2][1].getText().equals("X"))){
				win=true;
			}else if((position[count][2].getText().equals("O") &&  position[count+1][2].getText().equals("O") && position[count+2][2].getText().equals("O"))||(position[count][2].getText().equals("X") &&  position[count+1][2].getText().equals("X") && position[count+2][2].getText().equals("X"))){
				win=true;
			}
			else if((position[0][count].getText().equals("O") &&  position[0][count+1].getText().equals("O") && position[0][count+2].getText().equals("O"))||(position[0][count].getText().equals("X") &&  position[0][count+1].getText().equals("X") && position[0][count+2].getText().equals("X"))){
				win=true;
			}
			else if((position[1][count].getText().equals("O") &&  position[1][count+1].getText().equals("O") && position[1][count+2].getText().equals("O"))||(position[1][count].getText().equals("X") &&  position[1][count+1].getText().equals("X") && position[1][count+2].getText().equals("X"))){
				win=true;
			}
			else if((position[2][count].getText().equals("O") &&  position[2][count+1].getText().equals("O") && position[2][count+2].getText().equals("O"))||(position[2][count].getText().equals("X") &&  position[2][count+1].getText().equals("X") && position[2][count+2].getText().equals("X"))){
				win=true;
			}
			else if((position[0][count].getText().equals("O") &&  position[1][count+1].getText().equals("O") && position[2][count+2].getText().equals("O"))||(position[0][count].getText().equals("X") &&  position[1][count+1].getText().equals("X") && position[2][count+2].getText().equals("X"))){
				win=true;
			}
			else if((position[0][2].getText().equals("O") &&  position[1][1].getText().equals("O") && position[2][0].getText().equals("O"))||(position[0][2].getText().equals("X") &&  position[1][1].getText().equals("X") && position[2][0].getText().equals("X"))){
				win=true;
			}
			return win;
		}
		
		public JLabel[][] cleararray(JLabel[][] labels){
			int x=0;
			for (int i=0; i<3;i++){
				while (x<3){
					//i is x coordinate
					//x is y
					labels[i][x].setText(" ");
					x=x+1;
				}
				x=0;
			}
			return labels;
		}
	}
}

class Elements{
	public JLabel[] gridlabels(){
		JLabel[] gridlabels=new JLabel[6];
		Integer x=1;
		Integer flag=0;
		Integer count=0;
		while (flag<2){
			gridlabels[count]= new JLabel(x.toString());
			x++;
			if (x==4){
				x=1;
				flag++;
			}
			count++;
		}
		x=0;
		while (x<3){
			gridlabels[x].setLocation(92+(80*x),30);
			gridlabels[x+3].setLocation(35,88+(80*x));
			gridlabels[x].setSize(50,15);
			gridlabels[x+3].setSize(50,15);
			x=x+1;
		}
		return gridlabels;
	}
	//here
	public JLabel[][] noclabels(){
		JLabel[][] noclabels= new JLabel[3][3];
		int x=0;
		for (int i=0; i<3;i++){
			while (x<3){
				//i is x coordinate
				//x is y
				noclabels[i][x]=new JLabel(" ");
				noclabels[i][x].setFont(new Font("Monospaced", Font.TRUETYPE_FONT, 45));
				noclabels[i][x].setSize(50,50);
				noclabels[i][x].setLocation(81+(i*80),69+(x*80));
				x=x+1;
			}
			x=0;
		}
		return noclabels;
	}
	
	public JButton update(){
		JButton button = new JButton("UPDATE");
		button.setLocation(330,160);
		button.setSize(90,25);
		button.setBackground(new Color(250,250,250));
		button.setBorderPainted(true);
		button.setFocusPainted(true);
		button.setContentAreaFilled(true);
		return button;
	}
	
	public JButton reset(){
		JButton button = new JButton("RESET");
		button.setLocation(435,160);
		button.setSize(90,25);
		button.setBackground(new Color(250,250,250));
		button.setBorderPainted(true);
		button.setFocusPainted(true);
		button.setContentAreaFilled(true);
		return button;
	}
	
	public JLabel box(){
		JLabel box = new JLabel("");
		box.setSize(250,200);
		box.setLocation(315,13);
		box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"CROSSES TURN"));
		return box;
	}
	
	public JLabel scorebox(){
		JLabel box = new JLabel("");
		box.setSize(250,78);
		box.setLocation(315,220);
		box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"SCORE"));
		return box;
	}
	
	public JComboBox rowcombo(){
		String[] options =  {"1","2","3"};
		JComboBox<String> combo = new JComboBox<>(options);
		combo.setBackground(new Color(250,250,250));
		combo.setSize(100,20);
		combo.setLocation(330,68);
		return combo;
	}
	
	public JComboBox colcombo(){
		String[] options =  {"1","2","3"};
		JComboBox<String> combo = new JComboBox<>(options);
		combo.setSize(100,20);
		combo.setBackground(new Color(250,250,250));
		combo.setLocation(330,120);
		return combo;
	}
	
	public JLabel inplabel1(){
		JLabel inlabel = new JLabel("COLUMN NUMBER ");
		inlabel.setSize(200,15);
		inlabel.setLocation(330,48);
		return inlabel;
	}
	
	public JLabel inplabel2(){
		JLabel inlabel2 = new JLabel("ROW NUMBER ");
		inlabel2.setLocation(330,100);
		inlabel2.setSize(200,15);
		return inlabel2;
	}

	public JLabel checklabel(){
		JLabel check= new JLabel("CROSSES: 0        NOUGHTS: 0");
		check.setLocation(350,255);
		check.setSize(200,15);
		return check;
	}

}



