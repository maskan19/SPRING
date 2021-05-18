package kr.or.ddit.employee.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.ddit.employee.dao.IEmployeeDAO;

@Component //상위
public class RealEmployeeDeleteJob {
	private static final Logger logger = LoggerFactory.getLogger(RealEmployeeDeleteJob.class);
	@Inject
	private IEmployeeDAO dao;

//	@Scheduled(cron = "0 0 3 ? * MON")//매주 월요일 새벽세시
	@Scheduled(cron = "* * * * * MON")
	public void realDelete() {
		Map<String, Object> pMap = new HashMap<>();
		
//		dao.deleteEmp(pMap);
		Integer delCount = (Integer)pMap.get("delcount");
		
		logger.info("스케줄러가 동작하여 {} 탈퇴되었음", delCount);
	}
	
}
