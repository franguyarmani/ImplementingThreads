package francispeabody;
import java.util.Arrays;
import java.util.HashSet;
/**
 * Hello world!
 *
 */
public class Match
{
    public boolean orientation; //true = verticle
    public int x;
    public int y; 
    public String word;

    Match(String word, boolean orientation, int x, int y)
    {
        this.orientation = orientation;
        this.x = x;
        this.y = y;
        this.word = word;
    }
}