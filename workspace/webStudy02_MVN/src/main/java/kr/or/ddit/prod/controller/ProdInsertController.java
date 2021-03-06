package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController extends HttpServlet{
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();// 세션 팩토리가 싱글톤이므로 모든 DAO의 상태가 동일하다?
	
	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList 
			= othersDAO.selectLprodList();
		List<BuyerVO> buyerList 
			= othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String insertForm(HttpServletRequest req) {
		addAttribute(req);
		String view = "prod/prodForm";
		return view;
	}
	
	@RequestMapping(value = "/prod/prodInsert.do",method=RequestMethod.POST)
	public String prodInsert(
			@ModelAttribute("prod") ProdVO prod,
			HttpServletRequest req, HttpServletResponse resp) {
//		ProdVO prod = new ProdVO();
//		req.setAttribute("prod", prod);
//		try {
//			BeanUtils.populate(prod, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}//한번에 넘기기
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(prod, errors);

//		prod.getprod_id(); //call by reference 구조
//		super.doPost(req, resp);
//		등록 성공시 / prodView.do

		String view = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/prod/prodView.do?what="+prod.getProd_id();
			}else {
				message = "서버 오류";
				view = "prod/prodForm";
			}
		}else {
			view = "prod/prodForm";
		}
		
		req.setAttribute("message", message);
		return view;
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		
		return valid;
	}
}