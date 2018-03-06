package francispeabody;
import java.util.Arrays;
import java.util.HashSet;
/**
 * Hello world!
 *
 */
public class Searcher implements Runnable
{

    Searcher()
    {
    }

    public void run()
    {

    }

    public HashSet<Match> search(String[][] searchspace, HashSet<String> searchterms, int globalX, int globalY)
    {
        HashSet<Match> result = new HashSet<Match>();
        for (int i = globalY; i < searchspace.length; i++){
            String word = String.join(",", searchspace[i]);
            if(searchterms.contains(word))
            {
                result.add(new Match(word, false, globalX, i));
            }
        }

        for (int i = globalX; i < searchspace.length; i++)
        {
            String word = "";
            for (int j = globalY; j < searchspace.length; i++)
            {
                word.concat(searchspace[i][j]);
            }
            if(searchterms.contains(word))
            {
                result.add(new Match(word, true, i, globalY));
            }
        }

        return result;

    }

}
