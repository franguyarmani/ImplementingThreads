import java.util.concurrent.Semaphore;

public class Professor implements Runnable {
	
	int wTS;
	int sI;
	int sV;
	int bT;
	int bI;
	int iD;
	Semaphore sem;
	
	
	

	public Professor(
			int ProfessorID, 
			int waitToStart, 
			int submissionInterval, 
			int submissionVolume, 
			int breakTime, 
			int breakInterval, 
			Semaphore DatabaseAccess)
	{
		wTS = waitToStart;
		sI = submissionInterval; 
		sV = submissionVolume;
		bT = breakTime;
		bI = breakInterval;
		iD = ProfessorID;
		sem = DatabaseAccess;
	
		
		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(wTS);
			int breakcounter = 0;
			for (int i = 0; i < 20; i++) {
				long start = System.currentTimeMillis();
				int grade = Main.GradeBooks[iD][i];
				sem.acquire();
				Main.Students[i].grades[iD] = grade;
				
				
				
				
				
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public void writeGrade(int Grade) {
				
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