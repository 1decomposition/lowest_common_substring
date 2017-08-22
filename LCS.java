
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *Longest Common Subsequence
 * @author justincoy
 */
public class LCS {

    /**
     * Takes two strings and returns the lowest
     * common substring using dynamic programming
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String x,y;
        String lcs = "";
        String inputFile = args[0];
        //String inputFile = "input.txt";
        String pr;
        Pair pair;
        
        try {
            File file = new File(inputFile).getAbsoluteFile();
            PrintWriter writer = new PrintWriter("OutputLCS.txt","UTF-8");
            Scanner in = new Scanner (file);
            //Reads strings from file, finds lowest common subsstring,
            //and prints lowest common substring to file.
            while(in.hasNext()) {
                x = in.next();
                y = in.next();
                pair = lcs(x,y);
                String[][] b = pair.getArray1();
                System.out.println(printLCS(b,x,x.length(),y.length(),lcs));
                pr=printLCS(b,x,x.length(),y.length(),lcs);
                writer.println(pr);
            }
            in.close();
            writer.close();
        }
        catch(Exception e) {
            e.getMessage();
        }
    }
    
    //Finds lowest common substring between two strings. Returns an
    //object that contains arrays b and c
    public static Pair lcs(String x,String y) {
        String lcs = null;
        String[][] b = new String[x.length()+1][y.length()+1];
        int[][] c = new int[x.length()+1][y.length()+1];
        
        for(int i = 0; i < x.length(); i ++) {
            for(int j = 0; j < y.length(); j++) {
                c[i][j]=0;
            }
        }
        
        for(int i = 1; i < x.length()+1; i ++) {
            for(int j = 1; j < y.length()+1; j++) {
                if(x.charAt(i-1) == y.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = "diag";
                }
                else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    b[i][j] = "up";
                } 
                else {
                    c[i][j] = c[i][j-1];
                    b[i][j] = "left";
                }                   
            }
        }
        return new Pair(b,c);
    }
    //Prints the lowest common substring of two strings
    public static String printLCS(String[][] b,String x,int i,int j,String lcs) {
        if(i==0 || j==0) return lcs;
        if(b[i][j].equals("diag")) {
            return printLCS(b,x,i-1,j-1, x.charAt(i-1) + lcs);
        }
        else if(b[i][j].equals("up")) {
                    return printLCS(b,x,i-1,j,lcs);
                    }
        else return printLCS(b,x,i,j-1,lcs);
        
       
    }
    
    //Pair class implemented to be used as an object that can return two arrays
    public static class Pair
{
    private String[][] b;
    private int[][] c;
    public Pair(String[][] array1, int[][] array2)
    {
        this.b = array1;
        this.c = array2;

    }
    public String[][] getArray1() { return b; }
    public int[][] getArray2() { return c; }
}
    
}
