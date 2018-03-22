import java.util.Objects;

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
			return w+":\n  orientation: verticle \n  first letter position: ("+x+" "+y+")";
		}else {
			return w+":\n  orientation: horizontal \n  first letter position: ("+x+" "+y+")";
		}
	}
	@Override
	public boolean equals(Object o) {
		if ((o instanceof Match) && (((Match) o).w.equals(this.w)) && (((Match) o).o == this.o) && (((Match) o).x == this.x) && (((Match) o).y == this.y)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(w, o, x, y);
	}
}
