import java.util.HashSet;


public class Searcher implements Runnable
{
    public char[][] searchspace;
    public HashSet<String> searchterms;
    public HashSet<Match> result = new HashSet<Match>();
    public int xoffset;
    public int yoffset;

    Searcher(char[][] searchspace, HashSet<String> searchterms, int yoffset, int xoffset)
    {
        this.searchspace = searchspace;
        this.searchterms = searchterms;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }

    public void run()
    {	
    	for (int i = 0; i < 5; i++) { //horizontal     i is the location in vertical space or row index or y
    		StringBuilder sb = new StringBuilder(5);
    		for (int j = 0; j < 5; j++) {
    			sb.append(searchspace[i+yoffset][j+xoffset]);
    		}
    		String w = sb.toString();

    		if (searchterms.contains(w)) {
    			result.add(new Match(w, false, xoffset, i+yoffset));
    		}
        }
    	for (int i = 0; i < 5; i++) { //Vertical		i is the location in horizontal space or the column index or x
    		StringBuilder sb = new StringBuilder(5);
    		for (int j = 0; j < 5; j++) {
    			sb.append(searchspace[j+yoffset][i+xoffset]);
    		}
    		String w = sb.toString();

    		if (searchterms.contains(w)) {
    			result.add(new Match(w, true, i+xoffset, yoffset));
    		}
        }
    	//System.out.println("done");
        
    }

    public HashSet<Match> search(String[][] searchspace, HashSet<String> searchterms, int globalX, int globalY)
    {
        
        return result;

    }

}