package kr.or.ddit.thread;

import java.util.concurrent.ThreadPoolExecutor;

public class PrintNumberJobGenerator implements Runnable {

	private ThreadPoolExecutor executor;

	public PrintNumberJobGenerator(ThreadPoolExecutor executor) {
		super();
		this.executor = executor;
	}

	@Override
	public void run() {
		while (true) {
			PrintNumberJob job = new PrintNumberJob();
//별개의 쓰레드를 사용하는 코드			
//			Thread thread = new Thread(job);
//			thread.start();

//쓰레드 풀에서 쓰레드를 사용하는 코드
			executor.execute(job);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
