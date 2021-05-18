package kr.or.ddit.employee.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.Constants;
import kr.or.ddit.employee.dao.IEmployeeDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Inject
	private IEmployeeDAO dao;
	
	@Value("#{appInfo.attatchPath}")
	private String attatchPath;
	
	@Value("#{appInfo.adminAuth}")
	private String adminAuth;

	private File saveFolder;

	@PostConstruct
	public void init() {
		saveFolder = new File(attatchPath);
		logger.info("{} 초기화, {} 주입됨.", getClass().getSimpleName(), saveFolder.getAbsolutePath());
	}
	
	@Transactional
	@Override
	public ServiceResult enrollEmp(EmployeeVO empVO) {
		ServiceResult result = ServiceResult.FAIL;
		int cnt = dao.insertEmp(empVO);
		if (cnt > 0) {
			try {
				empVO.saveTo(saveFolder);
				result = ServiceResult.OK;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	@Override
	public int retrieveEmpCount(PagingVO<EmployeeVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectEmpCount(pagingVO);
	}

	@Override
	public List<EmployeeVO> retrieveEmpList(PagingVO<EmployeeVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectEmpList(pagingVO);
	}

	@Override
	public EmployeeVO retrieveEmp(EmployeeVO empVO) {
		// TODO Auto-generated method stub
		return dao.selectEmp(empVO);
	}

	@Transactional
	@Override
	public ServiceResult modifyEmp(EmployeeVO empVO) {
		ServiceResult result = ServiceResult.FAIL;
//		encodePassword(empVO);
		int cnt = dao.updateEmp(empVO);
		if (cnt > 0) {
			try {
				empVO.saveTo(saveFolder);
				result = ServiceResult.OK;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult removeEmp(EmployeeVO empVO) {
		ServiceResult result = ServiceResult.FAIL;
		EmployeeVO delTargetEmp = dao.selectEmp(empVO);
//		encodePassword(search);
		String targetPass = delTargetEmp.getEmployee_pwd();
		String inputPass = empVO.getEmployee_pwd();
		// 인증
		if (targetPass.equals(inputPass)) {
			
			int cnt = dao.deleteEmp(delTargetEmp);
			if (cnt > 0) {
				result = ServiceResult.OK;
			}
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public boolean adminAuthenticate(EmployeeVO empVO) {
		EmployeeVO authTarget = dao.selectEmp(empVO);
		String auth = authTarget.getEmployee_authority();
		return adminAuth.equals(auth);
	}

}
