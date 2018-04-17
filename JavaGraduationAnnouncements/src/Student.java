public class Student {
	int[] grades = {-1, -1, -1, -1, -1};
	public boolean graded = false;
	
	public Student() {};
	
	public void recieveGrade(int prof, int grade) {
		grades[prof] = grade;
		for (int i = 0; i < 5; i++) {
			if(grades[i] < 0) {
				return;
			}
		}
		Main.readyToCalculate.release();
		graded = true;
	}
}
