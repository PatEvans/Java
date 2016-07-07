package clicknoughts;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author pevans10
 */
public class ClickNoughts extends JFrame{
    ClickNoughts(JPanel Pane){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(Pane);
        setResizable(false);
        setVisible(true);
        setSize(320,350);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pane pane =new Pane();
        ClickNoughts clicknought = new ClickNoughts(pane);
    }
    
}
class Pane extends JPanel{
    Pane(){
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D dgraph= (Graphics2D) g;
        dgraph.setPaint(Color.GRAY);
        dgraph.fillRect(20,20,240,240);
        dgraph.setPaint(Color.BLACK);
		int x=1;
		while (x<3){
				dgraph.fillRect(20+(80*x),20,1,240);
				dgraph.fillRect(20,20+(80*x),240,1);
				x=x+1;
		}
    }
}
class Elements{
   public JButton[][] buttons(){
       JButton[][] buttons = new JButton[3][3];
       
       return buttons;
   }   
}
