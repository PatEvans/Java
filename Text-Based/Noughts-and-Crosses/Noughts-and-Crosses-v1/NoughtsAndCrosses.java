package noughtscrosses;
import java.util.*;
public class NoughtsAndCrosses {
	static int x=0;
	static int count=1;
	static int draw=0;
	public static int xscoreboard = 0;
	public static int oscoreboard = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainprog();
			
	}
	
	public static void mainprog(){
		String[][] position=new String[3][4];
		draw=0;
		count=0;
		clearscreen();
		render(cleararray(position));
		System.out.println("");
		while (true){
			render(input(position));
		}
	}
	
	//most of what the user sees
	//once rendered, check if a win or draw has been accomplished
	public static void render(String[][] position){
		System.out.println("       |         |         |         |");
		System.out.println("       |    1    |    2    |    3    |");
		System.out.println("       |         |         |         |");
		System.out.println("-------|---------|---------|---------|  ");
		System.out.println("       |         |         |         |");
		System.out.println("   1   |    "+position[0][0]+"    |    "+position[1][0]+"    |    "+position[2][0]+"    |");
		System.out.println("       |         |         |         |");
		System.out.println("-------|---------|---------|---------|");
		System.out.println("       |         |         |         |");
		System.out.println("   2   |    "+position[0][1]+"    |    "+position[1][1]+"    |    "+position[2][1]+"    |");
		System.out.println("       |         |         |         |");
		System.out.println("-------|---------|---------|---------|");
		System.out.println("       |         |         |         |");
		System.out.println("   3   |    "+position[0][2]+"    |    "+position[1][2]+"    |    "+position[2][2]+"    |");
		System.out.println("       |         |         |         |");
		cleararray(position);
		
		if (check(position)==true){

			if (position[0][3]=="X" && draw!=1){
				System.out.println("CROSSES WIN");
				winner("Crosses");
			}
			else if(position[0][3]=="O" && draw!=1){
				System.out.println("NOUGHTS WIN");
				winner("Noughts");
			}
			else if (draw==1){
				System.out.println("DRAW");
				winner("Draw");
			}
		}
	}
	
	//inputs as well as validation e.g. if in range
	public static String[][] input(String[][] position){
		x=x+1;
		String y=" ";
		int row=1;
		int column=1;
		boolean flag=false;
		while (flag==false){
			if (x%2==0){
				System.out.println("");
				System.out.println("NOUGHTS TURN:Input a column number:");
				y="O";
				position[0][3]="O";
			}
			else{
				System.out.println("CROSSES TURN: Input a column number:");
				y="X";
				position[0][3]="X";
			}
			Scanner scanner = new Scanner( System.in );
			row= scanner.nextInt();
			row=row-1;
			System.out.println("Input a row number:");
			column= scanner.nextInt();
			column=column-1;
			if (column<0 || column >2 || row<0 || row >2){
				System.out.println("ERROR: please choose another position");
			}
			else if (position[row][column]=="O"||cleararray(position)[row][column]=="X"){
				System.out.println("ERROR: please choose another position");
				cleararray(position);
			}
			else{
				flag=true;
				position[row][column]=y;
			}
			
			

		}
		clearscreen();
		return position;
		
	}
	
	//replaces all null elements with ""
	//allows for output
	public static String[][] cleararray(String[][] position){
		int i=0;
		int t=0;
		boolean swap=false;
		while (i<=2){
			while (t<=2){
				if (position[i][t]==null){
					position[i][t]=" ";
				}
				if (!(position[i][t].equals("O") || position[i][t].equals("X"))){
					swap=true;
				}
				t=t+1;
			}
			t=0;
			i=i+1;	
		}
		if (swap==false){
			draw=1;
		}
		
		return position;

	}

	public static void clearscreen(){
		char c = '\n';
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}
	
	//messy method to check if a win is achieved
	//change in next version
	public static boolean check(String[][] position){
		int count=0;
		boolean win=false;
		if (((position[count][0] == "O") &&  (position[count+1][0] == "O") && (position[count+2][0] == "O"))||((position[count][0] == "X") &&  (position[count+1][0] == "X") && (position[count+2][0] == "X"))){
			win=true;
		}else if(((position[count][1] == "O") &&  (position[count+1][1] == "O") && (position[count+2][1] == "O"))||((position[count][1] == "X") &&  (position[count+1][1] == "X") && (position[count+2][1] == "X"))){
			win=true;
		}else if(((position[count][2] == "O") &&  (position[count+1][2] == "O") && (position[count+2][2] == "O"))||((position[count][2] == "X") &&  (position[count+1][2] == "X") && (position[count+2][2] == "X"))){
			win=true;
		}
		else if(((position[0][count] == "O") &&  (position[0][count+1] == "O") && (position[0][count+2] == "O"))||((position[0][count] == "X") &&  (position[0][count+1] == "X") && (position[0][count+2] == "X"))){
			win=true;
		}
		else if(((position[1][count] == "O") &&  (position[1][count+1] == "O") && (position[1][count+2] == "O"))||((position[1][count] == "X") &&  (position[1][count+1] == "X") && (position[1][count+2] == "X"))){
			win=true;
		}
		else if(((position[2][count] == "O") &&  (position[2][count+1] == "O") && (position[2][count+2] == "O"))||((position[2][count] == "X") &&  (position[2][count+1] == "X") && (position[2][count+2] == "X"))){
			win=true;
		}
		else if(((position[0][count] == "O") &&  (position[1][count+1] == "O") && (position[2][count+2] == "O"))||((position[0][count] == "X") &&  (position[1][count+1] == "X") && (position[2][count+2] == "X"))){
			win=true;
		}
		else if(((position[0][2] == "O") &&  (position[1][1] == "O") && (position[2][0] == "O"))||((position[0][2] == "X") &&  (position[1][1] == "X") && (position[2][0] == "X"))){
			win=true;
		}
		else if(draw==1){
			win=true;
		}
		return win;
	}
	public static void winner(String x){
		if (x=="Noughts"){
				oscoreboard=oscoreboard+1;
				
		}
		else if (x=="Crosses"){
			xscoreboard=xscoreboard+1;
		}
		System.out.println("");
		System.out.println("NOUGHTS: "+oscoreboard +"  CROSSES: "+ xscoreboard);
		System.out.println("");
		System.out.println("Do you want to play again?");
		Scanner scanner = new Scanner( System.in );
		String winchoice= scanner.nextLine();
		System.out.println(winchoice);
		if(winchoice.equals("y")|| winchoice.equals("Y")){
			mainprog();
		}else{
			System.exit(0);
		}

	}
}

