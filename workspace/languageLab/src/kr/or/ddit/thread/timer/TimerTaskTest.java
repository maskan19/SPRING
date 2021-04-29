
package kr.or.ddit.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {

	public static void main(String[] args) {
		PrintNumberTimerTask task = new PrintNumberTimerTask();
		
		
		Timer timer = new Timer();
		
		timer.schedule(task, 0,1000);
	}
	
}