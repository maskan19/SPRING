package kr.or.ddit.prod.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController {
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();// 세션 팩토리가 싱글톤이므로 모든 DAO의 상태가 동일하다?

	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}

	@RequestMapping("/prod/prodInsert.do")
	public String prodInsertForm(HttpServletRequest req) {
		addAttribute(req);
		return "prod/prodForm";
	}

	@RequestMapping(value = "/prod/prodInsert.do", method = RequestMethod.POST)
	public String prodInsert(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req) {
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = new CommonValidator<ProdVO>().validate(prod, errors, InsertGroup.class);

		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createProd(prod);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/prod/prodView.do?what=" + prod.getProd_id();
			} else {
				message = "서버 오류";
				view = "prod/prodForm";
			}
		} else {
			view = "prod/prodForm";
		}
		req.setAttribute("message", message);
		return view;
	}

}