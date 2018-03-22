
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class WordSearcher {

	 public static void main( String[] args ) 
	    {
	        List<String> terms = Arrays.asList("array", "class", "queue", "stack", "trees");
	        HashSet<String> searchterms = new HashSet<String>(terms);
	        char[][] searchspace =		{{'t', 'c', 'l', 'a', 's', 'q', 'u'}, 
										{'r', 'l', 'e', 't', 't', 'u', 's'},
										{'e', 'a', 't', 'r', 'e', 'e', 's'}, 
										{'e', 's', 'k', 'a', 't', 'u', 't'},
										{'c', 's', 'q', 'u', 'a', 'e', 'a'},
										{'a', 'r', 'r', 'a', 'y', 'm', 'c'},
										{'c', 'r', 's', 'q', 'y', 'v', 'k'}};
	        HashSet<Match> masterlist = new HashSet<Match>();
	        Searcher[][] searchers = new Searcher[3][3];
	        Thread[][] threads = new Thread[3][3];
	       	for (int i = 0; i < 3; i++) {
	       		for (int j = 0; j < 3; j++) {

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
	   
        		}
	        }
	       	
	       	for (int i = 0; i < 3; i++) {
	       		for (int j = 0; j < 3; j++) {
	       			masterlist.addAll(searchers[i][j].result);
	       		}
	       	}
	        Iterator iterator = masterlist.iterator();		
	        System.out.println("FORMAT: word, orientation, (x y)");
	        while(iterator.hasNext()) {
	        	System.out.println(iterator.next());
	        }
	        System.out.println("DONE");
	    }

}
