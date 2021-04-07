package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet {
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance(); // 세션 팩토리가 싱글톤이므로 모든 DAO의 상태가 동일하다?

	private void addAttribute(HttpServletRequest req) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		String prod_lgu;
		prod_lgu = req.getParameter("prod_lgu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(prod_lgu);

		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);

		String pageParam = req.getParameter("page");
		String prodLgu = req.getParameter("prod_lgu");
		String prodBuyer = req.getParameter("prod_buyer");
		String prodName = req.getParameter("prod_name");
		ProdVO detailSearch = ProdVO.builder().prod_lgu(prodLgu).prod_buyer(prodBuyer).prod_name(prodName).build();

//		builder패턴을 사용하면 생성자를 대신할 수 있다.

		int currentPage = 1;
		if (pageParam != null && pageParam.matches("\\d+")) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setDetailSearch(detailSearch);
		pagingVO.setCurrentPage(currentPage);

		int totalRecord = service.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		String accept = req.getHeader("Accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			//마샬링+직렬화
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();
					){
				mapper.writeValue(out, pagingVO);//write 계열이 마샬링
			}
			
		}else {
			req.setAttribute("pagingVO", pagingVO);
			String view = "/WEB-INF/views/prod/prodList.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		}
			

	}
}
