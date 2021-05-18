package kr.or.ddit.employee.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

public interface IEmployeeService {
	
	public ServiceResult enrollEmp(EmployeeVO empVO);
	public int retrieveEmpCount(PagingVO<EmployeeVO> pagingVO);
	public List<EmployeeVO> retrieveEmpList(PagingVO<EmployeeVO> pagingVO);
	public EmployeeVO retrieveEmp(EmployeeVO empVO);
	public ServiceResult modifyEmp(EmployeeVO empVO);
	public ServiceResult removeEmp(EmployeeVO empVO);
	
	public boolean adminAuthenticate(EmployeeVO empVO);

}
