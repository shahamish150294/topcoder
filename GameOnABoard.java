package GameOnABoard;

import java.util.ArrayList;
import java.util.List;

public class GameOnABoard{
    public static int optimalChoice(String[] cost){
    	int m = cost.length;
        int n = cost[0].length();
        
        
        int [][]minPaths = new int[m][n];
        boolean [][]visited = new boolean[m][n];
        int [][]minResultspath = new int[m][n];
        
        
        //Initialize minPaths
        for (int i=0;i<minPaths.length;i++){
            char temp [] = cost[i].toCharArray();
            for(int j=0;j<temp.length;j++){
            	minPaths[i][j] = temp[j] == '0' ? 0: 1;
            }
        }
        int minValueL = Integer.MAX_VALUE;
        //Select x1,y1 with the two outer for loops and calculate the min distance to all the other cells assuming they are x2,y2 
        for (int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
            	//calculate min distances to all points from x1,y1
                {
                    //At every selection of x1,y1 initialize the results matrix and visited matrix
                    for(int p = 0;p<m;p++){
                    	for(int q = 0;q<n;q++){
                        	visited[p][q] = false;
                            minResultspath[p][q] = 10000;
                        }
                    }
                    visited[i][j] = true;
                    minResultspath[i][j] = minPaths[i][j];
                    List<Cell> listCells = new ArrayList<Cell>();
                    listCells.add(new Cell(i,j));
                    while(listCells.size() > 0){
                    	Cell current = listCells.remove(0);
                        int row = current.x1;
                        int col = current.y1;
                        //Check left
                        if (row > 0 && minResultspath[row-1][col] > (minPaths[row-1][col] + minResultspath[row][col])){
                            minResultspath[row-1][col] = (minPaths[row-1][col] + minResultspath[row][col]);
                            listCells.add(new Cell(row-1,col));
                            }
                        //Check right
                        if (col < n-1 && minResultspath[row][col+1] > (minPaths[row][col+1] + minResultspath[row][col])){
                            minResultspath[row][col+1] = (minPaths[row][col+1] + minResultspath[row][col]);
                            listCells.add(new Cell(row,col+1));
                            }
                        //Check top
                        if (col > 0 && minResultspath[row][col-1] > (minPaths[row][col-1] + minResultspath[row][col])){
                            minResultspath[row][col-1] = (minPaths[row][col-1] + minResultspath[row][col]);
                            listCells.add(new Cell(row,col-1));
                            }
                        //Check bottom
                        if (row < m-1 && minResultspath[row+1][col] > (minPaths[row+1][col] + minResultspath[row][col])){
                            minResultspath[row+1][col] = (minPaths[row+1][col] + minResultspath[row][col]);
                            listCells.add(new Cell(row+1,col));
                            }
                        
                    }
                }
                
                //Get the max of the minimum paths, that will be the L Bob will try to get
                int maxValueL = 0;
                for (int p = 0;p<m;p++){
                 	for (int q = 0;q<n;q++){
                 		maxValueL = Math.max(minResultspath[p][q], maxValueL);
                	}	   
                }
                //Get the min of all the Max Ls
                minValueL = Math.min(minValueL,maxValueL);
            }
        }
        
        return minValueL;    
    }
    public static void main(String arg[]){
    	String[] cost = new String[]{"011", "011"}; 
    	System.out.println(optimalChoice(cost));
    }
}
class Cell{
	int x1;
   	int y1;
    Cell(int x,int y){
    x1=x;
    y1= y;
    }
}