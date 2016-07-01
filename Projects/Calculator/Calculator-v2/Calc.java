package calculatorv2;
import java.util.Scanner;

public class MainCalc {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Split split = new Split();
		
		Scanner input = new Scanner(System.in);
		String userinp=input.nextLine();
		userinp=userinp.replaceAll("\\s+","");
		
		int[] position=split.splitting(userinp);
		float[] nums= split.nums(userinp, position);
		
		System.out.println(split.calculation(nums));
	}
}
class Split{
	int count;
	int operator=0;
	public int[] splitting(String userinp){
		//only supports when same operator used
		int[] position=new int[20];
		position[0]=-1;
		int count=1;
		String temp1;
		String temp2;
		for (int i=1; i<=userinp.length()-1;i++){
			temp1=userinp.substring(0,i+1);
			temp2=temp1.substring(i);
			if (temp2.equals("*") || temp2.equals("/") || temp2.equals("+") || temp2.equals("-")){
				if (operator==0){
					switch (temp2){
						case "*":
							operator=1;
							break;
						case "/":
							operator=2;
							break;
						case "+":
							operator=3;
							break;
						case "-":
							operator=4;
							break;
					}
				}else if( ( (operator==1) && !(temp2.equals("*")) ) || ( (operator==2) && !(temp2.equals("/")) ) ||  ( (operator==3) && !(temp2.equals("+")) ) || ( (operator==4) && !(temp2.equals("-")) )  )
				{
					operator=5;
				}
				position[count]=i;
				count++;
				this.count=count;
			}
		}
		//sets last used data position +1 to length 
		//for use in splitting up the numbers
		position[count]=userinp.length();
		return position;
	}
	
	public float[] nums(String userinp,int[] position){
		float[] nums= new float[count+1];
		for (int i=0; i<count;i++){
			nums[i]=Float.parseFloat(userinp.substring(position[i]+1,position[i+1]));
			//System.out.println(nums[i]);
		}
		return nums;
	}
	
	public float calculation(float[] nums){
		//count is the number of commands
		float answer=0;
		int x=0;
		int count= this.count;
		
		
		switch (operator){
			case 1:
				answer=1;
				while((x+1) <= count){
					answer=answer*nums[x];
					x++;
				}
				//System.out.println("1");
				break;
			case 2:
				answer=nums[0];
				float denominator=1;
				while(x+1 <= count-1){
					denominator=denominator*nums[x+1];
					x++;
				}
				answer=nums[0]/denominator;
				//System.out.println(denominator);
				//System.out.println("1");
				break;
			case 3:
				while(x <= count){
					answer=answer+nums[x];
					x++;
				}
				//System.out.println("1");
				break;
			case 4:
				answer=nums[0];
				while((x+1) <= count){
					answer=answer-nums[x];
					x++;
				}
				//System.out.println("1");
				break;
			case 5:
				System.out.println("There has been an error only supports 1 command character");
				break;
		}
		
		return answer;
	}
}
