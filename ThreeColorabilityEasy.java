public class ThreeColorabilityEasy{
    public String isColorable(String[] cells){
    	int count = 0;
        for (int i=0;i<cells.length-1;i++){
            count = 0;
            for (int j=0;j<cells[i].length()-1;j++){
            	
                if (cells[i].charAt(j) == 'N') count++;
                    if (cells[i+1].charAt(j) == 'N') count++;
                    if (cells[i].charAt(j+1) == 'N') count++;
                    if (cells[i+1].charAt(j+1) == 'N') count++;
                if (count == 1 || count ==3) return "No";
            }
        }
        
        
        return "Yes";
    }
}