package kr.or.ddit.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import kr.or.ddit.thread.PrintNumberJob;
import kr.or.ddit.thread.PrintNumberJobGenerator;

public class ThreadPoolTest {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5));
		//초기 세팅 3, 최대 5, 2, 초의 텀, 5개의 스레드까지 대기
		PrintNumberJobGenerator generator = new PrintNumberJobGenerator(executor);
		executor.execute(generator);
		//더이상 감당할 수 없는 요청이 들어왔을 때의 정책 설정
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.err.printf("%s가 거부당함.\n", r.getClass().getSimpleName());
				
			}
		});
		
	}
}
