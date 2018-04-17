import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
	static int[][] GradeBooks = {{80, 70, 30, 50, 90, 99, 94, 42, 66, 75, 82, 25, 69, 42, 87, 71, 70, 15, 95, 83},
			{75, 30, 40, 62, 88, 92, 75, 39, 70, 70, 80, 40, 55, 70, 95, 70, 75, 30, 80, 72},
			{90, 75, 52, 76, 81, 91, 80, 55, 75, 80, 81, 60, 81, 85, 80, 68, 80, 42, 75, 78},
			{72, 65, 47, 55, 78, 98, 82, 68, 69, 92, 79, 72, 63, 60, 68, 82, 78, 61, 90, 81},
			{88, 80, 39, 52, 92, 100, 93, 45, 58, 85, 84, 38, 59, 52, 92, 74, 82, 58, 60, 65}};
	static Student[] Students = new Student[20];
	static Semaphore dbAccess = new Semaphore(1);
	static Semaphore readyToCalculate = new Semaphore(1);
	static int[] calculated = new int[20];
	static Thread[] threads = new Thread[7];
	static int[][] professorAttributes = {
			{0,3000,3000,3,6000,3},
			{1,4200,4200,7,12000,7},
			{2,6000,6000,1,0,-1},
			{3,18000,6000,1,0,-1},
			{4,4800,4800,1,0,-1}};
	static Professor profs[] = new Professor[5];
	static Semaphore readyToAnnounce =  new Semaphore(3);
	static ConcurrentLinkedQueue<Pair> announcements = new ConcurrentLinkedQueue<Pair>(); 

	
	
	
	

	public static void main(String[] args) {
		for (int j = 0; j < 20; j++) {
			Students[j] = new Student();
		}
		for (int i = 0; i < 5; i++) {
			profs[i] = new Professor(professorAttributes[i]);
			threads[i] = new Thread(profs[i]);
			threads[i].start();
		}
		Thread provost = new Thread(new Provost());
		System.out.println("Threads created");
		provost.start();
		System.out.println("Provost started");
		readyToCalculate.drainPermits();
		readyToAnnounce.drainPermits();
		
		//announcer
		try {
			int count = 0;
			System.out.println("in announcer");
			while (provost.isAlive()) {
				if(!readyToAnnounce.tryAcquire(3, 500, TimeUnit.MILLISECONDS)) {
					continue;	
				}
				//System.out.println("in announcer.whileloop 'semaphore acquired'");
				for(int i = 0; i < 3; i++) {
					Pair student = announcements.poll();
					if(student.right < 75) {
						System.out.println("Student "+(student.left+1)+" Failed");
						//System.out.println("announced "+count++);
					} else if (student.right >= 75) {
						System.out.println("Student "+(student.left+1)+" Graduated");
						//System.out.println("announced "+count++);
					}
				}
				readyToAnnounce.tryAcquire(3);
			}
			for(int i = 0; i < 2; i++) {
				Pair student = announcements.poll();
				if(student.right < 75) {
					System.out.println("Student "+(student.left+1)+" Failed");
					//System.out.println("announced "+count++);
				} else if (student.right >= 75) {
					System.out.println("Student "+(student.left+1)+" Graduated");
					//System.out.println("announced "+count++);
				}
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
}


/*
 * Semaphores 
 * dbAccess: Is someone reading/writing the database?
 * calculated: Are 3 students ready to be announced?
 * 
 * Queue
 * to be calculated queue
 * 
 * 
 * Threads
 * Thread for each professor
 * Thread to add up grades
 * Thread to Print the announcement
 * 
*/
