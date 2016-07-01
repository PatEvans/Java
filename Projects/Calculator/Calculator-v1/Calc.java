package calculatorv1;
import java.util.*;
public class Calc {
	public static void main(String[] args){
		//Allows a calculation to be typed on one line
		//and interpreted as a calculation
		Split split = new Split();
		Calculation calculation = new Calculation();
		
		System.out.println("Input a calculation on a single line: e.g. 12 + 12");
		Scanner input = new Scanner(System.in);
		String x= input.nextLine();
		x=x.replaceAll("\\s+","");
		
		split.splitting(x);
		
		int position=split.position;
		String[] chars= split.chars;
		
		float numa=split.numa(position, x);
		float numb=split.numb(position, x); 
		
		System.out.println(calculation.result(chars,numa,numb));
	}
}


class Split{
	String[] chars;
	int position;
	public void splitting(String x){
		String temp;
		int position=0;
		//each element in the array is the next character
		//this method needed to find the command character position
		String[] chars = new String[x.length()+1];
		for (int i=0; i<=(x.length())-1;i++){
			temp=x.substring(0,i+1);
			chars[i]=temp.substring(i);
			if (chars[x.length()]==null){
				if (chars[i].equals("*")){
					position=i;
					chars[x.length()]="*";
				}
				if (chars[i].equals("/")){
					position=i;
					chars[x.length()]="/";
				}
				if (chars[i].equals("+")){
					position=i;
					chars[x.length()]="+";
				}
				if (chars[i].equals("-")){
					position=i;
					chars[x.length()]="-";
				}
				if (chars[i].equals("^")){
					position=i;
					chars[x.length()]="^";
				}
			}
			else if(chars[i].equals("*")|| chars[i].equals("/") || chars[i].equals("+")){
				System.out.println("error");
			}
		}
		this.position=position;
		this.chars=chars;
	}
	public int position(){
		return position;
	}
	public String[] chars(){
		return chars;
	}
	
	public int numa(int position, String x){
		int numa = Integer.parseInt(x.substring(0,position));
		return numa;
	}
	
	public int numb(int position, String x){
		int numa = Integer.parseInt(x.substring(position+1));
		return numa;
	}
}

class Calculation{
	public float result(String[] chars,float numa,float numb){
		float result=0;
		if(chars[chars.length-1]=="*"){
			result=numa*numb;
		}
		else if(chars[chars.length-1]=="/"){
			result=numa/numb;
		}
		else if(chars[chars.length-1]=="+"){
			result=numa+numb;
		}
		else if(chars[chars.length-1]=="-"){
			result=numa-numb;
		}
		else if(chars[chars.length-1]=="^"){
			result=(float)Math.pow(numa,numb);
		}
		return result;
	}
}
