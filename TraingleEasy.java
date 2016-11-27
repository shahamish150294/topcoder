//https://community.topcoder.com/stat?c=problem_statement&pm=14324

public class TriangleEasy {
	
	public int find(int n, int[] x, int[] y){
		
		//Prepare an adjacency matrix and mark edges as true
		boolean [][] adjacencyMatrix  = new boolean[n][n];
		if (x.length ==0 || y.length ==0){
			return 3;
		}
		for (int i=0;i<x.length;i++){
			adjacencyMatrix[x[i]][y[i]] = true;
			adjacencyMatrix[y[i]][x[i]] = true;
		}
		
		//if  a right angle is formed from the adjacency matrix then no edges to be added.
		//if Trues are present in the same col or rows then  
		//  0 1 2 3
		//0 F F T T
		//1 F F T F
		//2 T T F T
		//3 T F T F
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
					for (int k=0;k<n;k++){
						if (i!=k && i !=j && j !=k){
						if (adjacencyMatrix[i][j] && adjacencyMatrix[i][k]){
							return adjacencyMatrix[j][k] ? 0:1; 
						}
						}
					}
				}
			}
		
		return 2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []x = new int[]{0,2};
		int []y = new int[]{1,3};
		
		System.out.println(new TriangleEasy().find(4, x,y));
	}

}