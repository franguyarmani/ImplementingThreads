
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class WordSearcher {

	 public static void main( String[] args ) 
	    {
	        List<String> terms = Arrays.asList("abcde", "fafaf");
	        HashSet<String> searchterms = new HashSet<String>(terms);
	        char[][] searchspace =		{{'a', 'b', 'c', 'd', 'e', 'f', 'g'}, 
										{'f', 'g', 'h', 'i', 'j', 'k', 'l'},
										{'a', 'b', 'c', 'd', 'e', 'f', 'g'}, 
										{'f', 'g', 'h', 'i', 'j', 'k', 'l'},
										{'a', 'b', 'c', 'd', 'e', 'f', 'g'},
										{'f', 'g', 'h', 'i', 'j', 'k', 'l'},
										{'a', 'b', 'c', 'd', 'e', 'f', 'g'}};
	        Searcher[][] searchers = new Searcher[3][3];
	        Thread[][] threads = new Thread[3][3];
	       	for (int i = 0; i < 3; i++) {
	       		for (int j = 0; j < 3; j++) {
	       			System.out.println(j);
	       			searchers[i][j] = new Searcher(searchspace, searchterms, i, j);
	       			threads[i][j] = new Thread(searchers[i][j]);
	       			threads[i][j].start();
        		}
	        }
	       	for (int i = 0; i < 3; i++) {
	       		for (int j = 0; j < 3; j++) {
	       			try {
	    				threads[i][j].join();
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	       			System.out.println(j+3);
        		}
	        }
			
	        System.out.println("DONE");
	    }

}
