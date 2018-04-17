public class Professor implements Runnable {
	
	int wTS;
	int sI;
	int sV;
	int bT;
	int bF;
	int iD;
	
	
	

	public Professor(int[] args
//			int ProfessorID, 
//			int waitToStart, 
//			int submissionInterval, 
//			int submissionVolume, 
//			int breakTime, 
//			int breakFrequency
			)
	{
		iD = args[0];
		wTS = args[1];
		sI = args[2];
		sV = args[3];
		bT = args[4];
		bF = args[5];
		}

	@Override
	public void run() {
		try {
			Thread.sleep(wTS);
			int breakcounter = bF;
			int smolBreak = sI/sV;
			for (int i = 0; i < 20; i++) {
				int grade = Main.GradeBooks[iD][i];
				Main.dbAccess.acquire();
				Main.Students[i].recieveGrade(iD, grade);
				//System.out.println("Professor "+iD+" graded student "+i);
				breakcounter -= 1;
				Main.dbAccess.release();
				if (breakcounter == 0) {
					Thread.sleep(bT);
					breakcounter = bF;
				} else {
					Thread.sleep(smolBreak);
				}
			
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}

/*
 * Behavior
 * Cycle: Wait => Submit 
 * Grading: StartLag(Optional) => # of Cycles => Break
 * 
 * 
 * Attributes
 * Breaklength: an extended break that will occur every x amount of time (option, may repeat, may be one time) 
 * Submission number: a number of grades submitted per interval
 * Submission interval(seconds): period over which submissions take place
 * Break interval: number of cycles between breaks
 * StartLag: a one time break before grading starts (defaults to submission interval)
 * 
 * 
 */