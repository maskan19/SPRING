package kr.or.ddit.employee.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.employee.dao.IEmployeeDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {
	private static final Logger logger =
			LoggerFactory.getLogger(AuthenticateServiceImpl.class);
	@Inject
	private IEmployeeDAO dao;
	
	@Override
	public ServiceResult authenticate(EmployeeVO empVO) {
		//id로 검색한 emp
		EmployeeVO dbEmp = dao.selectEmpForAuth(empVO.getEmployee_id());
		ServiceResult result = null;
		//해당 id 존재
		if(dbEmp!=null) {
			String inputPass = empVO.getEmployee_pwd();
			try {
//				String encodedPass = CryptoUtil.sha512(inputPass);
				String dbPass = dbEmp.getEmployee_pwd();
				//pw 일치
				if(dbPass.equals(inputPass)) {
					try {
						BeanUtils.copyProperties(empVO, dbEmp);
					} catch (IllegalAccessException | InvocationTargetException e) {
						throw new RuntimeException(e);
					}
					result = ServiceResult.OK;
					//pw 불일치
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}
			}catch (Exception e) {
				logger.error("", e);
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}










