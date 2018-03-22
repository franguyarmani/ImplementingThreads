
public class Match {
	String w;
	boolean o;
	int x;
	int y;
	
	public Match(String word, boolean orientation, int x, int y) {
		this.w = word;
		o = orientation;
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		if(o) {
			return w+" verticle "+x+" "+y;
		}else {
			return w+" horizontal "+x+" "+y;
		}
	}
}
