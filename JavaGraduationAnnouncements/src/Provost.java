

public class Provost implements Runnable {

	public Provost() {}

	@Override
	public void run() {
		System.out.println("in Provost.run");
		try {
			int counter = 0;
			while (true){
				Main.readyToCalculate.acquire();
				for(int i = 0; i < 20; i++) {
					if ((Main.calculated[i] == 0)&&(Main.Students[i].graded)) {
						int GPA = calculate(Main.Students[i].grades);
						Main.announcements.add(new Pair(i,GPA));
						Main.calculated[i] = 1;
						Main.readyToAnnounce.release();
						counter++;
						//System.out.println("calculated student "+ i);
					}	
				}
				if (counter >= 20) {
					return;
				}
				
			}
		} catch (InterruptedException e) {
				e.printStackTrace();
		}

	}
	
	public int calculate(int[] grades){
		return (grades[0]+grades[1]*2+grades[2]+grades[3]*2+grades[4])/7;
	}

}
