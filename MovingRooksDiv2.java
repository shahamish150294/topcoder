package MovingRooksDiv2;

/*
{3,1,2,0}
{0,2,1,3}
*/
import java.util.*;
class MovingRooksDiv2{
	public String  move (int y1[], int y2[]){
        
        Deque<String> stack = new ArrayDeque<String>();
        Set<String> set = new HashSet<String>();
        String start = convertToString(y1);
        String end = convertToString(y2);


        stack.push(start);
        set.add(start);

        while(!stack.isEmpty())
        {

        	String current = stack.pop();
        	if (current.equals(end)){
        		return "POSSIBLE";
        	}

        	int []currentArray = convertToArray(current);

        	//Swap
        	for (int r1=0;r1<currentArray.length-1;r1++){
        		int c1 = currentArray[r1];
        		for (int r2=r1+1;r2<currentArray.length;r2++){
        			int c2 = currentArray[r2];
        			if (r1<r2 && c1>c2){
        				int []tempArray = copyToArray(currentArray);
        				tempArray[r1] = c2;
        				tempArray[r2] = c1;

        				String tempString = convertToString(tempArray);
        				if (!set.contains(tempString)){
        					set.add(tempString);
        					stack.push(tempString);
        				}
        			}
        		}
        	}
        }
        return "IMPOSSIBLE";

    }
	public static void main(String arg[]){
		int y1[] = new int[]{3,1,2,0};
		int y2[] = new int[]{3,2,0,1};
		System.out.println(new MovingRooksDiv2().move(y1, y2));
	}
    public static String convertToString(int y1[]){
    	StringBuilder s = new StringBuilder();
    	for (int i=0;i<y1.length;i++){
    		s.append(y1[i]+""); 
    	}
    	return new String(s);

    }

    public static int[] convertToArray(String s){
    	int []y1 = new int[s.length()];
    	for (int i=0;i<s.length();i++){
    		y1[i] = s.charAt(i)-48;
    	}
    	return y1;
    	
    }

    public static int[] copyToArray(int []s){
    	int temp[] = new int[s.length];
    	for(int i=0;i<temp.length;i++){
    		temp[i] = s[i];
    	}
    	return temp;
    }
    
}