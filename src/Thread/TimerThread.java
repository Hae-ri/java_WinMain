package Thread;

class TimerThread extends Thread {
	int n = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println(n);
			n++;
			try {
				sleep(1000);				
			}catch(InterruptedException e){
				return;
			}
			
		}
	}

}
