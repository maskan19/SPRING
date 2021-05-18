package kr.or.ddit.employee.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;

public interface IAuthenticateService {
	public ServiceResult authenticate(EmployeeVO empVO);
}
