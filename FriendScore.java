import java.util.Stack;

//https://community.topcoder.com/stat?c=problem_statement&pm=10343
class Friend{
	int row;
	int col;
	Friend(int r, int c){row=r;col=c;}
}
public class FriendScore {

	public int highestScore(String[] friends){
		
		
		//int []connections = new int[];
		//Use stack
		Stack<Integer> s = new Stack<Integer>();
		int count2friends = 0;
		int maxfriends = 0;
		int index  =0;
		for (int i=0;i<friends.length;i++){
			boolean []visited = new  boolean[friends[i].length()];
			visited[i] = true;
			
			for (int j=0;j<friends[i].length();j++){
				
				if(friends[i].charAt(j) == 'Y' && visited[i]){
					
					s.push(j);
					count2friends++;
					visited[j] = true;
				}
				
			}
			count2friends += check2friends (visited, friends, 0, s);
			maxfriends = Math.max(maxfriends,count2friends);
			System.out.println(i+" "+ count2friends);
			count2friends = 0;
		}
		
		return maxfriends;
	}
	private static int check2friends(boolean visited[], String[] friends, int count, Stack s) {
		

		while (!s.isEmpty()){
			int j = (Integer) s.pop();
			for (int k=0;k<friends[j].length();k++){
				if (visited[k]) continue;
				else if (friends[j].charAt(k) == 'Y')
					count++;
			}
		}
		return count;
	}
	
	public static void main(String arg[]){
		String friends[] = new String[]					
			 	
			 	
				
				
				{"NNNNYNNNNN",
				 "NNNNYNYYNN",
				 "NNNYYYNNNN",
				 "NNYNNNNNNN",
				 "YYYNNNNNNY",
				 "NNYNNNNNYN",
				 "NYNNNNNYNN",
				 "NYNNNNYNNN",
				 "NNNNNYNNNN",
				 "NNNNYNNNNN"}
	;
		System.out.println(new FriendScore().highestScore(friends));
	}
}