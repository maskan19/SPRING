package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class BuyerListController {
	private IBuyerService service = BuyerServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance(); // 세션 팩토리가 싱글톤이므로 모든 DAO의 상태가 동일하다?

	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		String prod_lgu;
		prod_lgu = req.getParameter("prod_lgu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(prod_lgu);

		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}

	@RequestMapping("/buyer/buyerList.do")
	public String prodList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("detailSearch") BuyerVO detailSearch, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		addAttribute(req);

		PagingVO<BuyerVO> pagingVO = new PagingVO<>();
		pagingVO.setDetailSearch(detailSearch);
		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.retrieveBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<BuyerVO> prodList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(prodList);

		String accept = req.getHeader("Accept");
		String view = null;
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			// 마샬링+직렬화
			ObjectMapper mapper = new ObjectMapper();
			try (PrintWriter out = resp.getWriter();) {
				mapper.writeValue(out, pagingVO);// write 계열이 마샬링
			}
		} else {
			req.setAttribute("pagingVO", pagingVO);
			view = "buyer/buyerList";
		}
		return view;
	}

	@RequestMapping("/buyer/buyerView.do")
	public String prodView(@RequestParam(value = "where", required = true, defaultValue = "") String buyer_id,
			HttpServletRequest req) {

		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		return "buyer/buyerView";
	}
}
