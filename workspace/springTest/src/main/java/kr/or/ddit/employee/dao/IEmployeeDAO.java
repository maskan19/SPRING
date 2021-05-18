package kr.or.ddit.employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IEmployeeDAO {

	public EmployeeVO selectEmpForAuth(String employee_id);

	public int insertEmp(EmployeeVO empVO);

	public int selectEmpCount(PagingVO<EmployeeVO> pagingVO);

	public List<EmployeeVO> selectEmpList(PagingVO<EmployeeVO> pagingVO);

	public EmployeeVO selectEmp(EmployeeVO empVO);

	public int updateEmp(EmployeeVO empVO);

	public int deleteEmp(EmployeeVO empVO);

	public int bannedEmp(EmployeeVO empVO);

}
