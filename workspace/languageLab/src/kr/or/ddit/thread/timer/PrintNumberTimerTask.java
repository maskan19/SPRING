package kr.or.ddit.thread.timer;

import java.util.TimerTask;

public class PrintNumberTimerTask extends TimerTask{

	private int number;

	@Override
	public void run() {
		if(number==100) cancel(); //break와 유사
		System.out.printf("%d - %s(of %d)\n", ++number, Thread.currentThread().getName(), Thread.activeCount());
		
	}

}
