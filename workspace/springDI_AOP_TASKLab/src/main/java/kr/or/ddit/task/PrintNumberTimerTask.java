package kr.or.ddit.task;

import java.util.TimerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class PrintNumberTimerTask {

	private int number;

//	fixedDelay 작업 종료 기준
//	@Scheduled(initialDelay = 0, fixedRate = 1000 )
	@Scheduled(cron = "* /5 3 ? * MON-FRI") //매월 일은 고려하지 않고 매주 월요일부터 금요일까지 3시 5초마다.(/5가 나누어떨어질 때)
	public void pojo() {
		System.out.printf("%d - %s(of %d)\n", ++number, Thread.currentThread().getName(), Thread.activeCount());
	}

}
