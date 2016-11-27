import java.util.Arrays;

public class ForbiddenStreets {
	
	 
	public int[][] calculateFastestPaths(int adjacencyMatrix[][], int n){
		
		int [][] distanceMatrix = new int[n][n];
        for (int i=0;i<distanceMatrix.length;i++){
        	for (int j=0;j<distanceMatrix[0].length;j++){
            	distanceMatrix[i][j] = adjacencyMatrix[i][j];    
            }
        }
        
        for (int k = 0; k < n; k++)
        {
            // Pick all vertices as source one by one
            for (int i = 0; i < n; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (int j = 0; j < n; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                	if (distanceMatrix[i][k] == Integer.MAX_VALUE || distanceMatrix[k][j] == Integer.MAX_VALUE) continue;
                    if (distanceMatrix[i][k] + distanceMatrix[k][j] < distanceMatrix[i][j])
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                }
            }
        }
        
		return distanceMatrix;
	}
	
	/**
	 * Find method creates distance matrix by Floyd Warshall algorithm
	 * We create a base distance matrix that has all the streets included
	 * Then we create other distance matrix that excludes one street 
	 * Compare, if the time increases then the buses passing through that route is affected
	 */
	
	public int[] find (int n, int A[], int B[], int time[]){
		//Create adjacency matrix
        
        int [][] adjacencyMatrix = new int[n][n];
        
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
                adjacencyMatrix[i][j]=Integer.MAX_VALUE;
            }
		}
        
        for (int i=0;i<A.length;i++){
        	adjacencyMatrix[A[i]][B[i]] = time[i];
            adjacencyMatrix[B[i]][A[i]] = time[i];
        }
        int baseDistanceMatrix[][] = calculateFastestPaths(adjacencyMatrix,n);
        int[] results = new int[A.length];
        //For each street in A,B that we exclude
        for (int i=0;i<A.length;i++){
            //We define and initialize a tempAdjMatrix
        	int [][]tempAdjancencyMatrix = new int [n][n];    
            
            for (int j =0;j<n;j++){
                for (int k = 0;k<n;k++){
                	    tempAdjancencyMatrix[j][k] = Integer.MAX_VALUE;
                }
                }
            
            //Exclude replacing the time for the street (i) we don't want to consider 
            for (int j=0;j<A.length;j++){
            	if (i !=j)
                    {
            		tempAdjancencyMatrix[A[j]][B[j]] = time[j];
                	tempAdjancencyMatrix[B[j]][A[j]] = time[j];
                	}
            }
            //Get the fatestpaths now
            int [][] tempDistanceMatrix = calculateFastestPaths(tempAdjancencyMatrix,n);
            int busesaffected = 0;
            for (int j=0;j<n;j++){
            	for (int k=0;k<j;k++){
                	if (baseDistanceMatrix[j][k] < tempDistanceMatrix[j][k]) busesaffected ++;
                }
                
            }
            results[i] = busesaffected;
            
        }
    	    
		return results;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int[] A = new int []{0,0,0,0,1,1,1,2,2,3};
		int[] B = new int []{1,2,3,4,2,3,4,3,4,4};
		
		int[] time = new int []{1,2,3,4,5,6,7,8,9,10};
		
		System.out.println(Arrays.toString(new ForbiddenStreets().find(n,A,B,time)));
	}

}