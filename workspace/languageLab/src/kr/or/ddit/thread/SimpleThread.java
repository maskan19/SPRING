package kr.or.ddit.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. 1부터 100까지의 숫자를 매 1초마다 한번씩 1씩 증가하면서 콘솔에 출력 
 * 2. 1번의 작업을 매 2초마다 한번씩 반복 
 * 3. 최대 쓰레드의 갯수는 10개 미만으로 제한
 *
 */
public class SimpleThread {

//	public static void main(String[] args) throws InterruptedException {
//
//		Runnable counting = new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 1; i <= 100; i++) {
//					System.out.println(i);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
//		for (int i = 0; i < 10; i++) {
//			executorService.execute(counting);
//			Thread.sleep(2000);
//		}
//
//	}
	
	public static void main(String[] args) {
//		PrintNumberJobGenerator generator = new PrintNumberJobGenerator();
//		Thread thread = new Thread(generator);
//		thread.start();
		
	}
	
	
	

}
